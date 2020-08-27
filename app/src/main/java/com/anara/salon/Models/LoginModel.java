package com.anara.salon.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel implements Serializable {
    @SerializedName("mobile")
    String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
