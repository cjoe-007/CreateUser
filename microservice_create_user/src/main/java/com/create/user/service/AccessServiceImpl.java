package com.create.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.create.user.dao.AccessDao;
import com.create.user.model.User;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	AccessDao dao;

	@Override
	public void createUser(User user) {
		dao.createUser(user);
	}
	
	@Override
	public List<User> findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	@Override
	public User findByToken(String token) {
		return dao.findByToken(token);
	}
}
