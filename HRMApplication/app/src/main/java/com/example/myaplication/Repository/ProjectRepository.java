package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.ProjectResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class ProjectRepository {
    private final ApiService apiService;

    public ProjectRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void getProjectsByEmployee(Long employeeId, Callback<List<ProjectResponse>> callback) {
        apiService.getProjectsByEmployee(employeeId).enqueue(callback);
    }
}
