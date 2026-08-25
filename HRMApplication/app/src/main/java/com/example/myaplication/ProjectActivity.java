package com.example.myaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplication.Repository.ProjectRepository;
import com.example.myaplication.adapter.ProjectAdapter;
import com.example.myaplication.model.response.ProjectResponse;
import com.example.myaplication.session.SesssoinManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectActivity extends AppCompatActivity {

    private RecyclerView rvProjects;
    private ProjectAdapter projectAdapter;
    private List<ProjectResponse> projectList = new ArrayList<>();
    private ProjectRepository projectRepository;
    private SesssoinManager sessionManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        initViews();
        loadProjects();
    }

    private void initViews() {
        rvProjects = findViewById(R.id.rvProjects);
        progressBar = findViewById(R.id.progressBar);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        projectAdapter = new ProjectAdapter(projectList);
        rvProjects.setAdapter(projectAdapter);

        projectRepository = new ProjectRepository(this);
        sessionManager = new SesssoinManager(this);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void loadProjects() {
        Long employeeId = sessionManager.getEmployee().getId();
        if (employeeId == null) {
            Toast.makeText(this, "Employee ID not found", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        projectRepository.getProjectsByEmployee(employeeId, new Callback<List<ProjectResponse>>() {
            @Override
            public void onResponse(Call<List<ProjectResponse>> call, Response<List<ProjectResponse>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    projectList.clear();
                    projectList.addAll(response.body());
                    projectAdapter.notifyDataSetChanged();
                    if (projectList.isEmpty()) {
                        Toast.makeText(ProjectActivity.this, "No projects assigned", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProjectActivity.this, "Failed to load projects", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProjectResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ProjectActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
