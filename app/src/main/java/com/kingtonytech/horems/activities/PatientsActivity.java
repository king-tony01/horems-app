package com.kingtonytech.horems.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.kingtonytech.horems.adapters.PatientAdapter;
import com.kingtonytech.horems.models.Patient;
import com.kingtonytech.horems.models.PatientListResponse;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private PatientAdapter adapter;
    private ApiService apiService;
    private ImageButton back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        recyclerView = findViewById(R.id.recyclerPatients);
        progressBar = findViewById(R.id.progressBarPatients);
        back_button = findViewById(R.id.back_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = RetrofitClient.getInstance().create(ApiService.class);

        fetchPatients();

        back_button.setOnClickListener(v -> {
            finish();
        });

        LinearLayout fabAdd = findViewById(R.id.fabAddPatient);
        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(PatientsActivity.this, AddPatientActivity.class));
        });
    }

    private void fetchPatients() {
        progressBar.setVisibility(View.VISIBLE);

        apiService.getPatients().enqueue(new Callback<PatientListResponse>() {
            @Override
            public void onResponse(Call<PatientListResponse> call, Response<PatientListResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Patient> patients = response.body().getData();
                    adapter = new PatientAdapter(patients);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(PatientsActivity.this, "Failed to load patients", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PatientListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PatientsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPatients();
    }
}
