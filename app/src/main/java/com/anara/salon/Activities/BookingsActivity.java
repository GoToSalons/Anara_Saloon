package com.anara.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anara.salon.Adapters.BookingAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Helpers.PrefManager;
import com.anara.salon.Models.BookingModel;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        RecyclerView booking_view = findViewById(R.id.booking_recycler);

        PrefManager prefManager = new PrefManager(BookingsActivity.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("customer_id",prefManager.getInteger("customerId",-1));
        } catch (JSONException e) {

            e.printStackTrace();
        }
        Log.e("=====","====="+jsonObject);
        RequestResponseManager.getBookings(jsonObject, Const.getBookings, new RequestResponseManager.OnResponseListener() {
            @Override
            public void onResponse(Object response) {
                BaseRs baseRs = (BaseRs) response;
                BookingAdapter bookingAdapter = new BookingAdapter(BookingsActivity.this,baseRs.getBookings());
                booking_view.setLayoutManager(new LinearLayoutManager(BookingsActivity.this));
                booking_view.setAdapter(bookingAdapter);
            }
        });
    }

    public void Rate(){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}