package com.anara.salon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.MainActivity;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email, mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView backButton = findViewById(R.id.back_button);
        RelativeLayout save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        mobileNumber = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);

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
                parameters.put("mobile", mobileNumber.getText().toString());
                RequestResponseManager.sendProfileDetails(parameters, Const.Set_Profile_details, new RequestResponseManager.OnResponseListener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}