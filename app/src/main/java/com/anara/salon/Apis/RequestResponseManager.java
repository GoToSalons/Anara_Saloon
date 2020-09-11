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
                    Log.e("tag", " = =  = call response = = = Register" + response.body());
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
                    Log.e("tag", " = =  = call response = = = Mobile" + response.body());

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

    public static void getBarbers(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.getBarbers(parameters.toString());
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

    public static void getTimeSlots(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.getTimeSlots(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response time = = = " + response.body());
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

    public static void orderSuccess(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.orderSuccess(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response order = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error order = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }
    }

    public static void getBookings(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.getBookings(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response Bookings = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error order = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }
    }

    public static void Rate(JSONObject parameters, int requestCode, OnResponseListener onResponseListener){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.Rate(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response Ratings = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error order = = = " + t.getMessage());
                    onResponseListener.onResponse(null);
                }
            });
        } catch (Exception e) {
            Log.e("============", "=========" + e.getMessage());
        }
    }
    public static void CancelBooking(JSONObject parameters, int requestCode, OnResponseListener onResponseListener){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<String> call = apiInterface.CancelBooking(parameters.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    Log.e("tag", " = =  = call response Ratings = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    Log.e("tag", " = =  = call error order = = = " + t.getMessage());
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
        } else if (requestType == Const.Get_Barbers) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Get_Time_Slots) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.orderSuccess) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.getBookings){
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Rate){
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Get_Filters){
            return Parser.getHomePageResponse(response);
        }else if (requestType == Const.Cancel){
            return Parser.getHomePageResponse(response);
        }
        return null;
    }

    public interface OnResponseListener {
        void onResponse(Object response);
    }

}
