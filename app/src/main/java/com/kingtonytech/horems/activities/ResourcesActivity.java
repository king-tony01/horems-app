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
import com.kingtonytech.horems.adapters.ResourceAdapter;
import com.kingtonytech.horems.models.Resource;
import com.kingtonytech.horems.models.ResourceListResponse;
import com.kingtonytech.horems.models.ResourceResponse;
import com.kingtonytech.horems.network.ApiService;
import com.kingtonytech.horems.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourcesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout fabAdd;

    private ImageButton back_button;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        recyclerView = findViewById(R.id.recyclerResources);
        progressBar = findViewById(R.id.progressBarResources);
        fabAdd = findViewById(R.id.fabAddResource);

        back_button = findViewById(R.id.back_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = RetrofitClient.getInstance().create(ApiService.class);

        fabAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddResourceActivity.class))
        );

        back_button.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchResources();
    }

    private void fetchResources() {
        progressBar.setVisibility(View.VISIBLE);
        apiService.getResources().enqueue(new Callback<ResourceListResponse>() {
            @Override
            public void onResponse(Call<ResourceListResponse> call, Response<ResourceListResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Resource> resources = response.body().getData();
                    recyclerView.setAdapter(new ResourceAdapter(resources));
                } else {
                    Toast.makeText(ResourcesActivity.this, "Failed to load resources", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResourceListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ResourcesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("ResourcesActivity Error", t.getMessage());
            }
        });
    }
}
