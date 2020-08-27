package com.anara.salon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Activities.IntroActivity;
import com.anara.salon.Activities.ListSalonActivity;
import com.anara.salon.Adapters.BookingAdapter;
import com.anara.salon.Dialogs.MainScreenDialog;
import com.anara.salon.Models.BookingModel;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);


        ArrayList<BookingModel> bookingModels = new ArrayList<>();
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Upcoming"));
        bookingModels.add(new BookingModel("11 Sep","11:00 AM","Enrich Salon","Hair Cut","Upcoming"));


        RecyclerView recyclerView = findViewById(R.id.upcoming_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new BookingAdapter(bookingModels));

        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainScreenDialog mainScreenDialog = new MainScreenDialog(MainActivity.this);
                mainScreenDialog.show(getSupportFragmentManager(), "Main");
            }
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
            intent.putExtra("service","Hair Salons");
            startActivity(intent);
        }else if (view.getId()==R.id.beard_skin){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("service","Beard & Skin Salons");
            startActivity(intent);
        }else if (view.getId()==R.id.beauty){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("service","Beauty Salons");
            startActivity(intent);
        }else if (view.getId()==R.id.others){
            Intent intent = new Intent(MainActivity.this, ListSalonActivity.class);
            intent.putExtra("service","Other Services");
            startActivity(intent);
        }
    }
}