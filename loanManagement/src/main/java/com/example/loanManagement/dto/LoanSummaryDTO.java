package com.example.loanManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoanSummaryDTO {
    private Long loanId;
    private String customerName;
    private double totalExpectedAmount;
    private double totalPaid;
    private double remainingBalance;
    private String status;

}
