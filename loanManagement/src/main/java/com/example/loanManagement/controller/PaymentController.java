package com.example.loanManagement.controller;

import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Payment;
import com.example.loanManagement.service.payment.PaymentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Payment> addPayment(@RequestBody PaymentRequestDTO requestDTO) {
        try{
            Payment payment = paymentService.addPayment(requestDTO);
            return ResponseEntity.ok(payment);
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
