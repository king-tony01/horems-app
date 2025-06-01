package com.kingtonytech.horems.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kingtonytech.horems.R;
import com.kingtonytech.horems.adapters.StaffAdapter;
import com.kingtonytech.horems.models.Staff;
import com.kingtonytech.horems.models.StaffListResponse;
import com.kingtonytech.horems.models.StaffResponse;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout fabAddStaff;
    private ImageButton back_button;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        recyclerView = findViewById(R.id.recyclerStaff);
        progressBar = findViewById(R.id.progressBarStaff);
        fabAddStaff = findViewById(R.id.fabAddStaff);
        back_button = findViewById(R.id.back_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = RetrofitClient.getInstance().create(ApiService.class);

        fabAddStaff.setOnClickListener(v ->
                startActivity(new Intent(this, AddStaffActivity.class))
        );

        back_button.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchStaff();
    }

    private void fetchStaff() {
        progressBar.setVisibility(View.VISIBLE);

        apiService.getStaff().enqueue(new Callback<StaffListResponse>() {
            @Override
            public void onResponse(Call<StaffListResponse> call, Response<StaffListResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Staff> staffList = response.body().getData();
                    recyclerView.setAdapter(new StaffAdapter(staffList));
                } else {
                    Toast.makeText(StaffActivity.this, "Failed to load staff", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StaffListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(StaffActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
