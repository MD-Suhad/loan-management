package com.example.loanManagement.dto;


import lombok.Data;

@Data
public class LoanRequestDTO {
    private String customerName;
    private double principalAmount;
    private double interestRate;
    private int tenureMonths;

}
