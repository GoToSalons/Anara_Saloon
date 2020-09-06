package com.anara.salon.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TimeModel implements Serializable, Parcelable {

    public static final Creator<TimeModel> CREATOR = new Creator<TimeModel>() {
        @Override
        public TimeModel createFromParcel(Parcel in) {
            return new TimeModel(in);
        }

        @Override
        public TimeModel[] newArray(int size) {
            return new TimeModel[size];
        }
    };
    String start_time;
    String end_time;

    protected TimeModel(Parcel in) {
        start_time = in.readString();
        end_time = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(start_time);
        parcel.writeString(end_time);
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
