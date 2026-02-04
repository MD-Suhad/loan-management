package com.example.loanManagement.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoanSummaryDTO {
    private Long loanId;
    private String customerName;
    @Column(columnDefinition = "Decimal(10,2)")
    private double totalExpectedAmount;
    @Column(columnDefinition = "Decimal(10,2)")
    private double totalPaid;
    @Column(columnDefinition = "Decimal(10,2)")
    private double remainingBalance;
    private String status;

}
