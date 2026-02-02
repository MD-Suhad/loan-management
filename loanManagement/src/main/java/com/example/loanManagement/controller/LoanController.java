package com.example.loanManagement.controller;

import com.example.loanManagement.dto.LoanRequestDTO;
import com.example.loanManagement.dto.LoanSummaryDTO;
import com.example.loanManagement.model.Loan;
import com.example.loanManagement.service.loan.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")

public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping(("/create-load/"))
    public Loan createLoan(@RequestBody LoanRequestDTO requestDTO) {
        return loanService.createLoan(requestDTO);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
    @GetMapping("/{loanId}/summary")
    public LoanSummaryDTO getLoanSummary(@PathVariable Long loanId) {
        return loanService.getLoanSummary(loanId);
    }
}
