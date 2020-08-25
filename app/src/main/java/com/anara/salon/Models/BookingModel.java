package com.anara.salon.Models;

public class BookingModel {

    String Date;
    String Time;
    String SalonName;
    String ServiceName;
    String Completed;

    public BookingModel(String date, String time, String salonName, String serviceName, String completed) {
        Date = date;
        Time = time;
        SalonName = salonName;
        ServiceName = serviceName;
        Completed = completed;
    }

    public String getCompleted() {
        return Completed;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getSalonName() {
        return SalonName;
    }

    public String getServiceName() {
        return ServiceName;
    }
}
