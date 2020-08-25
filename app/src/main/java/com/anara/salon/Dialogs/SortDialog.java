package com.anara.salon.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.anara.salon.Activities.ListSalonActivity;
import com.anara.salon.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SortDialog extends DialogFragment implements View.OnClickListener {

    private ListSalonActivity listSalonActivity;

    public SortDialog(ListSalonActivity listSalonActivity) {
        this.listSalonActivity = listSalonActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
            Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.drawable.dialog_bg);
            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL );
        }
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.sort_dialog, container, false);

        RelativeLayout popular = contentView.findViewById(R.id.popular);
        RelativeLayout nearMe = contentView.findViewById(R.id.near_me);
        RelativeLayout ratings = contentView.findViewById(R.id.ratings);
        RelativeLayout priceLowToHigh = contentView.findViewById(R.id.low_high);
        RelativeLayout priceHighToLow = contentView.findViewById(R.id.high_low);
        RelativeLayout Dismiss = contentView.findViewById(R.id.dismiss_l);

        popular.setOnClickListener(this);
        nearMe.setOnClickListener(this);
        ratings.setOnClickListener(this);
        priceLowToHigh.setOnClickListener(this);
        priceHighToLow.setOnClickListener(this);
        Dismiss.setOnClickListener(this);

        return contentView;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.popular) {
            listSalonActivity.serviceSort.setText("Popular");
            dismiss();
        } else if (v.getId() == R.id.near_me){
            listSalonActivity.serviceSort.setText("Near Me");
            dismiss();
        } else if (v.getId() == R.id.ratings){
            listSalonActivity.serviceSort.setText("Ratings");
            dismiss();
        } else if (v.getId() == R.id.low_high){
            listSalonActivity.serviceSort.setText("Price Low to High");
            dismiss();
        } else if (v.getId() == R.id.high_low){
            listSalonActivity.serviceSort.setText("Price High to Low");
            dismiss();
        }else if (v.getId()==R.id.dismiss_l){
            dismiss();
        }
    }
}
