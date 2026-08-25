package com.example.myaplication.model.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AdvanceRequest {
    private BigDecimal amount;
    private String requestDate;
    private String requiredByDate;
    private Integer installments;
    private String reason;
    private Long employeeId;
}
