package com.Login.service;

import java.util.Set;

import com.Login.model.User;
import com.Login.model.UserRole;


public interface UserService {

	// creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	// get user by username
	public User getUser(String username);
}
