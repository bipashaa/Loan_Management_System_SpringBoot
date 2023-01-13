package com.Login.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Login.model.User;
import com.Login.model.UserRole;
import com.Login.repo.RoleRepository;
import com.Login.repo.UserRepository;
import com.Login.service.UserService;

//creating user
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository user;

	@Autowired
	private RoleRepository role;

	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
		User local = this.user.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("User is already there!");
			throw new Exception("User is already there!");
		} else {
			// create user
			for (UserRole ur : userRoles) {
				role.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.user.save(user);

		}
		return local;
	}

	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.user.findByUsername(username);
	}

}
