package com.anara.salon.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.FilterActivity;
import com.anara.salon.Models.SubItemModel;
import com.anara.salon.R;

import java.util.ArrayList;

public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.MyViewHolder> {

    ArrayList<SubItemModel> subItems;
    FilterActivity filterActivity;
    String mainItem;
    public SubItemAdapter(FilterActivity filterActivity, ArrayList<SubItemModel> subItems, String itemName) {
        this.subItems = subItems;
        this.filterActivity = filterActivity;
        this.mainItem = itemName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_filter_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final SubItemModel subItem = subItems.get(holder.getAdapterPosition());
        holder.subItem.setText(subItem.getName());
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(subItem.isChecked());

        filterActivity.budget = filterActivity.getSharedPreferences("budget", Context.MODE_PRIVATE);
        filterActivity.rating = filterActivity.getSharedPreferences("rating", Context.MODE_PRIVATE);
        filterActivity.validFors = filterActivity.getSharedPreferences("validFor", Context.MODE_PRIVATE);


        switch (mainItem) {
            case "Budget":
                holder.checkBox.setChecked(filterActivity.budget.getBoolean(position+"", false));
                break;
            case "Rating":
                holder.checkBox.setChecked(filterActivity.rating.getBoolean(position+"", false));
                break;
            case "Valid For":
                holder.checkBox.setChecked(filterActivity.validFors.getBoolean(position+"", false));
                break;
        }

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switch (mainItem) {
                case "Budget":
                    if (filterActivity.budget.getBoolean(position+"", false)){
                        filterActivity.budget.edit().putBoolean(position+"",false).apply();
                    }else {
                        filterActivity.budget.edit().putBoolean(position+"",true).apply();
                        filterActivity.priceRange.put(subItem.getName());
                    }
                    break;
                case "Rating":
                    if (filterActivity.rating.getBoolean(position+"", false)) {
                        filterActivity.rating.edit().putBoolean(position + "", false).apply();
                    }else {
                        filterActivity.rating.edit().putBoolean(position + "", true).apply();
                        filterActivity.Rating.put(subItem.getName());
                    }
                    break;
                case "Valid For":
                    if (filterActivity.validFors.getBoolean(position+"", false)) {
                        filterActivity.validFors.edit().putBoolean(position + "", false).apply();
                    }else {
                        filterActivity.validFors.edit().putBoolean(position + "", true).apply();
                        filterActivity.validFor.put(subItem.getName());
                    }
                    break;
            }
            subItem.setChecked(isChecked);
        });

    }

    @Override
    public int getItemCount() {
        return subItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subItem;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subItem = itemView.findViewById(R.id.sub_item_name);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
