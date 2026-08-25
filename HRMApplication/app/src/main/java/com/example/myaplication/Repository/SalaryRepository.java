package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.PayslipResponse;
import com.example.myaplication.model.response.SalaryResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class SalaryRepository {
    private final ApiService apiService;

    public SalaryRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void getSalaryByEmployee(Long employeeId, Callback<SalaryResponse> callback) {
        apiService.getSalaryByEmployee(employeeId).enqueue(callback);
    }

    public void getPayslipsByEmployee(Long employeeId, Callback<List<PayslipResponse>> callback) {
        apiService.getPayslipsByEmployee(employeeId).enqueue(callback);
    }
}
