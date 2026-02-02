package com.example.loanManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private double principalAmount;

    private double interestRate;

    private int tenureMonths;

    // Calculated fields
    private double emi;

    private double totalExpectedAmount;

    private double remainingBalance;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private LocalDate createdDate;

}
