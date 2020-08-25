package com.anara.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.anara.salon.Adapters.ChooseBarbersAdapter;
import com.anara.salon.Adapters.TimeSlotAdapter;
import com.anara.salon.Models.BarberModel;
import com.anara.salon.Models.TimeModel;
import com.anara.salon.R;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class SelectTimeBarber extends AppCompatActivity implements DatePickerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_barber);

        RecyclerView timeRecyclerView = findViewById(R.id.time_recycler);
        RecyclerView barberRecyclerView = findViewById(R.id.barber_recycler);

        HorizontalPicker picker = findViewById(R.id.datePicker);
        picker.setDateSelectedColor(getResources().getColor(R.color.colorPrimary))
                .setDateSelectedTextColor(getResources().getColor(R.color.colorWhite))
                .setMonthAndYearTextColor(getResources().getColor(R.color.colorWhite))
                .setTodayDateTextColor(getResources().getColor(R.color.colorWhite))
                .setBackgroundColor(getResources().getColor(R.color.colorDarkGrey));

        picker.setListener(this).init();

        ArrayList<TimeModel> timeModels = new ArrayList<>();
        timeModels.add(new TimeModel("11:00 AM"));
        timeModels.add(new TimeModel("11:30 AM"));
        timeModels.add(new TimeModel("12:00 PM"));
        timeModels.add(new TimeModel("12:30 PM"));
        timeModels.add(new TimeModel("1:00 PM"));
        timeModels.add(new TimeModel("1:30 PM"));

        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(timeModels);

        timeRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this,LinearLayoutManager.HORIZONTAL,false));
        timeRecyclerView.setAdapter(timeSlotAdapter);

        ArrayList<BarberModel> barberModels = new ArrayList<>();
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));
        barberModels.add(new BarberModel(getResources().getDrawable(R.drawable.akim),"Akim Kasmani"));

        ChooseBarbersAdapter chooseBarbersAdapter = new ChooseBarbersAdapter(barberModels);
        barberRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTimeBarber.this,LinearLayoutManager.HORIZONTAL,false));
        barberRecyclerView.setAdapter(chooseBarbersAdapter);

    }

    @Override
    public void onDateSelected(DateTime dateSelected) {

    }
}