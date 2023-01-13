package com.Login.service.loan;

import java.util.List;

import com.Login.model.loan.Loan;

public interface LoanService {

	public List<Loan> getAllLoan();

	public Loan getLoanByLoanNo(Long loanNo) throws Exception;

	public boolean addLoan(Loan loan);

	public boolean deleteLoan(Long loanNo) throws Exception;

	public Loan updateLoan(Loan loan, Long loanNo);

}
