package com.anara.salon.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.SingleSalonActivity;
import com.anara.salon.ApiResponse.SalonServices;
import com.anara.salon.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    ArrayList<SalonServices> servicesModels;
    SingleSalonActivity singleSalonActivity;
    public ServicesAdapter(ArrayList<SalonServices> servicesModels, SingleSalonActivity singleSalonActivity) {
        this.servicesModels = servicesModels;
        this.singleSalonActivity = singleSalonActivity;
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
        double res = (Double.parseDouble(servicesModel.getPrice()) / 100.0f) * 10;
        holder.service_price.setText("₹ " + String.format("%.2f", (Double.parseDouble(servicesModel.getPrice()) + res)));

        switch (servicesModel.getMain_service_id()) {
            case 1:
                Glide.with(holder.serviceIcon).load(R.drawable.scissors).into(holder.serviceIcon);
                break;
            case 2:
                Glide.with(holder.serviceIcon).load(R.drawable.trimmer).into(holder.serviceIcon);
                break;
            case 3:
                Glide.with(holder.serviceIcon).load(R.drawable.lip_stick).into(holder.serviceIcon);
                break;
            default:
                Glide.with(holder.serviceIcon).load(R.drawable.hair_dryer).into(holder.serviceIcon);
                break;
        }
        holder.itemView.setOnClickListener(view -> {
            if (servicesModel.isChecked()) {
                servicesModel.setChecked(false);
                holder.Checked.setVisibility(View.INVISIBLE);
                singleSalonActivity.checkedItems.remove(servicesModel);
            } else {
                servicesModel.setChecked(true);
                holder.Checked.setVisibility(View.VISIBLE);
                singleSalonActivity.checkedItems.add(servicesModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView serviceIcon;
        TextView serviceName, service_price;
        RelativeLayout Checked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceIcon = itemView.findViewById(R.id.service_icon);
            serviceName = itemView.findViewById(R.id.service_name);
            service_price = itemView.findViewById(R.id.service_price);
            Checked = itemView.findViewById(R.id.checked_layout);
        }
    }
}
