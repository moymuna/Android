package com.example.myaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myaplication.Repository.SalaryRepository;
import com.example.myaplication.model.response.SalaryResponse;
import com.example.myaplication.session.SesssoinManager;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySalaryActivity extends AppCompatActivity {

    private SalaryRepository salaryRepository;
    private SesssoinManager sessionManager;
    private Long employeeId;

    private TextView tvNetSalary;
    private View rowBasic, rowHra, rowConveyance, rowMedical, rowSpecial, rowPf, rowPt, rowIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_salary);

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

        initViews();
        loadSalaryData();
    }

    private void initViews() {
        tvNetSalary = findViewById(R.id.tv_net_salary);
        rowBasic = findViewById(R.id.row_basic);
        rowHra = findViewById(R.id.row_hra);
        rowConveyance = findViewById(R.id.row_conveyance);
        rowMedical = findViewById(R.id.row_medical);
        rowSpecial = findViewById(R.id.row_special);
        rowPf = findViewById(R.id.row_pf);
        rowPt = findViewById(R.id.row_pt);
        rowIt = findViewById(R.id.row_it);

        setRowLabel(rowBasic, "Basic Salary");
        setRowLabel(rowHra, "HRA");
        setRowLabel(rowConveyance, "Conveyance Allowance");
        setRowLabel(rowMedical, "Medical Allowance");
        setRowLabel(rowSpecial, "Special Allowance");
        setRowLabel(rowPf, "Provident Fund");
        setRowLabel(rowPt, "Professional Tax");
        setRowLabel(rowIt, "Income Tax");
    }

    private void setRowLabel(View row, String label) {
        TextView tvLabel = row.findViewById(R.id.tv_label);
        tvLabel.setText(label);
    }

    private void setRowValue(View row, BigDecimal value) {
        TextView tvValue = row.findViewById(R.id.tv_value);
        tvValue.setText(formatCurrency(value));
    }

    private String formatCurrency(BigDecimal amount) {
        if (amount == null) amount = BigDecimal.ZERO;
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(amount);
    }

    private void loadSalaryData() {
        if (employeeId == null) return;

        salaryRepository.getSalaryByEmployee(employeeId, new Callback<SalaryResponse>() {
            @Override
            public void onResponse(Call<SalaryResponse> call, Response<SalaryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SalaryResponse salary = response.body();
                    displaySalary(salary);
                } else {
                    Toast.makeText(MySalaryActivity.this, "Failed to load salary details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SalaryResponse> call, Throwable t) {
                Toast.makeText(MySalaryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displaySalary(SalaryResponse salary) {
        setRowValue(rowBasic, salary.getBasicSalary());
        setRowValue(rowHra, salary.getHra());
        setRowValue(rowConveyance, salary.getConveyanceAllowance());
        setRowValue(rowMedical, salary.getMedicalAllowance());
        setRowValue(rowSpecial, salary.getSpecialAllowance());
        setRowValue(rowPf, salary.getProvidentFund());
        setRowValue(rowPt, salary.getProfessionalTax());
        setRowValue(rowIt, salary.getIncomeTax());
        tvNetSalary.setText(formatCurrency(salary.getNetMonthly()));
    }
}
