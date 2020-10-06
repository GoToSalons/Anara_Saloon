package com.anara.salon.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.anara.salon.Adapters.ServicesAdapter;
import com.anara.salon.Adapters.SliderAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.ApiResponse.SalonModel;
import com.anara.salon.ApiResponse.SalonServices;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SingleSalonActivity extends AppCompatActivity implements View.OnClickListener {

    public ArrayList<SalonServices> checkedItems;
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
    ViewPager enchantedViewPager;
    private Handler handler = new Handler();
    private Runnable Update;

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
        enchantedViewPager = findViewById(R.id.viewPager_home_fragment);

        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);


        instagram.setOnClickListener(this);
        facebook.setOnClickListener(this);
        twitter.setOnClickListener(this);
        checkedItems = new ArrayList<>();

        RelativeLayout book = findViewById(R.id.book);
        book.setOnClickListener(view -> {

            if (checkedItems.size()!=0){
                Intent intent = new Intent(SingleSalonActivity.this, SelectTimeBarber.class);
                intent.putExtra("salonId", getIntent().getStringExtra("salonId"));
                Bundle args = new Bundle();
                args.putSerializable("list", (Serializable) checkedItems);
                intent.putExtra("checkedServices", args);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Choose Services", Toast.LENGTH_SHORT).show();
            }

        });

        findViewById(R.id.back_btn).setOnClickListener(this);

        getSalonDetails();
    }

    @SuppressLint("SetTextI18n")
    private void getSalonDetails() {
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("salon_id", getIntent().getStringExtra("salonId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestResponseManager.getSalonDetails(parameters, Const.Get_Salon_Details_Request, new RequestResponseManager.OnResponseListener() {
            @Override
            public void onResponse(Object response) {
                if (response != null) {
                    BaseRs baseRs = (BaseRs) response;
                    salonModel = baseRs.getSalonModel();
                    ServicesAdapter servicesAdapter = new ServicesAdapter(baseRs.getSaloon_services(), SingleSalonActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SingleSalonActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    recyclerView.setAdapter(servicesAdapter);

                    if (baseRs.getSaloon_gallery().size() > 0) {
                        SingleSalonActivity.this.addSlider(baseRs.getSaloon_gallery());
                    }

                    salon_name.setText(salonModel.getSaloon_name());
                    address.setText(salonModel.getStreet_address());
                    time.setText(salonModel.getOpen_time() + " - " + salonModel.getClose_time());
                    salon_type.setText(salonModel.getSaloon_type());
                    mobile_number.setText(salonModel.getContact_no());
                    rating.setRating(round(salonModel.getRatings(), 1));
                    rating_text.setText(round(salonModel.getRatings(), 1) + "");

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
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, "Invalid URL by Salon", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void addSlider(ArrayList<String> imageUrl) {
        SliderAdapter sliderAdapter = new SliderAdapter(imageUrl,SingleSalonActivity.this);
        enchantedViewPager.setAdapter(sliderAdapter);
        enchantedViewPager.setCurrentItem(0);

        if (sliderAdapter.getCount() > 1) {
            Timer timer = new Timer(); // This will create a new Thread
            long DELAY_MS = 6000;
            long PERIOD_MS = 8000;
            timer.schedule(new TimerTask() { // task to be scheduled
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);
        }

        Update = () -> {
            if (enchantedViewPager.getCurrentItem() == (sliderAdapter.getCount() - 1)) {
                enchantedViewPager.setCurrentItem(0, true);
            } else {
                enchantedViewPager.setCurrentItem(enchantedViewPager.getCurrentItem() + 1, true);
            }
        };

    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}