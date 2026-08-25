package com.example.myaplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.Repository.LeaveRepository;
import com.example.myaplication.adapter.LeaveBalanceAdapter;
import com.example.myaplication.model.response.LeaveBalanceResponse;
import com.example.myaplication.session.SesssoinManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveBalanceActivity extends AppCompatActivity {

    private RecyclerView rvLeaveBalance;
    private LeaveBalanceAdapter adapter;
    private List<LeaveBalanceResponse> balanceList = new ArrayList<>();
    private LeaveRepository leaveRepository;
    private SesssoinManager sessionManager;
    private Long employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_balance);

        rvLeaveBalance = findViewById(R.id.rvLeaveBalance);
        rvLeaveBalance.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaveBalanceAdapter(balanceList);
        rvLeaveBalance.setAdapter(adapter);

        leaveRepository = new LeaveRepository(this);
        sessionManager = new SesssoinManager(this);

        if (sessionManager.getEmployee() != null) {
            employeeId = sessionManager.getEmployee().getId();
            loadLeaveBalances();
        } else {
            Toast.makeText(this, "Session expired", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadLeaveBalances() {
        leaveRepository.getLeaveBalancesByEmployee(employeeId, new Callback<List<LeaveBalanceResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveBalanceResponse>> call, Response<List<LeaveBalanceResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    balanceList.clear();
                    balanceList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(LeaveBalanceActivity.this, "Failed to load balances", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveBalanceResponse>> call, Throwable t) {
                Toast.makeText(LeaveBalanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
