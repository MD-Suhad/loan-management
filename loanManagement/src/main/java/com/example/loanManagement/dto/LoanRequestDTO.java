package com.example.loanManagement.dto;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoanRequestDTO {
    private String customerName;
    @Column(columnDefinition = "Decimal(10,2)")
    private double principalAmount;
    @Column(columnDefinition = "Decimal(10,2)")
    private double interestRate;
    private int tenureMonths;

}
