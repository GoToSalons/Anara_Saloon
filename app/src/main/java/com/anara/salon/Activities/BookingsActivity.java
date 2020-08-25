package com.anara.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.anara.salon.Adapters.BookingAdapter;
import com.anara.salon.Models.BookingModel;
import com.anara.salon.R;

import java.util.ArrayList;

public class BookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        RecyclerView booking_view = findViewById(R.id.booking_recycler);

        ArrayList<BookingModel> bookingModels = new ArrayList<>();
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Completed"));
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Upcoming"));
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Completed"));
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Completed"));
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Upcoming"));

        BookingAdapter bookingAdapter = new BookingAdapter(bookingModels);
        booking_view.setLayoutManager(new LinearLayoutManager(BookingsActivity.this));
        booking_view.setAdapter(bookingAdapter);

    }
}