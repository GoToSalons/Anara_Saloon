package com.anara.salon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anara.salon.Adapters.SalonListAdapter;
import com.anara.salon.Apis.RequestManager;
import com.anara.salon.Dialogs.SortDialog;
import com.anara.salon.Models.SalonModel;
import com.anara.salon.R;

import java.util.ArrayList;

public class ListSalonActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView serviceSort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salon);

        TextView serviceName = findViewById(R.id.service_name);
        RecyclerView recyclerView = findViewById(R.id.salon_list);
        LinearLayout sortLayout = findViewById(R.id.sort);
        LinearLayout filterLayout = findViewById(R.id.filter);
        ImageView backButton = findViewById(R.id.back_button);
        serviceSort = findViewById(R.id.service_sort);
        RequestManager.getService(ListSalonActivity.this);
        sortLayout.setOnClickListener(this);
        filterLayout.setOnClickListener(this);
        backButton.setOnClickListener(this);

        Intent intent = getIntent();
        serviceName.setText(intent.getStringExtra("service"));

        ArrayList<SalonModel> salonModels = new ArrayList<>();
        for (int i=0;i<10;i++){
            salonModels.add(new SalonModel(getResources().getDrawable(R.drawable.salon_sample),
                    "Enrich Salon","Lamington Road, Mumbai Central","3.5","50",
                    "11:00 am - 4:00 pm","Starts at ₹200 for 1 person"));
        }
        SalonListAdapter salonListAdapter = new SalonListAdapter(ListSalonActivity.this,salonModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListSalonActivity.this));
        recyclerView.setAdapter(salonListAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.sort){
            SortDialog sortDialog = new SortDialog(ListSalonActivity.this);
            sortDialog.show(getSupportFragmentManager(), "Main");
        }else if (view.getId()== R.id.filter){
            Intent intent = new Intent(ListSalonActivity.this,FilterActivity.class);
            startActivity(intent);
        }else if(view.getId()==R.id.back_button){
            onBackPressed();
        }
    }
}