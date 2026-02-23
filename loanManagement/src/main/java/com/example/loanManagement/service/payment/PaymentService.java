package com.example.loanManagement.service.payment;

import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Payment;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistry;

public interface PaymentService {
    Payment addPayment(PaymentRequestDTO requestDTO) throws RuntimeException;
    PaymentRequestDTO show(PaymentRequestDTO paymentRequestDTO) throws RuntimeException;
}
