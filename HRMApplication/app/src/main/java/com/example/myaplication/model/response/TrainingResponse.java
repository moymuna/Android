package com.example.myaplication.model.response;

import lombok.Data;

@Data
public class TrainingResponse {
    private Long id;
    private String trainingTitle;
    private String startDate;
    private String endDate;
    private Long employeeId;
    private String employeeName;
    private Long departmentId;
    private String departmentName;
    private String status;
    private String rejectionReason;
}
