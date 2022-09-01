package com.create.user.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.create.user.dto.UserViewDTO;
import com.create.user.interceptor.Validator;
import com.create.user.model.User;
import com.create.user.service.AccessService;
import com.create.user.util.Respuesta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	AccessService service;

	@Autowired
	Validator validate;

	@Autowired
	RestTemplate template;

	String url = "http://localhost:9000";
	String user = "admin";
	String pwd = "admin";
	String token;
	HttpHeaders headers = new HttpHeaders();

	public void autenticar() {
		token = template.postForObject(url + "/login?user=" + user + "&pwd=" + pwd, null, String.class);
		headers.add("Authorization", "Bearer " + token);
	}

	@PostMapping(value = "createUser")
	public ResponseEntity<String> createUser(@RequestBody String bodyRequest) throws ParseException {
		Respuesta respuesta = new Respuesta();
		autenticar();
		ObjectMapper mapper = new ObjectMapper();
		HttpStatus status = HttpStatus.ACCEPTED;
		String message = "";
		String messageBody = "";
		try {
			User user = new User();
			JsonNode node = mapper.readTree(bodyRequest);
			user.setName(node.get("name").asText());
			user.setEmail(node.get("email").asText());
			user.setPassword(node.get("password").asText());
			JsonNode phoneNode = mapper.readTree(bodyRequest).get("phones");
			phoneNode.forEach(it -> {
				user.setNumber(it.get("number").asText());
				user.setCityCode(it.get("citycode").asText());
				user.setCountryCode(it.get("countrycode").asText());
			});
			String validatorEmail = validate.validateEmail(user.getEmail());
			String validatorPassword = validate.validatePassword(user.getPassword());
			user.setToken(token);
			if (validatorEmail != "Email valido") {
				status = HttpStatus.FORBIDDEN;
				messageBody = validatorEmail;
			} else if (validatorPassword != "Password valido") {
				status = HttpStatus.FORBIDDEN;
				messageBody = validatorPassword;
			} else if (validate.createUser(user) == "usuario creado") {
				status = HttpStatus.ACCEPTED;
				User userSave = validate.findUser(token);
				UserViewDTO userView = new UserViewDTO();
				ObjectMapper obj = new ObjectMapper();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				userView.setId(userSave.getId());
				userView.setCreated(dateFormat.format(userSave.getCreated()));
				userView.setModified(userSave.getModified());
				userView.setLast_login(userSave.getLast_login());
				userView.setToken(userSave.getToken());
				userView.setIsactive(userSave.getIsactive());
				messageBody = obj.writeValueAsString(userView);
			}
			ObjectMapper obj1 = new ObjectMapper();
			respuesta.setMessage(messageBody);
			message = obj1.writeValueAsString(respuesta);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(status).body(message);
	}
}
