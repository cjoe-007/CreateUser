package com.create.user.interceptor;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.create.user.model.User;
import com.create.user.service.AccessService;

@Component
public class Validator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	AccessService service;

	public String validateEmail(String email) {
		String message = "Email no valido";
		String messageOk = "Email valido";
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);

		if (mather.find() == false) {
			return message;
		}

		if (service.findByEmail(email).size() > 0) {
			return "El correo ya esta registrado";
		}

		return messageOk;
	}

	public String validatePassword(String password) {
		String message = "Password valido";
		ValidatePassword validatePassword = new ValidatePassword();
		String validarPassword = validatePassword.validarPassword(password);
		if (validarPassword != message) {
			return validarPassword;
		}
		return message;
	}

	public String createUser(User user) {
		String message = "usuario creado";
		user.setCreated(new Date(System.currentTimeMillis()));
		user.setLast_login(new Date(System.currentTimeMillis()));
		user.setIsactive("ACT");
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));

		service.createUser(user);
		return message;
	}

	public User findUser(String token) {
		return service.findByToken(token);
	}
}
