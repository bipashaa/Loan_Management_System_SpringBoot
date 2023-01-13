package com.Login.controller.loan;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Login.model.loan.Loan;
import com.Login.service.loan.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private Loan loan;

	@MockBean
	private LoanService loanService;

	@InjectMocks
	private LoanController loanCon;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(loanCon).build();
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

	}

	@Test
	public void test_saveLoan() throws Exception {
		when(loanService.addLoan(loan)).thenReturn(true);
		mockMvc.perform(post("/api/v1/add-loan").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test_updateLoan() throws Exception {
		loan.setLoanNo(111L);
		when(loanService.updateLoan((Loan) any(), eq(loan.getLoanNo()))).thenReturn(loan);
		mockMvc.perform(
				put("/api/v1/update-loan/111").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test_deleteLoan() throws Exception {
		when(loanService.deleteLoan(111L)).thenReturn(true);
		mockMvc.perform(delete("/api/v1/delete-loan/111").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test_getLoan() throws Exception {
		when(loanService.getLoanByLoanNo(111L)).thenReturn(loan);
		mockMvc.perform(get("/api/v1/get-loan/111").contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void test_getAllLoan() throws Exception {
		when(loanService.getAllLoan()).thenReturn(null);
		mockMvc.perform(get("/api/v1/get-loan").contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
	}

	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper objmapper = new ObjectMapper();
			objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objmapper.registerModule(new JavaTimeModule());
			return objmapper.writeValueAsString(obj);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
