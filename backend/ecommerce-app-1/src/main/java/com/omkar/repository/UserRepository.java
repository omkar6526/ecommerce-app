package com.omkar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	public User findByEmail(String email);

}
