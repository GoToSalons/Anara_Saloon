package com.anara.salon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Models.ServicesModel;
import com.anara.salon.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    ArrayList<ServicesModel> servicesModels;

    public ServicesAdapter(ArrayList<ServicesModel> servicesModels) {
        this.servicesModels = servicesModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ServicesModel servicesModel = servicesModels.get(holder.getAdapterPosition());
        holder.serviceName.setText(servicesModel.getServiceName());
        Glide.with(holder.serviceIcon).load(servicesModel.getServiceIcon()).into(holder.serviceIcon);

    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceIcon;
        TextView serviceName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceIcon = itemView.findViewById(R.id.service_icon);
            serviceName = itemView.findViewById(R.id.service_name);
        }
    }
}
