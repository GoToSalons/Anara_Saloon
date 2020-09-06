package com.anara.salon.ApiResponse;

import com.anara.salon.Models.BarberModel;
import com.anara.salon.Models.TimeModel;
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
