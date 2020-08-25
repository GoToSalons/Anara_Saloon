package com.anara.salon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.FilterActivity;
import com.anara.salon.Models.MainItemModel;
import com.anara.salon.R;

import java.util.ArrayList;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MyViewHolder> {
    ArrayList<MainItemModel> arrayList;
    RecyclerView subItems;
    FilterActivity filterActivity;
    public MainItemAdapter(ArrayList<MainItemModel> arrayList, RecyclerView subItems, FilterActivity filterActivity) {
        this.arrayList = arrayList;
        this.subItems = subItems;
        this.filterActivity = filterActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_filter_item,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MainItemModel model = arrayList.get(holder.getAdapterPosition());
        holder.MainItemName.setText(model.getItemName());

        SubItemAdapter subItemAdapter = new SubItemAdapter(filterActivity,arrayList.get(0).getSubItems());
        subItems.setLayoutManager(new LinearLayoutManager(filterActivity));
        subItems.setAdapter(subItemAdapter);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubItemAdapter subItemAdapter = new SubItemAdapter(filterActivity, model.getSubItems());
                subItems.setLayoutManager(new LinearLayoutManager(filterActivity));
                subItems.setAdapter(subItemAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView MainItemName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            MainItemName = itemView.findViewById(R.id.main_item_name);
        }
    }
}
