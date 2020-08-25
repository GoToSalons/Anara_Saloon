package com.anara.salon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Models.BookingModel;
import com.anara.salon.R;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    ArrayList<BookingModel> bookingModels;

    public BookingAdapter(ArrayList<BookingModel> bookingModels) {
        this.bookingModels = bookingModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookingModel bookingModel = bookingModels.get(holder.getAdapterPosition());
        holder.name.setText(bookingModel.getSalonName());
        holder.date.setText(bookingModel.getDate().replace(" ","\n"));
        holder.service.setText(bookingModel.getServiceName());
        holder.time.setText(bookingModel.getTime());
        holder.completed.setText(bookingModel.getCompleted());
    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,time,service,name,completed;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            service = itemView.findViewById(R.id.service_name);
            name = itemView.findViewById(R.id.salon_name);
            completed = itemView.findViewById(R.id.completed);
        }
    }
}
