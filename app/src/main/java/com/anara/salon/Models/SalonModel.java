package com.anara.salon.Models;

import android.graphics.drawable.Drawable;

public class SalonModel {
    Drawable salonImage;
    String salonName;
    String salonAddress;
    String salonRating;
    String salonRatingPerson;
    String salonTiming;
    String salonPricing;

    public SalonModel(Drawable salonImage, String salonName, String salonAddress, String salonRating, String salonRatingPerson, String salonTiming, String salonPricing) {
        this.salonImage = salonImage;
        this.salonName = salonName;
        this.salonAddress = salonAddress;
        this.salonRating = salonRating;
        this.salonRatingPerson = salonRatingPerson;
        this.salonTiming = salonTiming;
        this.salonPricing = salonPricing;
    }

    public Drawable getSalonImage() {
        return salonImage;
    }

    public String getSalonName() {
        return salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public String getSalonRating() {
        return salonRating;
    }

    public String getSalonRatingPerson() {
        return salonRatingPerson;
    }

    public String getSalonTiming() {
        return salonTiming;
    }

    public String getSalonPricing() {
        return salonPricing;
    }
}
