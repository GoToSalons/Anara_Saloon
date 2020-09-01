package com.anara.salon.Apis;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestResponseManager {

    public static void sendProfileDetails(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


            Log.e("tag", " = = = = =  = " + parameters.toString());
            Call<String> call = apiInterface.setProfileDetails(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull retrofit2.Response<String> response) {
                    Log.e("tag", " = =  = call response = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }
    }

    public static void sendMobile(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


            Log.e("tag", " = = = = =  = " + parameters.toString());
            Call<String> call = apiInterface.sendMobile(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull retrofit2.Response<String> response) {
                    Log.e("tag", " = =  = call response = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }

    }

    public static void getSalon(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<String> call = apiInterface.getSalonList(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                Log.e("tag", " = =  = call response = = = " + response.body());
                Object object = invokeParser(response.body(), requestCode);
                onResponseListener.onResponse(object);

            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                Log.e("tag", " = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);
            }
        });
    }

    public static void getSalonDetails(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.getSalonDetails(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }
    }

    public static void getFilters(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.getFilters(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }
    }

    public static Object invokeParser(String response, int requestType) {
        if (requestType == Const.Customer_Login_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Get_Salon_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Get_Salon_Details_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Set_Profile_details) {
            return Parser.getHomePageResponse(response);
        }
        return null;
    }

    public interface OnResponseListener {
        void onResponse(Object response);
    }

}
