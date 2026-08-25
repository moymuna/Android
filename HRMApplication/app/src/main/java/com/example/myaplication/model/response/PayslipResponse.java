package com.example.myaplication.model.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PayslipResponse {
    private Long id;
    private Integer month;
    private Integer year;
    private BigDecimal grossSalary;
    private BigDecimal totalDeductions;
    private BigDecimal netSalary;
    private Integer paidDays;
    private Integer lopDays;
    private Integer unpaidLeaveDays;
    private BigDecimal leaveDeduction;
    private BigDecimal advanceDeduction;
    private BigDecimal lopDeduction;
    private BigDecimal providentFund;
    private BigDecimal professionalTax;
    private BigDecimal incomeTax;
    private String status;
    private String generatedAt;
    private String paidAt;
    private Long employeeId;
    private String employeeName;
    private String bankName;
    private String bankAccountNumber;
    private Long payrollId;
}
