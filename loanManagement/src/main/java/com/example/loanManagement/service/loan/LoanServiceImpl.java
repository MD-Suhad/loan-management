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

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        BigDecimal principal = BigDecimal.valueOf(requestDTO.getPrincipalAmount());
        BigDecimal annualRate = BigDecimal.valueOf(requestDTO.getInterestRate());
        int tenure = requestDTO.getTenureMonths();
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal onePlusRPowerN = monthlyRate.add(BigDecimal.ONE).pow(tenure);
        BigDecimal emi = principal.multiply(monthlyRate).multiply(onePlusRPowerN)
                .divide(
                        onePlusRPowerN.subtract(BigDecimal.ONE),
                        10,
                        RoundingMode.HALF_UP
                );
        emi = emi.setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalExpected = emi
                .multiply(BigDecimal.valueOf(tenure))
                .setScale(2, RoundingMode.HALF_UP);

        loan.setCustomerName(requestDTO.getCustomerName());
        loan.setPrincipalAmount(principal.setScale(2, RoundingMode.HALF_UP).doubleValue());
        loan.setInterestRate(annualRate.setScale(2, RoundingMode.HALF_UP).doubleValue());
        loan.setTenureMonths(tenure);
        loan.setEmi(emi.doubleValue());
        loan.setTotalExpectedAmount(totalExpected.doubleValue());
        loan.setRemainingBalance(totalExpected.doubleValue());
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setCreatedDate(LocalDate.now());

        return loanRepository.save(loan);
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
