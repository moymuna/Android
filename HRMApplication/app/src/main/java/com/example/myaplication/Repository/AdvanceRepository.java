package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.request.AdvanceRequest;
import com.example.myaplication.model.response.AdvanceResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class AdvanceRepository {
    private final ApiService apiService;

    public AdvanceRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void saveAdvance(AdvanceRequest request, Callback<AdvanceResponse> callback) {
        apiService.saveAdvance(request).enqueue(callback);
    }

    public void getAdvancesByEmployee(Long employeeId, Callback<List<AdvanceResponse>> callback) {
        apiService.getAdvancesByEmployee(employeeId).enqueue(callback);
    }
}
