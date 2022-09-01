package com.create.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.create.user.model.User;

@Repository
public class AccessDaoImpl implements AccessDao {

	@Autowired
	AccessJpaSpring accessJpaSpring;

	
	@Override
	public void createUser(User user) {
		accessJpaSpring.save(user);
	}

	@Override
	public List<User> findByEmail(String email) {
		return accessJpaSpring.findByEmail(email);
	}
	
	@Override
	public User findByToken(String token) {
		return accessJpaSpring.findByToken(token);
	}
}
