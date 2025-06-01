package com.kingtonytech.horems.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Overview;
import com.kingtonytech.horems.models.OverviewResponse;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout patientsBtn, staffBtn, resourcesBtn, dataWrapper;
    private Button logoutBtn;

    private TextView textOverviewPatients, textOverviewStaff, textOverviewResources;

    private ApiService apiService;

    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        patientsBtn = findViewById(R.id.btnPatients);
        staffBtn = findViewById(R.id.btnStaff);
        resourcesBtn = findViewById(R.id.btnResources);
        logoutBtn = findViewById(R.id.btnLogout);
        dataWrapper = findViewById(R.id.data_wrapper);

        loadingBar = findViewById(R.id.progressBar);

        textOverviewPatients = findViewById(R.id.textOverviewPatients);
        textOverviewStaff = findViewById(R.id.textOverviewStaff);
        textOverviewResources = findViewById(R.id.textOverviewResources);

        apiService = RetrofitClient.getInstance().create(ApiService.class);

        patientsBtn.setOnClickListener(v ->
                startActivity(new Intent(this, PatientsActivity.class))
        );

        staffBtn.setOnClickListener(v ->
                startActivity(new Intent(this, StaffActivity.class))
        );

        resourcesBtn.setOnClickListener(v ->
                startActivity(new Intent(this, ResourcesActivity.class))
        );

        logoutBtn.setOnClickListener(v -> {
            // Clear login state if needed, then return to login
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        fetchOverview();
    }

    @Override
    protected void onResume(){
        super.onResume();
        fetchOverview();
    }

    private void fetchOverview() {
        loadingBar.setVisibility(View.VISIBLE);
        dataWrapper.setVisibility(View.GONE);

        apiService.getOverview().enqueue(new Callback<OverviewResponse>() {
            @Override
            public void onResponse(Call<OverviewResponse> call, Response<OverviewResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Overview data = response.body().getData();
                    textOverviewPatients.setText(String.valueOf(data.getPatients()));
                    textOverviewStaff.setText(String.valueOf(data.getStaffs()));
                    textOverviewResources.setText(String.valueOf(data.getResources()));
                    loadingBar.setVisibility(View.GONE);
                    dataWrapper.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(DashboardActivity.this, "Failed to load overview", Toast.LENGTH_SHORT).show();
                    loadingBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<OverviewResponse> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
