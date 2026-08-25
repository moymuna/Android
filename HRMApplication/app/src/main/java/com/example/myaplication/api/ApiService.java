package com.example.myaplication.api;

import com.example.myaplication.model.request.AdvanceRequest;
import com.example.myaplication.model.request.AttendanceRequest;
import com.example.myaplication.model.request.LeaveRequest;
import com.example.myaplication.model.request.LogInRequest;
import com.example.myaplication.model.response.AdvanceResponse;
import com.example.myaplication.model.response.AttendanceResponse;
import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.model.response.HolidayResponse;
import com.example.myaplication.model.response.LeaveBalanceResponse;
import com.example.myaplication.model.response.LeaveResponse;
import com.example.myaplication.model.response.LogInResponse;
import com.example.myaplication.model.response.NoticeResponse;
import com.example.myaplication.model.response.PayslipResponse;
import com.example.myaplication.model.response.ProjectResponse;
import com.example.myaplication.model.response.SalaryResponse;
import com.example.myaplication.model.response.TrainingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/auth/login")
    Call<LogInResponse> login(@Body LogInRequest request);

    @GET("api/employees/user/{userId}")
    Call<EmployeeResponse> getEmployeeByUserId(@Path("userId") Long userId);

    // Attendance
    @POST("api/attendance/clock-in/{employeeId}")
    Call<AttendanceResponse> clockIn(@Path("employeeId") Long employeeId);

    @PUT("api/attendance/clock-out/{employeeId}")
    Call<AttendanceResponse> clockOut(@Path("employeeId") Long employeeId);

    @GET("api/attendance/today/{employeeId}")
    Call<AttendanceResponse> getTodayAttendance(@Path("employeeId") Long employeeId);

    // Leave
    @POST("api/leaves/save")
    Call<LeaveResponse> applyLeave(@Body LeaveRequest request);

    @GET("api/leaves/employee/{employeeId}")
    Call<List<LeaveResponse>> getLeavesByEmployee(@Path("employeeId") Long employeeId);

    @GET("api/leave-balance/employee/{employeeId}")
    Call<List<LeaveBalanceResponse>> getLeaveBalancesByEmployee(@Path("employeeId") Long employeeId);

    // Salary & Payslip
    @GET("api/salary/employee/{employeeId}")
    Call<SalaryResponse> getSalaryByEmployee(@Path("employeeId") Long employeeId);

    @GET("api/payslip/employee/{employeeId}")
    Call<List<PayslipResponse>> getPayslipsByEmployee(@Path("employeeId") Long employeeId);

    // Notice & Holiday
    @GET("api/notice")
    Call<List<NoticeResponse>> getAllNotices();

    @GET("api/holiday/all")
    Call<List<HolidayResponse>> getAllHolidays();

    @GET("api/holiday/upcoming")
    Call<List<HolidayResponse>> getUpcomingHolidays(@Query("limit") int limit);

    // Project & Training
    @GET("api/project/employee/{id}/projects")
    Call<List<ProjectResponse>> getProjectsByEmployee(@Path("id") Long employeeId);

    @GET("api/training")
    Call<List<TrainingResponse>> getAllTrainings();

    @PUT("api/training/{id}/apply")
    Call<TrainingResponse> applyForTraining(@Path("id") Long trainingId, @Query("employeeId") Long employeeId);

    // Advance Salary
    @POST("api/advances/save")
    Call<AdvanceResponse> saveAdvance(@Body AdvanceRequest request);

    @GET("api/advances/employee/{employeeId}")
    Call<List<AdvanceResponse>> getAdvancesByEmployee(@Path("employeeId") Long employeeId);

}
