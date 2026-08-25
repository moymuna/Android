package com.example.myaplication.model.response;

import lombok.Data;

@Data
public class AttendanceResponse {
    private Long id;
    private String date;
    private String checkInTime;
    private String checkOutTime;
    private Double workedHours;
    private String status;
    private Long employeeId;
    private String employeeName;
}
