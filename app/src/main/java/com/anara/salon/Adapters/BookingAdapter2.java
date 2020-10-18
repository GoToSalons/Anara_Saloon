package com.anara.salon.Adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.BookingsActivity;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Dialogs.RateDialog;
import com.anara.salon.MainActivity;
import com.anara.salon.Models.BookingModel;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingAdapter2 extends RecyclerView.Adapter<BookingAdapter2.MyViewHolder> {

    ArrayList<BookingModel> bookingModels;
    MainActivity activity;
    public BookingAdapter2(MainActivity activity, ArrayList<BookingModel> bookingModels) {
        this.bookingModels = bookingModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookingModel bookingModel = bookingModels.get(holder.getAdapterPosition());
        holder.service.setText(bookingModel.getSalon_name());
        holder.date.setText(bookingModel.getBook_date());
        holder.time.setText(bookingModel.getFrom_time());
        StringBuilder s= new StringBuilder();
        for (int i=0;i<bookingModel.getBookingServices().size();i++){
            s.append(" | ").append(bookingModel.getBookingServices().get(i).getService_name());
            holder.name.setText(s);
        }
        holder.RateCancel.setOnClickListener(view -> {
            if (!bookingModel.getStatus().equals("Completed")){
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("booking_id",bookingModel.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestResponseManager.CancelBooking(jsonObject, Const.Cancel, response -> {
                    bookingModels.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                });
            }
        });

        if (bookingModel.getStatus().equals("Completed")){
            holder.itemView.setVisibility(View.GONE);
//            bookingModels.remove(holder.getAdapterPosition());

        }else if (bookingModel.getStatus().equals("Cancelled")){

//            bookingModels.remove(holder.getAdapterPosition());
        }

    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,time,service,name,RateCancel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            service = itemView.findViewById(R.id.service_name);
            name = itemView.findViewById(R.id.salon_name);
            RateCancel = itemView.findViewById(R.id.t1);
        }
    }
}
