package com.example.loanManagement.repository;

import com.example.loanManagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{
    @Query("SELECT COALESCE(SUM(p.amountPaid), 0) FROM Payment p WHERE p.loanId = ?1")
    double getTotalPaid(Long loanId);

}
