package com.anara.salon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Helpers.PrefManager;
import com.anara.salon.MainActivity;
import com.anara.salon.R;
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email, mobileNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView backButton = findViewById(R.id.back_button);
        RelativeLayout save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        mobileNumber = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        countryCodePicker = findViewById(R.id.cpp);
        countryCodePicker.registerCarrierNumberEditText(mobileNumber);

        Intent intent = getIntent();
        if (intent.getStringExtra("mode").equals("register")){
            name.setClickable(true);
            mobileNumber.setClickable(true);
            email.setClickable(true);
            countryCodePicker.setClickable(true);
        }else {
            PrefManager prefManager = new PrefManager(ProfileActivity.this);
            name.setText(prefManager.getString("customerName","User"));
            mobileNumber.setText(prefManager.getString("customerMobileNumber","Mobile"));
            email.setText(prefManager.getString("customerEmail","Email"));
            name.setEnabled(false);
            mobileNumber.setEnabled(false);
            email.setEnabled(false);
            countryCodePicker.setVisibility(View.GONE);
            save.setVisibility(View.GONE);
        }


        backButton.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.back_button){
            onBackPressed();
        }else if(view.getId()==R.id.save) {
            try {
                JSONObject parameters = new JSONObject();
                parameters.put("name", name.getText().toString());
                parameters.put("email", email.getText().toString());
                parameters.put("mobile", countryCodePicker.getFullNumberWithPlus());
                RequestResponseManager.sendProfileDetails(parameters, Const.Set_Profile_details, new RequestResponseManager.OnResponseListener() {
                    @Override
                    public void onResponse(Object response) {
                        BaseRs baseRs = (BaseRs) response;
                        if (baseRs.getStatus().equals("success")){
                            Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            PrefManager prefManager = new PrefManager(ProfileActivity.this);
                            prefManager.setString("customerEmail",((BaseRs) response).getUserModel().getEmail());
                            prefManager.setString("customerName",((BaseRs) response).getUserModel().getName());
                            prefManager.setString("customerMobileNumber",((BaseRs) response).getUserModel().getMobile());
                            prefManager.setInteger("customerId",((BaseRs) response).getUserModel().getId());
                            prefManager.setLoggedIn(true);
                            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(ProfileActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}