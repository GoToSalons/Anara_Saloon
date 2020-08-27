package com.anara.salon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Dialogs.SortDialog;
import com.anara.salon.R;

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
        sortLayout.setOnClickListener(this);
        filterLayout.setOnClickListener(this);
        backButton.setOnClickListener(this);

        Intent intent = getIntent();
        serviceName.setText(intent.getStringExtra("service"));

        RequestResponseManager.getSalon(ListSalonActivity.this, recyclerView);


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