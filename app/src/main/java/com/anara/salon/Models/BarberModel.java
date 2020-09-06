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
    boolean isChecked;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected BarberModel(Parcel in) {
        id = in.readString();
        profile_image = in.readString();
        name = in.readString();
        isChecked = in.readBoolean();
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
        parcel.writeBoolean(isChecked);
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
}
