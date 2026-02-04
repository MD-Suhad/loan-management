package com.example.loanManagement.service.payment;

import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Payment;

public interface PaymentService {
    Payment addPayment(PaymentRequestDTO requestDTO);
}
