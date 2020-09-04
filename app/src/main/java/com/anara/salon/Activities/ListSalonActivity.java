package com.anara.salon.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anara.salon.Adapters.SalonListAdapter;
import com.anara.salon.ApiResponse.BaseRs;
import com.anara.salon.Apis.Const;
import com.anara.salon.Apis.RequestResponseManager;
import com.anara.salon.Dialogs.SortDialog;
import com.anara.salon.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ListSalonActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView serviceSort;
    public RecyclerView recyclerView;
    String serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salon);

        TextView serviceName = findViewById(R.id.service_name);
        recyclerView = findViewById(R.id.salon_list);
        LinearLayout sortLayout = findViewById(R.id.sort);
        LinearLayout filterLayout = findViewById(R.id.filter);
        ImageView backButton = findViewById(R.id.back_button);
        serviceSort = findViewById(R.id.service_sort);
        sortLayout.setOnClickListener(this);
        filterLayout.setOnClickListener(this);
        backButton.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListSalonActivity.this));

        Intent intent = getIntent();
        serviceName.setText(intent.getStringExtra("service"));

        JSONObject parameters = new JSONObject();
        serviceId = intent.getStringExtra("serviceId");
        try {
            parameters.put("service_id", serviceId);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestResponseManager.getSalon(parameters, Const.Get_Salon_Request, response -> {
            if (response != null) {
                BaseRs baseRs = (BaseRs) response;
                SalonListAdapter salonListAdapter = new SalonListAdapter(ListSalonActivity.this, baseRs.getSaloons());
                recyclerView.setAdapter(salonListAdapter);
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.sort){
            SortDialog sortDialog = new SortDialog(ListSalonActivity.this, serviceId);
            sortDialog.show(getSupportFragmentManager(), "Main");
        }else if (view.getId()== R.id.filter){
            Intent intent = new Intent(ListSalonActivity.this,FilterActivity.class);
            intent.putExtra("service_id", serviceId);
            startActivity(intent);
        }else if(view.getId()==R.id.back_button){
            onBackPressed();
        }
    }
}