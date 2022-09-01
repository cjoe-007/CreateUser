package com.create.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.create.user.model.User;

public interface AccessJpaSpring extends JpaRepository<User, Integer> {
	User findByName(String name);
	List<User> findByEmail(String email);
	User findByToken(String token);
}
