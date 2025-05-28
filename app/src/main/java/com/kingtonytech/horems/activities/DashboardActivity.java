package com.kingtonytech.horems.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.kingtonytech.horems.R;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout patientsBtn, staffBtn, resourcesBtn;
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        patientsBtn = findViewById(R.id.btnPatients);
        staffBtn = findViewById(R.id.btnStaff);
        resourcesBtn = findViewById(R.id.btnResources);
        logoutBtn = findViewById(R.id.btnLogout);

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
    }
}
