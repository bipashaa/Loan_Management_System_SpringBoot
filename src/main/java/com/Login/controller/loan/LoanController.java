package com.Login.controller.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Login.model.loan.Loan;
import com.Login.service.loan.LoanService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class LoanController {

	@Autowired
	private LoanService service;

	// Get All loan details
	@GetMapping("/get-loan")
	public ResponseEntity<?> getAllLoan() {
		List<Loan> allloan = service.getAllLoan();
		return new ResponseEntity<>(allloan, HttpStatus.OK);
	}

	// Get loan details by loan number
	@GetMapping("/get-loan/{loanNo}")
	public ResponseEntity<?> getLoan(@PathVariable("loanNo") Long loanNo) throws Exception {
		return new ResponseEntity<>(service.getLoanByLoanNo(loanNo), HttpStatus.OK);
	}

	// add new loan details
	@PostMapping("/add-loan")
	public ResponseEntity<?> saveLoan(@RequestBody Loan loan) {
		try {
			System.out.println("controller invoked");
			return new ResponseEntity<>(service.addLoan(loan), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	// delete existing loan details
	@DeleteMapping("/delete-loan/{loanNo}")
	public ResponseEntity<?> deleteNews(@PathVariable("loanNo") Long loanNo) throws Exception {

		ResponseEntity<?> entity;
		Boolean flag = service.deleteLoan(loanNo);
		if (flag) {
			entity = new ResponseEntity<>("Deleted Successfully ", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>("Loan not found for this LoanNo", HttpStatus.NOT_FOUND);
		}
		return entity;
	}

	// update existing loan details
	@PutMapping("/update-loan/{loanNo}")
	public Loan updateLoan(@RequestBody Loan loan, @PathVariable("loanNo") Long loanNo) {
		return service.updateLoan(loan, loanNo);
	}

		
}
