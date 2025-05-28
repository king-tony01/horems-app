package com.kingtonytech.horems.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Patient;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPatientActivity extends AppCompatActivity {
    private EditText inputName, inputAge, inputCondition;
    private Button btnSubmit;
    private ProgressBar progressBar;
    private ImageButton back_button;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        inputName = findViewById(R.id.inputName);
        inputAge = findViewById(R.id.inputAge);
        inputCondition = findViewById(R.id.inputCondition);
        btnSubmit = findViewById(R.id.btnSubmitPatient);
        back_button = findViewById(R.id.back_button);
        progressBar = findViewById(R.id.progressBarAddPatient);
        apiService = RetrofitClient.getInstance().create(ApiService.class);

        btnSubmit.setOnClickListener(v -> submitPatient());

        back_button.setOnClickListener(v -> {
            finish();
        });
    }

    private void submitPatient() {
        String name = inputName.getText().toString().trim();
        String ageStr = inputAge.getText().toString().trim();
        String condition = inputCondition.getText().toString().trim();

        if (name.isEmpty() || ageStr.isEmpty() || condition.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);
        Patient patient = new Patient();
        patient.setPatient_name(name);
        patient.setAge(age);
        patient.setPatient_condition(condition);

        progressBar.setVisibility(View.VISIBLE);
        btnSubmit.setEnabled(false);

        apiService.createPatient(patient).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                if (response.isSuccessful()) {
                    Toast.makeText(AddPatientActivity.this, "Patient added", Toast.LENGTH_SHORT).show();
                    finish(); // go back to patient list
                } else {
                    Toast.makeText(AddPatientActivity.this, "Failed to add patient", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                Toast.makeText(AddPatientActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
