package com.anara.salon.Models;

import java.util.ArrayList;

public class BookingModel {

    Integer id;
    String book_date;
    String saloon_name;
    String from_time;
    String to_time;
    String total_price;
    ArrayList<BookingServices> services;
    String status;
    Integer barber_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<BookingServices> getBookingServices() {
        return services;
    }

    public void setBookingServices(ArrayList<BookingServices> services) {
        this.services = services;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public String getSaloon_name() {
        return saloon_name;
    }

    public void setSaloon_name(String saloon_name) {
        this.saloon_name = saloon_name;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(Integer barber_id) {
        this.barber_id = barber_id;
    }
}
