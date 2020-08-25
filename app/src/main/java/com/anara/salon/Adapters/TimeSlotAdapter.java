package com.anara.salon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Models.TimeModel;
import com.anara.salon.R;

import java.util.ArrayList;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyViewHolder> {
    ArrayList<TimeModel> timeModels;

    public TimeSlotAdapter(ArrayList<TimeModel> timeModels) {
        this.timeModels = timeModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TimeModel timeModel = timeModels.get(holder.getAdapterPosition());
        if (timeModel.getTime().contains("AM")){
            holder.time.setText(timeModel.getTime().replace(" AM",""));
            holder.amPm.setText("AM");
        }else {
            holder.time.setText(timeModel.getTime().replace(" PM",""));
            holder.amPm.setText("PM");
        }
    }

    @Override
    public int getItemCount() {
        return timeModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time,amPm;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            amPm = itemView.findViewById(R.id.am_pm);
        }
    }
}
