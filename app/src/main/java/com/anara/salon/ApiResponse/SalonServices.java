package com.anara.salon.ApiResponse;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class SalonServices implements Serializable, Parcelable {

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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected SalonServices(Parcel in) {
        main_service_id = in.readInt();
        service_id = in.readString();
        name = in.readString();
        price = in.readString();
        isChecked = in.readBoolean();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(main_service_id);
        dest.writeString(service_id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeBoolean(isChecked);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalonServices> CREATOR = new Creator<SalonServices>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public SalonServices createFromParcel(Parcel in) {
            return new SalonServices(in);
        }

        @Override
        public SalonServices[] newArray(int size) {
            return new SalonServices[size];
        }
    };

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
