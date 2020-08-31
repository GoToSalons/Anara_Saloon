package com.anara.salon.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.ApiResponse.SalonServices;
import com.anara.salon.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    ArrayList<SalonServices> servicesModels;

    public ServicesAdapter(ArrayList<SalonServices> servicesModels) {
        this.servicesModels = servicesModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SalonServices servicesModel = servicesModels.get(holder.getAdapterPosition());

        holder.serviceName.setText(servicesModel.getName());

        holder.service_price.setText("₹ " + servicesModel.getPrice());

        Glide.with(holder.serviceIcon).load(R.drawable.scissors).into(holder.serviceIcon);

    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView serviceIcon;
        TextView serviceName, service_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceIcon = itemView.findViewById(R.id.service_icon);
            serviceName = itemView.findViewById(R.id.service_name);
            service_price = itemView.findViewById(R.id.service_price);
        }
    }
}
