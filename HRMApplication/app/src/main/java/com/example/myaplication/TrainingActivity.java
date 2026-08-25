package com.example.myaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplication.Repository.TrainingRepository;
import com.example.myaplication.adapter.TrainingAdapter;
import com.example.myaplication.model.response.TrainingResponse;
import com.example.myaplication.session.SesssoinManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingActivity extends AppCompatActivity implements TrainingAdapter.OnApplyClickListener {

    private RecyclerView rvTrainings;
    private TrainingAdapter trainingAdapter;
    private List<TrainingResponse> trainingList = new ArrayList<>();
    private TrainingRepository trainingRepository;
    private SesssoinManager sessionManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();
        loadTrainings();
    }

    private void initViews() {
        rvTrainings = findViewById(R.id.rvTrainings);
        progressBar = findViewById(R.id.progressBar);
        rvTrainings.setLayoutManager(new LinearLayoutManager(this));
        trainingAdapter = new TrainingAdapter(trainingList, this);
        rvTrainings.setAdapter(trainingAdapter);

        trainingRepository = new TrainingRepository(this);
        sessionManager = new SesssoinManager(this);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void loadTrainings() {
        progressBar.setVisibility(View.VISIBLE);
        trainingRepository.getAllTrainings(new Callback<List<TrainingResponse>>() {
            @Override
            public void onResponse(Call<List<TrainingResponse>> call, Response<List<TrainingResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    trainingList.clear();
                    trainingList.addAll(response.body());
                    trainingAdapter.notifyDataSetChanged();
                    if (trainingList.isEmpty()) {
                        Toast.makeText(TrainingActivity.this, "No training available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TrainingActivity.this, "Failed to load trainings", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TrainingResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(TrainingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onApplyClick(TrainingResponse training) {
        Long employeeId = sessionManager.getEmployee().getId();
        if (employeeId == null) {
            Toast.makeText(this, "Employee ID not found", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        trainingRepository.applyForTraining(training.getId(), employeeId, new Callback<TrainingResponse>() {
            @Override
            public void onResponse(Call<TrainingResponse> call, Response<TrainingResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Toast.makeText(TrainingActivity.this, "Application submitted successfully for " + training.getTrainingTitle(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TrainingActivity.this, "Failed to apply for training", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrainingResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(TrainingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
