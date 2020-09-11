package com.anara.salon.ApiResponse;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SalonModel implements Serializable, Parcelable {

    String saloon_id;
    String saloon_name;
    String open_time;
    String close_time;
    String saloon_type;
    String street_address;
    String image;
    String price;
    String contact_no;
    String owner_image;
    String instagram;
    String facebook;
    String twitter;
    String latitude;
    String logitude;
    Float ratings;

    protected SalonModel(Parcel in) {
        saloon_id = in.readString();
        saloon_name = in.readString();
        open_time = in.readString();
        close_time = in.readString();
        saloon_type = in.readString();
        street_address = in.readString();
        image = in.readString();
        price = in.readString();
        contact_no = in.readString();
        owner_image = in.readString();
        instagram = in.readString();
        facebook = in.readString();
        twitter = in.readString();
        latitude = in.readString();
        logitude = in.readString();
        ratings = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(saloon_id);
        dest.writeString(saloon_name);
        dest.writeString(open_time);
        dest.writeString(close_time);
        dest.writeString(saloon_type);
        dest.writeString(street_address);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(contact_no);
        dest.writeString(owner_image);
        dest.writeString(instagram);
        dest.writeString(facebook);
        dest.writeString(twitter);
        dest.writeString(latitude);
        dest.writeString(logitude);
        dest.writeFloat(ratings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalonModel> CREATOR = new Creator<SalonModel>() {
        @Override
        public SalonModel createFromParcel(Parcel in) {
            return new SalonModel(in);
        }

        @Override
        public SalonModel[] newArray(int size) {
            return new SalonModel[size];
        }
    };

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getOwner_image() {
        return owner_image;
    }

    public void setOwner_image(String owner_image) {
        this.owner_image = owner_image;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getSaloon_id() {
        return saloon_id;
    }

    public void setSaloon_id(String saloon_id) {
        this.saloon_id = saloon_id;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getSaloon_name() {
        return saloon_name;
    }

    public void setSaloon_name(String saloon_name) {
        this.saloon_name = saloon_name;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getSaloon_type() {
        return saloon_type;
    }

    public void setSaloon_type(String saloon_type) {
        this.saloon_type = saloon_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Float getRatings() {
        return ratings;
    }

    public void setRatings(Float ratings) {
        this.ratings = ratings;
    }
}
