package com.Login.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.Login.config.JwtUtil;
import com.Login.model.Request;
import com.Login.model.User;
import com.Login.service.impl.UserDetailsServiceImpl;
import com.Login.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@EnableWebSecurity
public class AuthenticateControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private AuthenticationManager authenticationManager;

	@Mock
	private UserDetailsServiceImpl userService;

	@Mock
	private JwtUtil jwtUtil;

	@InjectMocks
	private AuthenticateController authController; // from where we will assert

//	@Test
//	public void generateToken_Test() throws Exception {
//
//		final String jwtToken = "jj.ww.tt";
//		ResponseEntity<String> response = new ResponseEntity<String>(jwtToken, HttpStatus.OK);
//		Request request = new Request();
//		request.setUsername("bipasha");
//		request.setPassword("bipasha");
//
//		User user = new User(1L, "bipasha", "bipasha");
//		
//		when(userService.loadUserByUsername(request.getUsername())).thenReturn(user);
//
//		when(authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())))
//				.thenReturn(null);
//		
//		when(jwtUtil.generateToken(user)).thenReturn(jwtToken);
//
//	}
//
//	public static String asJsonString(final Object obj) {
//		try {
//			ObjectMapper objmapper = new ObjectMapper();
//			objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//			objmapper.registerModule(new JavaTimeModule());
//			return objmapper.writeValueAsString(obj);
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

}