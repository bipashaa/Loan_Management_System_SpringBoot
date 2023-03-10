package com.Login.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private boolean enabled = true;

	// user can have many roles
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
		@JsonIgnore
		private Set<UserRole> userRoles = new HashSet<UserRole>();

		public User() {
		}

		public User(Long id, String username, String password) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public Set<UserRole> getUserRoles() {
			return userRoles;
		}

		public void setUserRoles(Set<UserRole> userRoles) {
			this.userRoles = userRoles;
		}

		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			Set<Authority> set = new HashSet<>();

			this.userRoles.forEach(userRole -> {

				set.add(new Authority(userRole.getRole().getRoleName()));
			});

			return set;
		}

		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

}
