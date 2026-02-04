package com.example.loanManagement.service.payment;

import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Loan;
import com.example.loanManagement.model.LoanStatus;
import com.example.loanManagement.model.Payment;
import com.example.loanManagement.repository.LoanRepository;
import com.example.loanManagement.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final LoanRepository loanRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(LoanRepository loanRepository, PaymentRepository paymentRepository) {
        this.loanRepository = loanRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(PaymentRequestDTO requestDTO) {

        Loan loan = loanRepository.findById(requestDTO.getLoanId())
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        Payment payment = new Payment();
        payment.setLoanId(requestDTO.getLoanId());
        payment.setAmountPaid(requestDTO.getAmountPaid());
        payment.setPaymentDate(LocalDate.now());

        paymentRepository.save(payment);

        double totalPaid = paymentRepository.getTotalPaid(loan.getId());
        double remaining = loan.getTotalExpectedAmount() - totalPaid;

        loan.setRemainingBalance(Math.max(remaining, 0));

        if (remaining <= 0) {
            loan.setStatus(LoanStatus.CLOSED);
        }

        loanRepository.save(loan);

        return payment;
    }
}
