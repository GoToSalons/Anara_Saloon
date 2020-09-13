package com.anara.salon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.BookingsActivity;
import com.anara.salon.Activities.IntroActivity;
import com.anara.salon.Activities.ListSalonActivity;
import com.anara.salon.Adapters.BookingAdapter;
import com.anara.salon.Adapters.BookingAdapter2;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Dialogs.MainScreenDialog;
import com.anara.salon.Helpers.PrefManager;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView upcomingBookings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefManager prefManager = new PrefManager(MainActivity.this);
        if (!prefManager.isLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        upcomingBookings = findViewById(R.id.upcoming_bookings);

        TextView name = findViewById(R.id.name);
        name.setText(prefManager.getString("customerName","User"));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("customer_id",prefManager.getInteger("customerId",-1));
        } catch (JSONException e) {

            e.printStackTrace();
        }
        RequestResponseManager.getBookings(jsonObject, Const.getBookings, new RequestResponseManager.OnResponseListener() {
            @Override
            public void onResponse(Object response) {
                BaseRs baseRs = (BaseRs) response;
                if (baseRs.getBookings()!=null){
                    RecyclerView recyclerView = findViewById(R.id.upcoming_bookings);
                    BookingAdapter2 bookingAdapter = new BookingAdapter2(MainActivity.this,baseRs.getBookings());
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(bookingAdapter);
                }
            }
        });

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(view -> {
            MainScreenDialog mainScreenDialog = new MainScreenDialog(MainActivity.this);
            mainScreenDialog.show(getSupportFragmentManager(), "Main");
        });

        ImageView imageView = findViewById(R.id.salon_image);
        Glide.with(imageView).load(getResources().getDrawable(R.drawable.main_page_image)).centerCrop().into(imageView);

        RelativeLayout HairSalon = findViewById(R.id.hair_saloon);
        RelativeLayout BeardSkinSalon = findViewById(R.id.beard_skin);
        RelativeLayout BeautySalon = findViewById(R.id.beauty);
        RelativeLayout OtherServicesSalon = findViewById(R.id.others);
        HairSalon.setOnClickListener(this);
        BeardSkinSalon.setOnClickListener(this);
        BeautySalon.setOnClickListener(this);
        OtherServicesSalon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.hair_saloon){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("mode", 1);
            intent.putExtra("service", "Hair Salons");
            intent.putExtra("serviceId", "1");
            startActivity(intent);
        }else if (view.getId()==R.id.beard_skin){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("mode", 1);
            intent.putExtra("service", "Beard & Skin Salons");
            intent.putExtra("serviceId", "2");
            startActivity(intent);
        }else if (view.getId()==R.id.beauty){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("mode", 1);
            intent.putExtra("service", "Beauty Salons");
            intent.putExtra("serviceId", "3");
            startActivity(intent);
        }else if (view.getId()==R.id.others){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("mode", 1);
            intent.putExtra("service", "Other Services");
            intent.putExtra("serviceId", "4");
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}