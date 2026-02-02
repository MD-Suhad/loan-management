package com.example.loanManagement.controller;

import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Payment;
import com.example.loanManagement.service.loan.LoanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")

public class PaymentController {
    private final LoanService loanService;

    public PaymentController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping
    public Payment addPayment(@RequestBody PaymentRequestDTO requestDTO) {
        return loanService.addPayment(requestDTO);
    }
}
