package com.example.myaplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplication.Repository.AdvanceRepository;
import com.example.myaplication.adapter.AdvanceAdapter;
import com.example.myaplication.model.request.AdvanceRequest;
import com.example.myaplication.model.response.AdvanceResponse;
import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.session.SesssoinManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvanceSalaryActivity extends AppCompatActivity {

    private TextInputEditText etAmount, etRequiredDate, etInstallments, etReason;
    private MaterialButton btnSubmit;
    private RecyclerView rvAdvances;
    private AdvanceAdapter adapter;
    private List<AdvanceResponse> advanceList = new ArrayList<>();

    private AdvanceRepository advanceRepository;
    private SesssoinManager sessionManager;
    private EmployeeResponse employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advance_salary);

        initViews();
        setupDatePicker();
        loadData();

        btnSubmit.setOnClickListener(v -> submitRequest());
    }

    private void initViews() {
        etAmount = findViewById(R.id.etAmount);
        etRequiredDate = findViewById(R.id.etRequiredDate);
        etInstallments = findViewById(R.id.etInstallments);
        etReason = findViewById(R.id.etReason);
        btnSubmit = findViewById(R.id.btnSubmit);
        rvAdvances = findViewById(R.id.rvAdvances);

        rvAdvances.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdvanceAdapter(advanceList);
        rvAdvances.setAdapter(adapter);

        advanceRepository = new AdvanceRepository(this);
        sessionManager = new SesssoinManager(this);
        employee = sessionManager.getEmployee();
    }

    private void setupDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Required Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        etRequiredDate.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(), "DATE_PICKER"));

        datePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            etRequiredDate.setText(sdf.format(new Date(selection)));
        });
    }

    private void loadData() {
        if (employee == null) return;

        advanceRepository.getAdvancesByEmployee(employee.getId(), new Callback<List<AdvanceResponse>>() {
            @Override
            public void onResponse(Call<List<AdvanceResponse>> call, Response<List<AdvanceResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    advanceList.clear();
                    advanceList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<AdvanceResponse>> call, Throwable t) {
                Toast.makeText(AdvanceSalaryActivity.this, "Failed to load history", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitRequest() {
        String amountStr = etAmount.getText().toString().trim();
        String date = etRequiredDate.getText().toString().trim();
        String installmentsStr = etInstallments.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        if (TextUtils.isEmpty(amountStr) || TextUtils.isEmpty(date) || 
            TextUtils.isEmpty(installmentsStr) || TextUtils.isEmpty(reason)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (employee == null) {
            Toast.makeText(this, "Session expired", Toast.LENGTH_SHORT).show();
            return;
        }

        AdvanceRequest request = new AdvanceRequest();
        request.setAmount(new BigDecimal(amountStr));
        request.setRequiredByDate(date);
        request.setRequestDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        request.setInstallments(Integer.parseInt(installmentsStr));
        request.setReason(reason);
        request.setEmployeeId(employee.getId());

        btnSubmit.setEnabled(false);
        advanceRepository.saveAdvance(request, new Callback<AdvanceResponse>() {
            @Override
            public void onResponse(Call<AdvanceResponse> call, Response<AdvanceResponse> response) {
                btnSubmit.setEnabled(true);
                if (response.isSuccessful()) {
                    Toast.makeText(AdvanceSalaryActivity.this, "Request submitted successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                    loadData();
                } else {
                    Toast.makeText(AdvanceSalaryActivity.this, "Submission failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdvanceResponse> call, Throwable t) {
                btnSubmit.setEnabled(true);
                Toast.makeText(AdvanceSalaryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearFields() {
        etAmount.setText("");
        etRequiredDate.setText("");
        etInstallments.setText("");
        etReason.setText("");
    }
}
