package com.example.loanManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    @Column(columnDefinition = "Decimal(10,2)")
    private double principalAmount;
    @Column(columnDefinition = "Decimal(10,2)")
    private double interestRate;

    private int tenureMonths;

    @Column(columnDefinition = "Decimal(10,2)")
    private double emi;
    @Column(columnDefinition = "Decimal(10,2)")
    private double totalExpectedAmount;
    @Column(columnDefinition = "Decimal(10,2)")
    private double remainingBalance;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private LocalDate createdDate;
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

}
