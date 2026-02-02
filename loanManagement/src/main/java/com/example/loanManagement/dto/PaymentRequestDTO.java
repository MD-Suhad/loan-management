package com.example.loanManagement.dto;

import lombok.Data;

@Data

public class PaymentRequestDTO {
    private Long loanId;
    private double amountPaid;

}
