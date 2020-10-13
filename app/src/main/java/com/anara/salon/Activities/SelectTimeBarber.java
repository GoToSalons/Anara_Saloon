package com.anara.salon.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SelectTimeBarber extends AppCompatActivity implements DatePickerListener, PaymentResultListener {

    public ArrayList<BarberModel> checked;
    public int barberId = -1;
    int salonId;
    ArrayList<SalonServices> salonServices;
    public String date;
    RecyclerView timeRecyclerView;
    public String startTime = "", endTime = "";
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_barber);

        timeRecyclerView = findViewById(R.id.time_recycler);
        RecyclerView barberRecyclerView = findViewById(R.id.barber_recycler);

        checked = new ArrayList<>();
        ImageView imageView = findViewById(R.id.back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle args = getIntent().getBundleExtra("checkedServices");
        salonServices = (ArrayList<SalonServices>) args.getSerializable("list");
        salonId = Integer.parseInt(getIntent().getStringExtra("salonId"));

        prefManager = new PrefManager(SelectTimeBarber.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("salon_id", salonId);
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

        findViewById(R.id.next).setOnClickListener(view -> {
            if (!startTime.equals("") || !endTime.equals("")) {
                startPayment();
            } else {
                Toast.makeText(this, "Select Time Slot", Toast.LENGTH_SHORT).show();
            }
        });
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
            double extraPer;
            for (SalonServices salonServices : salonServices) {
                total = total + Double.parseDouble(salonServices.getPrice());

            }
            extraPer = (total/100.0f)*10;
            options.put("amount", (extraPer+total) * 100);
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
            Calendar c = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(c.getTime());
            try {
                Date date1 = sdf.parse(formattedDate);
                Date date2 = sdf.parse(date);
                if (date1.compareTo(date2) <= 0) {
                    getTimeSlots();

                } else {
                    Toast.makeText(this, "Invalid Date", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
                if (baseRs.getTimeSlots() != null && baseRs.getTimeSlots().size() != 0) {
                    TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(baseRs.getTimeSlots(), SelectTimeBarber.this,date);
                    timeRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this, LinearLayoutManager.HORIZONTAL, false));
                    timeRecyclerView.setAdapter(timeSlotAdapter);
                } else {
                    ArrayList<TimeModel> timeModels = new ArrayList<>();
                    TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(timeModels, SelectTimeBarber.this,date);
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
            jsonObject.put("salon_id", salonId);
            jsonObject.put("barber_id", barberId);
            jsonObject.put("services", jsonArray);
            jsonObject.put("book_date", date);
            jsonObject.put("customer_id", prefManager.getInteger("customerId", -1));
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