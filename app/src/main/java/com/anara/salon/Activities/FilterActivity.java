package com.anara.salon.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Adapters.MainItemAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Models.MainItemModel;
import com.anara.salon.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {


    public JSONArray validFor;
    public JSONArray Rating;
    public JSONArray priceRange;


    public SharedPreferences budget;
    public SharedPreferences rating;
    public SharedPreferences validFors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        RecyclerView MainItems = findViewById(R.id.main_items);
        RecyclerView SubItems = findViewById(R.id.sub_items);
        RelativeLayout Apply = findViewById(R.id.apply);
        RelativeLayout Reset = findViewById(R.id.reset);

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("service_id", getIntent().getStringExtra("service_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilterActivity.this,ListSalonActivity.class);
                intent.putExtra("validFor", new Gson().toJson(validFor));
                intent.putExtra("Rating", new Gson().toJson(Rating));
                intent.putExtra("priceRange", new Gson().toJson(priceRange));
                intent.putExtra("serviceId",  getIntent().getStringExtra("service_id"));
                intent.putExtra("service",  getIntent().getStringExtra("service"));
                intent.putExtra("mode", 2);
                startActivity(intent);
                finish();
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilterActivity.this,ListSalonActivity.class);
                intent.putExtra("mode", 1);
                intent.putExtra("serviceId",  getIntent().getStringExtra("service_id"));
                intent.putExtra("service",  getIntent().getStringExtra("service"));
                budget.edit().clear().apply();
                rating.edit().clear().apply();
                validFors.edit().clear().apply();
                startActivity(intent);
                finish();
            }
        });

        validFor=new JSONArray();
        Rating=new JSONArray();
        priceRange=new JSONArray();



        RequestResponseManager.getFilters(parameters, Const.Get_Filters, new RequestResponseManager.OnResponseListener() {
            @Override
            public void onResponse(Object response) {
                BaseRs baseRs = (BaseRs) response;
                ArrayList<MainItemModel> arrayList = new ArrayList<>();
                arrayList.add(new MainItemModel("Budget", baseRs.getPriceFilters()));
                arrayList.add(new MainItemModel("Rating", baseRs.getRatingFilters()));
                arrayList.add(new MainItemModel("Valid For", baseRs.getValidForFilters()));
                MainItemAdapter mainItemAdapter = new MainItemAdapter(arrayList, SubItems, FilterActivity.this);
                MainItems.setLayoutManager(new LinearLayoutManager(FilterActivity.this));
                MainItems.setAdapter(mainItemAdapter);
            }
        });
    }
}