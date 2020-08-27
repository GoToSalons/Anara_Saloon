package com.anara.salon.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SalonModel implements Serializable {

    @SerializedName("saloon_name")
    String salonName;

    @SerializedName("open_time")
    String salonOpeningTime;

    @SerializedName("close_time")
    String salonClosingTime;

    @SerializedName("saloon_type")
    String SalonType;

    @SerializedName("street_address")
    String SalonAddress;

    @SerializedName("image")
    String ImageUrl;

    @SerializedName("price")
    String salonPricing;

    public String getSalonAddress() {
        return SalonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        SalonAddress = salonAddress;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonOpeningTime() {
        return salonOpeningTime;
    }

    public void setSalonOpeningTime(String salonOpeningTime) {
        this.salonOpeningTime = salonOpeningTime;
    }

    public String getSalonClosingTime() {
        return salonClosingTime;
    }

    public void setSalonClosingTime(String salonClosingTime) {
        this.salonClosingTime = salonClosingTime;
    }

    public String getSalonType() {
        return SalonType;
    }

    public void setSalonType(String salonType) {
        SalonType = salonType;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getSalonPricing() {
        return salonPricing;
    }

    public void setSalonPricing(String salonPricing) {
        this.salonPricing = salonPricing;
    }
}
