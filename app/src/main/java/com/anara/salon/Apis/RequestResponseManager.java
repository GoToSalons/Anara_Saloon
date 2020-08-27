package com.anara.salon.Apis;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.ListSalonActivity;
import com.anara.salon.Activities.OTPActivity;
import com.anara.salon.Adapters.SalonListAdapter;
import com.anara.salon.Models.SalonModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestResponseManager {

    public static void sendMobile(String mobileNumber, OTPActivity otpActivity) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            JSONObject parameters = new JSONObject();
            try {
                parameters.put("mobile", mobileNumber);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Call<String> call = apiInterface.sendMobile(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull retrofit2.Response<String> response) {
                    Log.e("Retrofit", "Retrofit Response" + response.toString());
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("Retrofit", "Retrofit Error" + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }

    }

    public static void getSalon(ListSalonActivity listSalonActivity, RecyclerView recyclerView) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("service_id", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<String> call = apiInterface.getSalonList(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    JSONObject jObject = new JSONObject(response.body());
                    Log.e("=========", "==========" + response.body());
                    ArrayList<SalonModel> salonList = new Gson().fromJson(jObject.getString("saloons"), new TypeToken<List<SalonModel>>() {
                    }.getType());
                    SalonListAdapter salonListAdapter = new SalonListAdapter(listSalonActivity, salonList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(listSalonActivity));
                    recyclerView.setAdapter(salonListAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
