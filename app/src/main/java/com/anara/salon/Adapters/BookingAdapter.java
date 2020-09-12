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
import com.anara.salon.Models.BookingModel;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    ArrayList<BookingModel> bookingModels;
    BookingsActivity activity;
    public BookingAdapter(BookingsActivity activity, ArrayList<BookingModel> bookingModels) {
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
        holder.service.setText(bookingModel.getSaloon_name());
        holder.date.setText(bookingModel.getBook_date());
        holder.time.setText(bookingModel.getFrom_time());
        StringBuilder s= new StringBuilder();
        for (int i=0;i<bookingModel.getBookingServices().size();i++){
            s.append(" ").append(bookingModel.getBookingServices().get(i).getService_name());
            holder.name.setText(s);
        }
        holder.RateCancel.setOnClickListener(view -> {
            if (bookingModel.getStatus().equals("Completed")){
                RateDialog rateDialog = new RateDialog(activity,bookingModel.getBarber_id());
                rateDialog.show(activity.getSupportFragmentManager(),"rate");
            }else {
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
        holder.completed.setText(bookingModel.getStatus());
        if (bookingModel.getStatus().equals("Completed")){
            holder.RateCancel.setText("Rate");
        }else {
            holder.RateCancel.setText("Cancel");
        }

    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,time,service,name,completed,RateCancel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            service = itemView.findViewById(R.id.service_name);
            name = itemView.findViewById(R.id.salon_name);
            completed = itemView.findViewById(R.id.completed);
            RateCancel = itemView.findViewById(R.id.t1);
        }
    }
}
