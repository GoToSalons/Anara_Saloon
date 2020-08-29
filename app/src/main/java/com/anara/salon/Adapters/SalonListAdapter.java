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
import com.bumptech.glide.Glide;

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
        Glide.with(holder.salonImage).load(salonModel.getImageUrl()).centerCrop().into(holder.salonImage);
        holder.salonName.setText(salonModel.getSalonName());
        holder.salonAddress.setText(salonModel.getSalonAddress());
        holder.salonTiming.setText(salonModel.getSalonOpeningTime() + " : " + salonModel.getSalonClosingTime());
        holder.salonPricing.setText("Starts at ₹" + salonModel.getSalonPricing() + " for 1 person");
        holder.salonType.setText(salonModel.getSalonType());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listSalonActivity, SingleSalonActivity.class);
                intent.putExtra("salonId", salonModel.getSalonId());
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
        TextView salonName, salonAddress, salonTiming, salonPricing, salonRating, salonRatingPerson, salonType;
        RelativeLayout view;
        RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            salonImage = itemView.findViewById(R.id.salon_image);
            salonName = itemView.findViewById(R.id.salon_name);
            salonAddress = itemView.findViewById(R.id.address);
            salonTiming = itemView.findViewById(R.id.time);
            salonPricing = itemView.findViewById(R.id.pricing);
            salonType = itemView.findViewById(R.id.type);
            view = itemView.findViewById(R.id.view);

        }
    }
}
