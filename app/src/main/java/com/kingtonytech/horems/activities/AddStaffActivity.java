package com.kingtonytech.horems.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Staff;
import com.kingtonytech.horems.models.StaffResponse;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStaffActivity extends AppCompatActivity {

    private EditText inputName, inputRole;
    private Spinner spinnerShift;
    private Button btnSubmit;
    private ProgressBar progressBar;
    private ImageButton back_button;
    private ApiService apiService;

    private final String[] shifts = {"Morning", "Afternoon", "Night"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        inputName = findViewById(R.id.inputStaffName);
        inputRole = findViewById(R.id.inputStaffRole);
        spinnerShift = findViewById(R.id.spinnerShift);
        btnSubmit = findViewById(R.id.btnSubmitStaff);
        back_button = findViewById(R.id.back_button);
        progressBar = findViewById(R.id.progressBarAddStaff);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, shifts);
        spinnerShift.setAdapter(adapter);

        apiService = RetrofitClient.getInstance().create(ApiService.class);

        btnSubmit.setOnClickListener(v -> submitStaff());

        back_button.setOnClickListener(v -> {
            finish();
        });
    }

    private void submitStaff() {
        String name = inputName.getText().toString().trim();
        String role = inputRole.getText().toString().trim();
        String shift = spinnerShift.getSelectedItem().toString();

        if (name.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Staff staff = new Staff();
        staff.setStaff_name(name);
        staff.setStaff_role(role);
        staff.setShift_time(shift);

        progressBar.setVisibility(View.VISIBLE);
        btnSubmit.setEnabled(false);

        apiService.createStaff(staff).enqueue(new Callback<StaffResponse>() {
            @Override
            public void onResponse(Call<StaffResponse> call, Response<StaffResponse> response) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);

                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(AddStaffActivity.this, "Staff added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddStaffActivity.this, "Failed to add staff", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StaffResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                Toast.makeText(AddStaffActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
