package com.Login.repo.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Login.model.loan.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
