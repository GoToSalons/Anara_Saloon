package com.anara.salon.ApiResponse;


import java.io.Serializable;

public class SalonServices implements Serializable {

    int main_service_id;
    String service_id;
    String name;
    String price;
    boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public int getMain_service_id() {
        return main_service_id;
    }

    public void setMain_service_id(int main_service_id) {
        this.main_service_id = main_service_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
