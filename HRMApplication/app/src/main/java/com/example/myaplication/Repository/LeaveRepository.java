package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.request.LeaveRequest;
import com.example.myaplication.model.response.LeaveBalanceResponse;
import com.example.myaplication.model.response.LeaveResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class LeaveRepository {
    private final ApiService apiService;

    public LeaveRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void applyLeave(LeaveRequest request, Callback<LeaveResponse> callback) {
        apiService.applyLeave(request).enqueue(callback);
    }

    public void getLeavesByEmployee(Long employeeId, Callback<List<LeaveResponse>> callback) {
        apiService.getLeavesByEmployee(employeeId).enqueue(callback);
    }

    public void getLeaveBalancesByEmployee(Long employeeId, Callback<List<LeaveBalanceResponse>> callback) {
        apiService.getLeaveBalancesByEmployee(employeeId).enqueue(callback);
    }
}
