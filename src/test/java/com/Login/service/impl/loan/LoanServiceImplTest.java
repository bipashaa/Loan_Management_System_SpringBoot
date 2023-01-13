package com.Login.service.impl.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Login.model.loan.Loan;
import com.Login.repo.loan.LoanRepository;

public class LoanServiceImplTest {

	@Mock
	LoanRepository rep;

	Loan loan;

	@InjectMocks
	LoanServiceImpl loanService;

	List<Loan> userList = null;
	Optional<Loan> options;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		loan = new Loan();
		loan.setLoanNo(111L);
		loan.setFirstName("Bipasha");
		loan.setLastName("Dutta");
		loan.setLoanAmount(500000L);
		loan.setLoanType("Personal Loan");
		loan.setLoanTerm("2 years");
		loan.setContactNo(9078563412L);
		loan.setEmailId("abc@abc.com");
		loan.setAddress("India");

		options = Optional.of(loan);

	}

	@Test
	public void addLoan_test() {
		when(rep.save(loan)).thenReturn(loan);
		boolean lsaved = loanService.addLoan(loan);
		assertEquals(true, lsaved);
	}

	@Test
	public void updateLoan() throws Exception {
		when(rep.findById(111L)).thenReturn(options);
		Loan fetchloan = loanService.updateLoan(loan, loan.getLoanNo());
		assertEquals(userList, fetchloan);
	}

	@Test
	public void deleteLoan_test() throws Exception {
		when(rep.findById(111L)).thenReturn(options);
		boolean flag = loanService.deleteLoan(loan.getLoanNo());
		assertEquals(true, flag);
	}

	@Test
	public void getLoanById_test() throws Exception {
		when(rep.findById(111L)).thenReturn(options);
		Loan fetchedLoan = loanService.getLoanByLoanNo(loan.getLoanNo());
		assertEquals(loan, fetchedLoan);
	}
	
	@Test
	public void getAllLoan_test() {
		when(rep.findAll()).thenReturn(userList);
		List<Loan> fetchedLoan = loanService.getAllLoan();
		assertEquals(userList, fetchedLoan);
	}
}
