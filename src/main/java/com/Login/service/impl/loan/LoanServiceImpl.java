package com.Login.service.impl.loan;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Login.model.loan.Loan;
import com.Login.repo.loan.LoanRepository;
import com.Login.service.loan.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepo;

	@Override
	public List<Loan> getAllLoan() {
		// TODO Auto-generated method stub
		return loanRepo.findAll();
	}

	@Override
	public Loan getLoanByLoanNo(Long loanNo) throws Exception {
		// TODO Auto-generated method stub
		Optional<Loan> opt = loanRepo.findById(loanNo);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new Exception("LOAN DETAILS NOT FOUND");
		}
	}

	@Override
	public boolean addLoan(Loan loan) {
		// TODO Auto-generated method stub
		loanRepo.save(loan);
		return true;
	}

	@Override
	public boolean deleteLoan(Long loanNo) throws Exception {
		// TODO Auto-generated method stub
		Optional<Loan> opt = loanRepo.findById(loanNo);
		if (opt.isPresent()) {
			loanRepo.deleteById(loanNo);
			return true;

		} else {
			throw new Exception("NOT FOUND");
		}
	}

	@Override
	public Loan updateLoan(Loan loan, Long loanNo) {
		// TODO Auto-generated method stub
		Loan loan1 = loanRepo.findById(loanNo).get();
		loan1.setFirstName(loan.getFirstName());
		loan1.setLastName(loan.getLastName());
		loan1.setLoanAmount(loan.getLoanAmount());
		loan1.setLoanTerm(loan.getLoanTerm());
		loan1.setLoanType(loan.getLoanType());
		loan1.setContactNo(loan.getContactNo());
		loan1.setEmailId(loan.getEmailId());
		loan1.setAddress(loan.getAddress());
		return loanRepo.save(loan1);

	}	

}
