package com.Login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Login.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
