package com.example.loanManagement.controller;

import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Payment;
import com.example.loanManagement.service.payment.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")

public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController( PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/payment-created-by-loan")
    public Payment addPayment(@RequestBody PaymentRequestDTO requestDTO) {
        return paymentService.addPayment(requestDTO);
    }
}
