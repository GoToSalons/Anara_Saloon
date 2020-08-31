package com.anara.salon.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Adapters.ServicesAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.ApiResponse.SalonModel;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.R;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class SingleSalonActivity extends AppCompatActivity implements View.OnClickListener {

    // service list
    RecyclerView recyclerView;

    // salon image
    ImageView salonImage;

    // salon ratings
    RatingBar rating;

    // salon data
    SalonModel salonModel;

    TextView salon_name, rating_text, rating_text_person, address, time, salon_type, mobile_number;
    ImageView instagram, facebook, twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_salon);

        salonImage = findViewById(R.id.salon_image);
        recyclerView = findViewById(R.id.service_recycler);

        salon_name = findViewById(R.id.salon_name);
        rating = findViewById(R.id.rating);
        rating_text = findViewById(R.id.rating_text);
        rating_text_person = findViewById(R.id.rating_text_person);
        address = findViewById(R.id.address);
        time = findViewById(R.id.time);
        salon_type = findViewById(R.id.salon_type);
        mobile_number = findViewById(R.id.mobile_number);

        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);


        instagram.setOnClickListener(this);
        facebook.setOnClickListener(this);
        twitter.setOnClickListener(this);

        RelativeLayout book = findViewById(R.id.book);
        book.setOnClickListener(view -> {
            Intent intent = new Intent(SingleSalonActivity.this, SelectTimeBarber.class);
            startActivity(intent);
        });

        findViewById(R.id.back_btn).setOnClickListener(this);

        getSalonDetails();
    }

    @SuppressLint("SetTextI18n")
    private void getSalonDetails() {
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("saloon_id", getIntent().getStringExtra("salonId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestResponseManager.getSalonDetails(parameters, Const.Get_Salon_Details_Request, response -> {
            if (response != null) {
                BaseRs baseRs = (BaseRs) response;
                salonModel = baseRs.getSalonModel();
                ServicesAdapter servicesAdapter = new ServicesAdapter(baseRs.getSaloon_services());
                recyclerView.setLayoutManager(new LinearLayoutManager(SingleSalonActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(servicesAdapter);

                if (baseRs.getSaloon_gallery().size() > 0) {
                    Glide.with(salonImage).load(baseRs.getSaloon_gallery().get(0)).placeholder(R.drawable.salon_sample).centerCrop().into(salonImage);
                }

                salon_name.setText(salonModel.getSaloon_name());
                address.setText(salonModel.getStreet_address());
                time.setText(salonModel.getOpen_time() + " - " + salonModel.getClose_time());
                salon_type.setText(salonModel.getSaloon_type());

                if (salonModel.getInstagram().equals("")) {
                    instagram.setVisibility(View.GONE);
                } else {
                    instagram.setVisibility(View.VISIBLE);
                }

                if (salonModel.getFacebook().equals("")) {
                    facebook.setVisibility(View.GONE);
                } else {
                    facebook.setVisibility(View.VISIBLE);
                }

                if (salonModel.getTwitter().equals("")) {
                    twitter.setVisibility(View.GONE);
                } else {
                    twitter.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.instagram:
                openSocialProfile(salonModel.getInstagram());
                break;
            case R.id.facebook:
                openSocialProfile(salonModel.getFacebook());
                break;
            case R.id.twitter:
                openSocialProfile(salonModel.getTwitter());
                break;
        }
    }

    private void openSocialProfile(String url) {
        if (!url.equals("")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }


}