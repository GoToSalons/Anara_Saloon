package com.anara.salon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Models.BarberModel;
import com.anara.salon.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class ChooseBarbersAdapter extends RecyclerView.Adapter<ChooseBarbersAdapter.MyViewHolder> {

    ArrayList<BarberModel> barberModels;

    public ChooseBarbersAdapter(ArrayList<BarberModel> barberModels) {
        this.barberModels = barberModels;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_barber_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BarberModel barberModel = barberModels.get(holder.getAdapterPosition());
        holder.barberName.setText(barberModel.getBarberName());
        Glide.with(holder.barberImage).load(barberModel.getBarberImage()).transform(new CenterCrop(),new RoundedCorners(25))
                .into(holder.barberImage);

    }

    @Override
    public int getItemCount() {
        return barberModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView barberImage;
        TextView barberName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barberImage = itemView.findViewById(R.id.barber_image);
            barberName = itemView.findViewById(R.id.barber_name);
        }
    }
}
