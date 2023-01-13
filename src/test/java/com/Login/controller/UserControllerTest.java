package com.Login.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.Login.model.Role;
import com.Login.model.User;
import com.Login.model.UserRole;
import com.Login.service.UserService;
import com.Login.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	User user;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder pass;
	
	@InjectMocks
	UserController userController;

	@Test
	public void createUser() throws Exception {
		User user = new User();
		user.setUsername("bipasha");
		user.setPassword(pass.encode("bipasha"));

		Set<UserRole> roles = new HashSet<>();
		Role role = new Role(44L, "Normal");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);

		mockMvc.perform(post("/api/v1/create-user").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andDo(MockMvcResultHandlers.print());

		when(userService.createUser(user, roles)).thenReturn(user);

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
