package com.example.myaplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.Repository.SalaryRepository;
import com.example.myaplication.adapter.PayslipAdapter;
import com.example.myaplication.model.response.PayslipResponse;
import com.example.myaplication.session.SesssoinManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayslipActivity extends AppCompatActivity {

    private SalaryRepository salaryRepository;
    private SesssoinManager sessionManager;
    private Long employeeId;

    private RecyclerView rvPayslips;
    private PayslipAdapter adapter;
    private List<PayslipResponse> payslipList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        sessionManager = new SesssoinManager(this);
        salaryRepository = new SalaryRepository(this);

        if (sessionManager.getEmployee() != null) {
            employeeId = sessionManager.getEmployee().getId();
        }

        rvPayslips = findViewById(R.id.rv_payslips);
        rvPayslips.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PayslipAdapter(payslipList);
        rvPayslips.setAdapter(adapter);

        loadPayslips();
    }

    private void loadPayslips() {
        if (employeeId == null) return;

        salaryRepository.getPayslipsByEmployee(employeeId, new Callback<List<PayslipResponse>>() {
            @Override
            public void onResponse(Call<List<PayslipResponse>> call, Response<List<PayslipResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    payslipList.clear();
                    payslipList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PayslipActivity.this, "Failed to load payslips", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PayslipResponse>> call, Throwable t) {
                Toast.makeText(PayslipActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
