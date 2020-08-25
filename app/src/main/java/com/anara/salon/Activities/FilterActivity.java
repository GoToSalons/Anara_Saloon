package com.anara.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.anara.salon.Adapters.MainItemAdapter;
import com.anara.salon.Models.MainItemModel;
import com.anara.salon.Models.SubItemModel;
import com.anara.salon.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        RecyclerView MainItems = findViewById(R.id.main_items);
        RecyclerView SubItems = findViewById(R.id.sub_items);

        ArrayList<MainItemModel> arrayList = new ArrayList<>();
        arrayList.add(new MainItemModel("Option", new ArrayList<>(Arrays.asList(new SubItemModel("Hair"), new SubItemModel("Beard"), new SubItemModel("Nails"), new SubItemModel("Waxing")))));
        arrayList.add(new MainItemModel("Budget", new ArrayList<>(Arrays.asList(new SubItemModel("0-500"), new SubItemModel("500-1000"), new SubItemModel("1000-2000"), new SubItemModel("2000-5000")))));
        arrayList.add(new MainItemModel("Rating", new ArrayList<>(Arrays.asList(new SubItemModel("3.0"), new SubItemModel("4.0"), new SubItemModel("5.0")))));
        arrayList.add(new MainItemModel("Valid For", new ArrayList<>(Arrays.asList(new SubItemModel("Male"), new SubItemModel("Female"), new SubItemModel("Unisex")))));


        MainItemAdapter mainItemAdapter = new MainItemAdapter(arrayList,SubItems,FilterActivity.this);
        MainItems.setLayoutManager(new LinearLayoutManager(FilterActivity.this));
        MainItems.setAdapter(mainItemAdapter);


    }
}