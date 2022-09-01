package com.create.user.service;

import java.util.List;

import com.create.user.model.User;

public interface AccessService {
	void createUser(User user);
	List<User> findByEmail(String email);
	User findByToken(String token);
}
