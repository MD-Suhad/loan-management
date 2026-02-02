package com.example.loanManagement.service.loan;

import com.example.loanManagement.dto.LoanRequestDTO;
import com.example.loanManagement.dto.LoanSummaryDTO;
import com.example.loanManagement.dto.PaymentRequestDTO;
import com.example.loanManagement.model.Loan;
import com.example.loanManagement.model.LoanStatus;
import com.example.loanManagement.model.Payment;
import com.example.loanManagement.repository.LoanRepository;
import com.example.loanManagement.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final PaymentRepository paymentRepository;

    public LoanServiceImpl(LoanRepository loanRepository, PaymentRepository paymentRepository) {
        this.loanRepository = loanRepository;
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Loan createLoan(LoanRequestDTO requestDTO) {

        Loan loan = new Loan();

        loan.setCustomerName(requestDTO.getCustomerName());
        loan.setPrincipalAmount(requestDTO.getPrincipalAmount());
        loan.setInterestRate(requestDTO.getInterestRate());
        loan.setTenureMonths(requestDTO.getTenureMonths());
        double interest = requestDTO.getPrincipalAmount() * (requestDTO.getInterestRate() / 100);
        double totalExpected = requestDTO.getPrincipalAmount() + interest;
        double emi = totalExpected / requestDTO.getTenureMonths();

        loan.setTotalExpectedAmount(totalExpected);
        loan.setEmi(emi);
        loan.setRemainingBalance(totalExpected);

        loan.setStatus(LoanStatus.ACTIVE);
        loan.setCreatedDate(LocalDate.now());

        return loanRepository.save(loan);
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
    @Override
    public LoanSummaryDTO getLoanSummary(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        double totalPaid = paymentRepository.getTotalPaid(loanId);

        return new LoanSummaryDTO(
                loan.getId(),
                loan.getCustomerName(),
                loan.getTotalExpectedAmount(),
                totalPaid,
                loan.getRemainingBalance(),
                loan.getStatus().name()
        );
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
