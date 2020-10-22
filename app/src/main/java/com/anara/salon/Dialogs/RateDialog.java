package com.anara.salon.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.anara.salon.Activities.BookingsActivity;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Helpers.PrefManager;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RateDialog extends DialogFragment {

    Integer barberId;
    BookingsActivity bookingsActivity;
    public RateDialog(BookingsActivity activity, Integer barber_id) {
        this.bookingsActivity = activity;
        this.barberId = barber_id;

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
            dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.rate_dialog, container, false);
        RelativeLayout rate = contentView.findViewById(R.id.rate);
        RatingBar ratingBar = contentView.findViewById(R.id.rating_bar);
        PrefManager prefManager = new PrefManager(bookingsActivity);

        RelativeLayout relativeLayout = contentView.findViewById(R.id.dismiss_l);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("customer_id", prefManager.getInteger("customerId", -1));
                    jsonObject.put("barber_id", barberId);
                    jsonObject.put("rating", Math.round(ratingBar.getRating()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestResponseManager.Rate(jsonObject, Const.Rate, response -> {
                    BaseRs baseRs = (BaseRs) response;
                    if (baseRs.status.equals("success")){
                        Toast.makeText(bookingsActivity, "Thank U", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }else {
                        Toast.makeText(bookingsActivity, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return contentView;
    }
}
