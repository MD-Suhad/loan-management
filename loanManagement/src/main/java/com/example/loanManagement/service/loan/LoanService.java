package com.example.loanManagement.service.loan;

import com.example.loanManagement.dto.LoanRequestDTO;
import com.example.loanManagement.dto.LoanSummaryDTO;
import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Loan;
import com.example.loanManagement.model.Payment;

import java.util.List;

public interface LoanService {
    Loan createLoan(LoanRequestDTO requestDTO);

    Payment addPayment(PaymentRequestDTO requestDTO);

    LoanSummaryDTO getLoanSummary(Long loanId);

    List<Loan> getAllLoans();
}
