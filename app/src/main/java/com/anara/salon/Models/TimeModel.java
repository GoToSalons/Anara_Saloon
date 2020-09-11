package com.anara.salon.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TimeModel implements Serializable {


    String start_time;
    String end_time;
    boolean isChecked;

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
