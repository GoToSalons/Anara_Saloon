package com.anara.salon.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.ListSalonActivity;
import com.anara.salon.Activities.SingleSalonActivity;
import com.anara.salon.Models.SalonModel;
import com.anara.salon.R;


import java.util.ArrayList;

public class SalonListAdapter extends RecyclerView.Adapter<SalonListAdapter.MyViewHolder> {
    ArrayList<SalonModel> salonModels;
    ListSalonActivity listSalonActivity;
    public SalonListAdapter(ListSalonActivity listSalonActivity, ArrayList<SalonModel> salonModels) {
        this.salonModels = salonModels;
        this.listSalonActivity = listSalonActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.salon_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SalonModel salonModel = salonModels.get(holder.getAdapterPosition());
        holder.salonImage.setImageDrawable(salonModel.getSalonImage());
        holder.salonName.setText(salonModel.getSalonName());
        holder.salonAddress.setText(salonModel.getSalonAddress());
        holder.salonTiming.setText(salonModel.getSalonTiming());
        holder.salonPricing.setText(salonModel.getSalonPricing());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listSalonActivity, SingleSalonActivity.class);
                listSalonActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return salonModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView salonImage;
        TextView salonName,salonAddress,salonTiming,salonPricing,salonRating,salonRatingPerson;
        RelativeLayout view;
        RatingBar ratingBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            salonImage = itemView.findViewById(R.id.salon_image);
            salonName = itemView.findViewById(R.id.salon_name);
            salonAddress = itemView.findViewById(R.id.address);
            salonTiming = itemView.findViewById(R.id.time);
            salonPricing = itemView.findViewById(R.id.pricing);
            view = itemView.findViewById(R.id.view);

        }
    }
}
