package com.anara.salon.Adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
    SelectTimeBarber selectTimeBarber;
    int selectedPosition = -1;

    public TimeSlotAdapter(ArrayList<TimeModel> timeModels, SelectTimeBarber selectTimeBarber) {
        this.timeModels = timeModels;
        this.selectTimeBarber = selectTimeBarber;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        if (selectedPosition == holder.getAdapterPosition()) {
            timeModel.setChecked(true);
        } else {
            timeModel.setChecked(false);
        }

        holder.itemView.setOnClickListener(view -> {

            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();

        });

        if (timeModel.isChecked()) {

            holder.Checked.setBackground(selectTimeBarber.getDrawable(R.drawable.orange_bg));
            selectTimeBarber.startTime = timeModel.getStart_time();
            selectTimeBarber.endTime = timeModel.getEnd_time();

        } else {
            holder.Checked.setBackground(selectTimeBarber.getDrawable(R.drawable.dark_grey_bg));
        }
    }

    @Override
    public int getItemCount() {
        return timeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView timeFrom, timeTo;
        RelativeLayout Checked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            timeFrom = itemView.findViewById(R.id.time_from);
            timeTo = itemView.findViewById(R.id.time_to);
            Checked = itemView.findViewById(R.id.checked);
        }
    }
}
