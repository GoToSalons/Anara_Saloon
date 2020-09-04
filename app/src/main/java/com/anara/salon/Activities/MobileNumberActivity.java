package com.anara.salon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.anara.salon.R;
import com.hbb20.CountryCodePicker;

public class MobileNumberActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);
        CountryCodePicker countryCodePicker = findViewById(R.id.cpp);
        EditText phoneNumber = findViewById(R.id.phone_number);
        countryCodePicker.registerCarrierNumberEditText(phoneNumber);

        RelativeLayout next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MobileNumberActivity.this,OTPActivity.class);
                intent.putExtra("mobile", countryCodePicker.getFullNumberWithPlus());
                startActivity(intent);

            }
        });
    }
}