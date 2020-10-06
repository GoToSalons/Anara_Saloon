package com.anara.salon.Models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class BarberModel implements Serializable, Parcelable {

    public static final Creator<BarberModel> CREATOR = new Creator<BarberModel>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public BarberModel createFromParcel(Parcel in) {
            return new BarberModel(in);
        }

        @Override
        public BarberModel[] newArray(int size) {
            return new BarberModel[size];
        }
    };
    String id;
    String profile_image;
    String name;
    String mobile;
    boolean isChecked;
    int exp_year;
    int exp_month;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected BarberModel(Parcel in) {
        id = in.readString();
        profile_image = in.readString();
        name = in.readString();
        mobile = in.readString();
        isChecked = in.readBoolean();
        exp_year = in.readInt();
        exp_month = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(profile_image);
        parcel.writeString(name);
        parcel.writeString(mobile);
        parcel.writeBoolean(isChecked);
        parcel.writeInt(exp_year);
        parcel.writeInt(exp_month);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getExp_year() {
        return exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    public int getExp_month() {
        return exp_month;
    }

    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
