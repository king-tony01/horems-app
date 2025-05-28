package com.kingtonytech.horems.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Resource;
import com.kingtonytech.horems.models.ResourceResponse;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddResourceActivity extends AppCompatActivity {

    private EditText inputType, inputQuantity;
    private CheckBox checkAvailable;
    private Button btnSubmit;
    private ProgressBar progressBar;
    private ApiService apiService;

    private ImageButton back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);

        inputType = findViewById(R.id.inputResourceType);
        inputQuantity = findViewById(R.id.inputResourceQuantity);
        checkAvailable = findViewById(R.id.checkboxAvailable);
        btnSubmit = findViewById(R.id.btnSubmitResource);
        progressBar = findViewById(R.id.progressBarAddResource);
        back_button = findViewById(R.id.back_button);

        apiService = RetrofitClient.getInstance().create(ApiService.class);

        btnSubmit.setOnClickListener(v -> submitResource());

        back_button.setOnClickListener(v -> {
            finish();
        });
    }

    private void submitResource() {
        String type = inputType.getText().toString().trim();
        String qtyStr = inputQuantity.getText().toString().trim();
        int available = checkAvailable.isChecked() ? 1 : 0;

        if (type.isEmpty() || qtyStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(qtyStr);

        Resource resource = new Resource();
        resource.setResource_type(type);
        resource.setQuantity(quantity);
        resource.setAvailable(available);

        progressBar.setVisibility(View.VISIBLE);
        btnSubmit.setEnabled(false);

        apiService.createResource(resource).enqueue(new Callback<ResourceResponse>() {
            @Override
            public void onResponse(Call<ResourceResponse> call, Response<ResourceResponse> response) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                if (response.isSuccessful()) {
                    Toast.makeText(AddResourceActivity.this, "Resource added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddResourceActivity.this, "Failed to add resource", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResourceResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                Toast.makeText(AddResourceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("AddResourceActivity Error", t.getMessage());
            }
        });
    }
}