package com.create.user.dao;

import java.util.List;

import com.create.user.model.User;

public interface AccessDao {
	
	void createUser(User user);
	List<User> findByEmail(String email);
	User findByToken(String token);
}
