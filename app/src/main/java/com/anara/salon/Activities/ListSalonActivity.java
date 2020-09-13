package com.anara.salon.Activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Adapters.SalonListAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.ApiResponse.SalonModel;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Dialogs.SortDialog;
import com.anara.salon.Helpers.GpsLocation;
import com.anara.salon.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSalonActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView serviceSort;
    public TextView filterSort;
    public RecyclerView recyclerView;
    
    String serviceId;
    public String sort="";

    public JSONArray validFor;
    public JSONArray Rating;
    public JSONArray priceRange;
    int distancePreference = 10000;

    ArrayList<SalonModel> salonModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salon);

        TextView serviceName = findViewById(R.id.service_name);
        recyclerView = findViewById(R.id.salon_list);
        LinearLayout sortLayout = findViewById(R.id.sort);
        LinearLayout filterLayout = findViewById(R.id.filter);
        ImageView backButton = findViewById(R.id.back_button);
        serviceSort = findViewById(R.id.service_sort);
        filterSort = findViewById(R.id.howMuch);
        sortLayout.setOnClickListener(this);
        filterLayout.setOnClickListener(this);
        backButton.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListSalonActivity.this));

        validFor = new JSONArray();
        Rating = new JSONArray();
        priceRange = new JSONArray();

        Intent intent = getIntent();
        if (intent.getIntExtra("mode",1)==1){
            serviceId = intent.getStringExtra("serviceId");
            serviceName.setText(intent.getStringExtra("service"));
        }else if (intent.getIntExtra("mode",1)==2){
            serviceId = intent.getStringExtra("serviceId");
            serviceName.setText(intent.getStringExtra("service"));
            validFor = new Gson().fromJson(getIntent().getStringExtra("validFor"), JSONArray.class);
            Rating = new Gson().fromJson(getIntent().getStringExtra("Rating"), JSONArray.class);
            priceRange = new Gson().fromJson(getIntent().getStringExtra("priceRange"), JSONArray.class);
            filterSort.setText(validFor.length()+Rating.length()+priceRange.length()+"  Selected");
        }

        getSalons();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.sort){
            SortDialog sortDialog = new SortDialog(ListSalonActivity.this, serviceId);
            sortDialog.show(getSupportFragmentManager(), "Main");
        }else if (view.getId()== R.id.filter){
            Intent intent = new Intent(ListSalonActivity.this,FilterActivity.class);
            intent.putExtra("service_id", serviceId);
            startActivity(intent);
        }else if(view.getId()==R.id.back_button){
            onBackPressed();
        }
    }
    public void getSalons(){
        if (sort.equals("near")) {
            sortByNearByMe();
        } else if (sort.equals("ratings")) {
            sortByRatings();
        } else {
            JSONObject parameters = new JSONObject();
            try {
                parameters.put("service_id", serviceId);
                parameters.put("sort_by", sort);
                parameters.put("valid_for", validFor);
                parameters.put("rating", Rating);
                parameters.put("price_range", priceRange);
                Log.e("-=-=-=-=-=-", "-=-=-=-=-=-" + parameters);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            RequestResponseManager.getSalon(parameters, Const.Get_Salon_Request, response -> {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    salonModels = baseRs.getSaloons();
                    SalonListAdapter salonListAdapter = new SalonListAdapter(ListSalonActivity.this, baseRs.getSaloons());
                    recyclerView.setAdapter(salonListAdapter);
                }
            });
        }
    }

    private void sortByRatings() {
        if (salonModels != null && salonModels.size() > 1) {
            Collections.sort(salonModels, (salonModel, t1) -> salonModel.getRatings().compareTo(t1.getRatings()));
            SalonListAdapter salonListAdapter = new SalonListAdapter(ListSalonActivity.this, salonModels);
            recyclerView.setAdapter(salonListAdapter);
        }
    }

    public void sortByNearByMe() {
        if (salonModels != null && salonModels.size() > 1) {
            Collections.sort(salonModels, (salonModel, t1) -> {
                if (!salonModel.getLatitude().equals("") && !salonModel.getLogitude().equals("") && !t1.getLogitude().equals("") && !t1.getLatitude().equals("")) {
                    Float distance1 = checkNearByMe(Double.parseDouble(salonModel.getLatitude()), Double.parseDouble(salonModel.getLogitude()));
                    Float distance2 = checkNearByMe(Double.parseDouble(t1.getLatitude()), Double.parseDouble(t1.getLogitude()));
                    return distance1.compareTo(distance2);
                }
                return 0;
            });
            Log.e("tag"," = = size  = = " + salonModels.size());
            SalonListAdapter salonListAdapter = new SalonListAdapter(ListSalonActivity.this, salonModels);
            recyclerView.setAdapter(salonListAdapter);
        }
    }

    public float checkNearByMe(double latitude, double longtitude) {
        GpsLocation gps = new GpsLocation(this);
        double centerLatitude = gps.getLatitude();
        double centerLongitude = gps.getLongitude();

        float[] results = new float[1];
        Location.distanceBetween(centerLatitude, centerLongitude, latitude, longtitude, results);
        return results[0];
    }

}