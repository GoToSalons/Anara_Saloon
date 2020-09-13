package com.anara.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.anara.salon.R;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutPage = new AboutPage(this)

                .setDescription(getString(R.string.privacy))
                .addGroup("Connect with us")
                .addEmail("gotosalons@gmail.com")
                .create();

        setContentView(aboutPage);
    }


}