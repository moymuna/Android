package com.example.myaplication.model.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AdvanceResponse {
    private Long id;
    private BigDecimal amount;
    private String requestDate;
    private String requiredByDate;
    private Integer installments;
    private BigDecimal monthlyDeduction;
    private BigDecimal recoveredAmount;
    private BigDecimal outstandingAmount;
    private String reason;
    private String status;
    private String decidedAt;
    private String disbursedAt;
    private String rejectionReason;
    private Long employeeId;
    private String employeeName;
    private String employeeCode;
}
