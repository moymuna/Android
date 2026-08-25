package com.example.myaplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myaplication.Enum.LeaveType;
import com.example.myaplication.Repository.LeaveRepository;
import com.example.myaplication.model.request.LeaveRequest;
import com.example.myaplication.model.response.LeaveResponse;
import com.example.myaplication.session.SesssoinManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyLeaveActivity extends AppCompatActivity {

    private AutoCompleteTextView spinnerLeaveType;
    private TextInputEditText etStartDate, etEndDate, etReason;
    private MaterialButton btnSubmit;
    private LeaveRepository leaveRepository;
    private SesssoinManager sessionManager;
    private Long employeeId;
    private Long selectedLeaveTypeId = 1L; // Default

    private final SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private final SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);

        initViews();
        leaveRepository = new LeaveRepository(this);
        sessionManager = new SesssoinManager(this);

        if (sessionManager.getEmployee() != null) {
            employeeId = sessionManager.getEmployee().getId();
        } else {
            Toast.makeText(this, "Session expired", Toast.LENGTH_SHORT).show();
            finish();
        }

        setupLeaveTypeSpinner();
        setupDatePickers();

        btnSubmit.setOnClickListener(v -> submitApplication());
    }

    private void initViews() {
        spinnerLeaveType = findViewById(R.id.spinnerLeaveType);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etReason = findViewById(R.id.etReason);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupLeaveTypeSpinner() {
        LeaveType[] leaveTypes = LeaveType.values();
        String[] typeNames = new String[leaveTypes.length];
        for (int i = 0; i < leaveTypes.length; i++) {
            typeNames[i] = leaveTypes[i].name().replace("_", " ");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, typeNames);
        spinnerLeaveType.setAdapter(adapter);
        spinnerLeaveType.setOnItemClickListener((parent, view, position, id) -> {
            selectedLeaveTypeId = (long) (position + 1);
        });
    }

    private void setupDatePickers() {
        etStartDate.setOnClickListener(v -> showDatePicker(etStartDate));
        etEndDate.setOnClickListener(v -> showDatePicker(etEndDate));
    }

    private void showDatePicker(TextInputEditText editText) {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            editText.setText(displayFormat.format(new Date(selection)));
            editText.setTag(apiFormat.format(new Date(selection)));
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    private void submitApplication() {
        String startDate = (String) etStartDate.getTag();
        String endDate = (String) etEndDate.getTag();
        String reason = etReason.getText().toString().trim();

        if (startDate == null || endDate == null || reason.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(employeeId);
        request.setLeaveTypeId(selectedLeaveTypeId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setReason(reason);
        request.setStatus("PENDING");
        request.setTotalDays(1.0); // Simple default, backend usually calculates this

        leaveRepository.applyLeave(request, new Callback<LeaveResponse>() {
            @Override
            public void onResponse(Call<LeaveResponse> call, Response<LeaveResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ApplyLeaveActivity.this, "Leave application submitted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ApplyLeaveActivity.this, "Submission failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeaveResponse> call, Throwable t) {
                Toast.makeText(ApplyLeaveActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
