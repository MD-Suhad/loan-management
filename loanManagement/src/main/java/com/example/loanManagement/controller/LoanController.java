package com.example.loanManagement.controller;

import com.example.loanManagement.dto.LoanRequestDTO;
import com.example.loanManagement.dto.LoanSummaryDTO;
import com.example.loanManagement.model.Loan;
import com.example.loanManagement.service.exception.CustomException;
import com.example.loanManagement.service.loan.LoanService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")

public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping(("/create-loan"))
    public Loan createLoan(@RequestBody LoanRequestDTO requestDTO) {
        return loanService.createLoan(requestDTO);
    }

    @GetMapping("/all-loans")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
    @GetMapping("/all-loans-by-summary-by-loanId/{loanId}")
    public LoanSummaryDTO getLoanSummaryAll(@PathVariable Long loanId) {
        return loanService.getLoanSummary(loanId);
    }
    @DeleteMapping("/loans-delete-by-loanId/{loanId}")
    public ResponseEntity<String> getDeleteByLoan(@PathVariable Long loanId) {

        String message = loanService.getDeleteByLoan(loanId);

        return ResponseEntity.ok(message);
    }
}

