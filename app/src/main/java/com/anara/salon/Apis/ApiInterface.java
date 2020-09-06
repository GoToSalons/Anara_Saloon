package com.anara.salon.Apis;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/fetch-saloons")
    Call<String> getSalonList(@Body String serviceId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/login")
    Call<String> sendMobile(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/saloon-details")
    Call<String> getSalonDetails(@Body String salonId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/customers/register")
    Call<String> setProfileDetails(@Body String json);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/filter-options")
    Call<String> getFilters(@Body String json);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/fetch-saloon-barbers")
    Call<String> getBarbers(@Body String json);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/customers/fetch-time-slot")
    Call<String> getTimeSlots(@Body String json);
}
