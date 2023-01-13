package com.Login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

}
