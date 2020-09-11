package com.anara.salon.ApiResponse;

import com.anara.salon.Models.BarberModel;
import com.anara.salon.Models.BookingModel;
import com.anara.salon.Models.SubItemModel;
import com.anara.salon.Models.TimeModel;
import com.anara.salon.Models.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseRs {

    @SerializedName("status")
    public String status;

    @SerializedName("login")
    public String login;

    @SerializedName("message")
    public String message;

    @SerializedName("saloons")
    public ArrayList<SalonModel> saloons;

    @SerializedName("saloon")
    public SalonModel salonModel;

    @SerializedName("saloon_services")
    ArrayList<SalonServices> saloon_services;

    @SerializedName("saloon_gallery")
    public ArrayList<String> saloon_gallery;

    @SerializedName("barbers")
    public ArrayList<BarberModel> barbers;

    @SerializedName("available_time")
    public ArrayList<TimeModel> timeSlots;

    @SerializedName("user")
    public UserModel userModel;

    @SerializedName("booking_list")
    public ArrayList<BookingModel> bookings;

    @SerializedName("price_range")
    public ArrayList<SubItemModel> priceFilters;

    @SerializedName("rating")
    public ArrayList<SubItemModel> ratingFilters;

    @SerializedName("valid_for")
    public ArrayList<SubItemModel> validForFilters;


    public ArrayList<SubItemModel> getPriceFilters() {
        return priceFilters;
    }

    public void setPriceFilters(ArrayList<SubItemModel> priceFilters) {
        this.priceFilters = priceFilters;
    }

    public ArrayList<SubItemModel> getRatingFilters() {
        return ratingFilters;
    }

    public void setRatingFilters(ArrayList<SubItemModel> ratingFilters) {
        this.ratingFilters = ratingFilters;
    }

    public ArrayList<SubItemModel> getValidForFilters() {
        return validForFilters;
    }

    public void setValidForFilters(ArrayList<SubItemModel> validForFilters) {
        this.validForFilters = validForFilters;
    }

    public ArrayList<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public ArrayList<TimeModel> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeModel> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public ArrayList<BarberModel> getBarbers() {
        return barbers;
    }

    public void setBarbers(ArrayList<BarberModel> barbers) {
        this.barbers = barbers;
    }

    public ArrayList<SalonServices> getSaloon_services() {
        return saloon_services;
    }

    public void setSaloon_services(ArrayList<SalonServices> saloon_services) {
        this.saloon_services = saloon_services;
    }

    public ArrayList<String> getSaloon_gallery() {
        return saloon_gallery;
    }

    public void setSaloon_gallery(ArrayList<String> saloon_gallery) {
        this.saloon_gallery = saloon_gallery;
    }

    public SalonModel getSalonModel() {
        return salonModel;
    }

    public void setSalonModel(SalonModel salonModel) {
        this.salonModel = salonModel;
    }

    public ArrayList<SalonModel> getSaloons() {
        return saloons;
    }

    public void setSaloons(ArrayList<SalonModel> saloons) {
        this.saloons = saloons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
