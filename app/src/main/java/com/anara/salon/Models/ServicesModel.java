package com.anara.salon.Models;

import android.graphics.drawable.Drawable;

public class ServicesModel {
    Drawable serviceIcon;
    String serviceName;

    public ServicesModel(Drawable serviceIcon, String serviceName) {
        this.serviceIcon = serviceIcon;
        this.serviceName = serviceName;
    }

    public Drawable getServiceIcon() {
        return serviceIcon;
    }

    public String getServiceName() {
        return serviceName;
    }
}
