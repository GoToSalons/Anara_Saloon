package com.anara.salon.Adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.SelectTimeBarber;
import com.anara.salon.Models.BarberModel;
import com.anara.salon.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class ChooseBarbersAdapter extends RecyclerView.Adapter<ChooseBarbersAdapter.MyViewHolder> {

    ArrayList<BarberModel> barberModels;
    SelectTimeBarber selectTimeBarber;
    int selectedPosition = -1;

    public ChooseBarbersAdapter(ArrayList<BarberModel> barberModels, SelectTimeBarber selectTimeBarber) {
        this.barberModels = barberModels;
        this.selectTimeBarber = selectTimeBarber;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_barber_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BarberModel barberModel = barberModels.get(holder.getAdapterPosition());
        holder.barberName.setText(barberModel.getName());
        Glide.with(holder.barberImage).load(barberModel.getProfile_image()).transform(new CenterCrop(), new RoundedCorners(25))
                .into(holder.barberImage);

        if (selectedPosition == holder.getAdapterPosition()) {
            barberModel.setChecked(true);
        } else {
            barberModel.setChecked(false);
        }

        holder.itemView.setOnClickListener(view -> {

            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();

        });

        if (barberModel.isChecked()) {

            holder.Checked.setBackground(selectTimeBarber.getDrawable(R.drawable.orange_bg));
            selectTimeBarber.barberId = Integer.parseInt(barberModel.getId());
//            selectTimeBarber.getTimeSlots();

        } else {
            holder.Checked.setBackground(selectTimeBarber.getDrawable(R.drawable.dark_grey_bg));
        }

    }

    @Override
    public int getItemCount() {
        return barberModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView barberImage;
        TextView barberName;
        RelativeLayout Checked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barberImage = itemView.findViewById(R.id.barber_image);
            barberName = itemView.findViewById(R.id.barber_name);
            Checked = itemView.findViewById(R.id.checked);
        }
    }
}
