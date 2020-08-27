package com.anara.salon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Adapters.ServicesAdapter;
import com.anara.salon.Models.ServicesModel;
import com.anara.salon.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SingleSalonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_salon);
        ImageView salonImage = findViewById(R.id.salon_image);

        Glide.with(salonImage).load(getResources().getDrawable(R.drawable.salon_sample)).centerCrop().into(salonImage);
        RecyclerView recyclerView = findViewById(R.id.service_recycler);

        RelativeLayout book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleSalonActivity.this,SelectTimeBarber.class);
                startActivity(intent);
            }
        });

        ArrayList<ServicesModel> servicesModels = new ArrayList<>();
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.scissors),"Haircut"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.hair_dryer),"Hairdry"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.trimmer),"Trimming"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.scissors),"Haircut"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.hair_dryer),"Hairdry"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.trimmer),"Trimming"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.scissors),"Haircut"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.hair_dryer),"Hairdry"));
        servicesModels.add(new ServicesModel(getResources().getDrawable(R.drawable.trimmer),"Trimming"));

        ServicesAdapter servicesAdapter = new ServicesAdapter(servicesModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(SingleSalonActivity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(servicesAdapter);
    }
}