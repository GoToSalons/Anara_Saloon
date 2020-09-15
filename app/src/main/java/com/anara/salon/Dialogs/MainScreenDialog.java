package com.anara.salon.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.anara.salon.Activities.BookingsActivity;
import com.anara.salon.Activities.ContactActivity;
import com.anara.salon.Activities.IntroActivity;
import com.anara.salon.Activities.MobileNumberActivity;
import com.anara.salon.Activities.PrivacyActivity;
import com.anara.salon.Activities.ProfileActivity;
import com.anara.salon.Helpers.PrefManager;
import com.anara.salon.MainActivity;
import com.anara.salon.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MainScreenDialog extends DialogFragment implements View.OnClickListener {

    private MainActivity mainActivity;

    public MainScreenDialog(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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
        View contentView = inflater.inflate(R.layout.main_screen_dialog, container, false);

        RelativeLayout profile = contentView.findViewById(R.id.profile_layout);
        RelativeLayout appointments = contentView.findViewById(R.id.appointments);
        RelativeLayout dismiss = contentView.findViewById(R.id.dismiss_l);
        RelativeLayout privacy = contentView.findViewById(R.id.privacy);
        RelativeLayout customer = contentView.findViewById(R.id.customer);
        RelativeLayout logOut = contentView.findViewById(R.id.logout);
        appointments.setOnClickListener(this);
        profile.setOnClickListener(this);
        dismiss.setOnClickListener(this);
        privacy.setOnClickListener(this);
        logOut.setOnClickListener(this);
        customer.setOnClickListener(this);

        return contentView;
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.profile_layout){
            Intent intent = new Intent(mainActivity, ProfileActivity.class);
            intent.putExtra("mode","view");
            startActivity(intent);
            dismiss();
        }else if (v.getId()==R.id.appointments){
            Intent intent = new Intent(mainActivity, BookingsActivity.class);
            startActivity(intent);
            dismiss();
        }else if (v.getId()==R.id.dismiss_l){
            dismiss();
        }else if (v.getId()==R.id.privacy){
            Intent intent = new Intent(mainActivity, PrivacyActivity.class);
            startActivity(intent);
            dismiss();
        }else if (v.getId()==R.id.customer){
            Intent intent = new Intent(mainActivity, ContactActivity.class);
            startActivity(intent);
            dismiss();
        }
        else if (v.getId()==R.id.logout){
            PrefManager prefManager = new PrefManager(mainActivity);
            prefManager.setLoggedIn(false);
            Intent intent = new Intent(mainActivity, IntroActivity.class);
            startActivity(intent);
            dismiss();
        }

    }

}
