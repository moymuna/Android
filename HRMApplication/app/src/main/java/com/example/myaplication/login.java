package com.example.myaplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myaplication.Repository.AuthRepository;
import com.example.myaplication.Repository.EmployeeRepository;
import com.example.myaplication.model.request.LogInRequest;
import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.model.response.LogInResponse;
import com.example.myaplication.session.SesssoinManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login  extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    private AuthRepository authRepository;
    private EmployeeRepository employeeRepository;
    private SesssoinManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        init();

        btnLogin.setOnClickListener(v -> login());

    }

    private void init() {

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        authRepository = new AuthRepository(this);
        employeeRepository = new EmployeeRepository(this);
        sessionManager = new SesssoinManager(this);

    }

    private void login() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email Required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password Required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

        LogInRequest request = new LogInRequest();

        request.setEmail(email);
        request.setPassword(password);

        authRepository.login(request, new Callback<LogInResponse>() {

            @Override
            public void onResponse(Call<LogInResponse> call,
                                   Response<LogInResponse> response) {

                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);

                if (!response.isSuccessful()) {

                    Toast.makeText(login.this,
                            "Invalid Email or Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                LogInResponse login = response.body();

                System.out.println(login);

                if (login == null) {
                    Toast.makeText(login.this,
                            "Login Failed",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save Token
                sessionManager.saveToken(login.getToken());

                // Save User
                sessionManager.saveUser(login);

                // Load Customer
                loadEmployee(login.getId());

            }

            @Override
            public void onFailure(Call<LogInResponse> call,
                                  Throwable t) {

                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);

                Toast.makeText(login.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    private void loadEmployee(Long id) {

        employeeRepository.getEmployeeByUserId(id,
                new Callback<EmployeeResponse>() {

                    @Override
                    public void onResponse(Call<EmployeeResponse> call,
                                           Response<EmployeeResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            sessionManager.saveEmployee(response.body());
                        }

                        Intent intent = new Intent(login.this, EmployeeDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponse> call,
                                          Throwable t) {

                        Toast.makeText(login.this,
                                t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}