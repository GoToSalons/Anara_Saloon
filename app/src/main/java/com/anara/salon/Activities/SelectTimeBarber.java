package com.anara.salon.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Adapters.ChooseBarbersAdapter;
import com.anara.salon.Adapters.TimeSlotAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.ApiResponse.SalonServices;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Helpers.PrefManager;
import com.anara.salon.MainActivity;
import com.anara.salon.Models.BarberModel;
import com.anara.salon.Models.TimeModel;
import com.anara.salon.R;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectTimeBarber extends AppCompatActivity implements DatePickerListener, PaymentResultListener {

    public ArrayList<BarberModel> checked;
    public int barberId = -1;
    int salonId;
    ArrayList<SalonServices> salonServices;
    String date;
    RecyclerView timeRecyclerView;
    public String startTime, endTime;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_barber);

        timeRecyclerView = findViewById(R.id.time_recycler);
        RecyclerView barberRecyclerView = findViewById(R.id.barber_recycler);

        checked = new ArrayList<>();

        Bundle args = getIntent().getBundleExtra("checkedServices");
        salonServices = (ArrayList<SalonServices>) args.getSerializable("list");
        salonId = Integer.parseInt(getIntent().getStringExtra("salonId"));

        prefManager = new PrefManager(SelectTimeBarber.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("saloon_id", salonId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestResponseManager.getBarbers(jsonObject, Const.Get_Barbers, new RequestResponseManager.OnResponseListener() {
            @Override
            public void onResponse(Object response) {
                BaseRs baseRs = (BaseRs) response;
                ChooseBarbersAdapter chooseBarbersAdapter = new ChooseBarbersAdapter(baseRs.getBarbers(), SelectTimeBarber.this);
                barberRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this, LinearLayoutManager.HORIZONTAL, false));
                barberRecyclerView.setAdapter(chooseBarbersAdapter);
            }
        });


        HorizontalPicker picker = findViewById(R.id.datePicker);
        picker.setDateSelectedColor(getResources().getColor(R.color.colorPrimary))
                .setDateSelectedTextColor(getResources().getColor(R.color.colorWhite))
                .setMonthAndYearTextColor(getResources().getColor(R.color.colorWhite))
                .setTodayDateTextColor(getResources().getColor(R.color.colorWhite))
                .setBackgroundColor(getResources().getColor(R.color.colorDarkGrey));

        picker.setListener(this).init();

        ArrayList<TimeModel> timeModels = new ArrayList<>();


        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(timeModels, SelectTimeBarber.this);

        timeRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this, LinearLayoutManager.HORIZONTAL, false));
        timeRecyclerView.setAdapter(timeSlotAdapter);


        findViewById(R.id.next).setOnClickListener(view -> startPayment());

    }

    public void startPayment() {
        Activity activity = this;
        Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Anara Salon");
            options.put("description", "Payment");
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");

            double total = 0.0;
            for (SalonServices salonServices : salonServices) {
                total = total + Double.parseDouble(salonServices.getPrice());
            }

            options.put("amount", total * 100);
            co.open(activity, options);

        } catch (Exception e) {
            Log.e("error", " error " + e.toString());
        }
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        if (barberId == -1) {
            Toast.makeText(this, "Select Barber", Toast.LENGTH_SHORT).show();
        } else {
            date = dateSelected.toLocalDate().toString();
            getTimeSlots();
        }

    }

    public void getTimeSlots() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (SalonServices salonServices : salonServices) {
            jsonArray.put(salonServices.getService_id());
        }

        try {
            jsonObject.put("saloon_id", salonId);
            jsonObject.put("barber_id", barberId);
            jsonObject.put("services", jsonArray);
            jsonObject.put("book_date", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestResponseManager.getTimeSlots(jsonObject, Const.Get_Time_Slots, new RequestResponseManager.OnResponseListener() {
            @Override
            public void onResponse(Object response) {
                BaseRs baseRs = (BaseRs) response;
                if (baseRs.getTimeSlots()!=null && baseRs.getTimeSlots().size()!=0){
                    TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(baseRs.getTimeSlots(), SelectTimeBarber.this);
                    timeRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this, LinearLayoutManager.HORIZONTAL, false));
                    timeRecyclerView.setAdapter(timeSlotAdapter);
                }else {
                    ArrayList<TimeModel> timeModels = new ArrayList<>();
                    TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(timeModels, SelectTimeBarber.this);
                    timeRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this, LinearLayoutManager.HORIZONTAL, false));
                    timeRecyclerView.setAdapter(timeSlotAdapter);
                    Toast.makeText(SelectTimeBarber.this, "No Time Slots Available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        orderSuccess();
    }

    private void orderSuccess() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (SalonServices salonServices : salonServices) {
                jsonArray.put(Integer.parseInt(salonServices.getService_id()));
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("saloon_id", salonId);
            jsonObject.put("barber_id", barberId);
            jsonObject.put("services", jsonArray);
            jsonObject.put("book_date", date);
            jsonObject.put("customer_id", prefManager.getInteger("customerId",-1));
            JSONObject object = new JSONObject();
            object.put("start_time", startTime);
            object.put("end_time", endTime);
            jsonObject.put("booked_time", object);

            RequestResponseManager.orderSuccess(jsonObject, Const.orderSuccess, new RequestResponseManager.OnResponseListener() {
                @Override
                public void onResponse(Object response) {
                    Toast.makeText(SelectTimeBarber.this, "Order Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SelectTimeBarber.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            Log.e("eee", "eee" + e.getMessage());
        }

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "onPaymentError" + s, Toast.LENGTH_SHORT).show();
    }
}