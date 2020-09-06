package com.anara.salon.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.SelectTimeBarber;
import com.anara.salon.Models.TimeModel;
import com.anara.salon.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyViewHolder> {
    ArrayList<TimeModel> timeModels;

    public TimeSlotAdapter(ArrayList<TimeModel> timeModels, SelectTimeBarber selectTimeBarber) {
        this.timeModels = timeModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TimeModel timeModel = timeModels.get(holder.getAdapterPosition());

        try {

            SimpleDateFormat oldSimpleDateFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("hh:mm a");
            Date fromTime = oldSimpleDateFormat.parse(timeModel.getStart_time());
            Date toTime = oldSimpleDateFormat.parse(timeModel.getEnd_time());
            holder.timeFrom.setText(newSimpleDateFormat.format(fromTime));
            holder.timeTo.setText(newSimpleDateFormat.format(toTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return timeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView timeFrom, timeTo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            timeFrom = itemView.findViewById(R.id.time_from);
            timeTo = itemView.findViewById(R.id.time_to);
        }
    }
}
