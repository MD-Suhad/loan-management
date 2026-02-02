package com.example.loanManagement.repository;

import com.example.loanManagement.model.Loan;
import com.example.loanManagement.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByStatus(LoanStatus status);
}
