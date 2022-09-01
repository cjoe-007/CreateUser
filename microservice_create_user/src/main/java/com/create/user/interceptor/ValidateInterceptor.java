package com.create.user.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
@Component
public class ValidateInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 // Read from request
		ContentCachingRequestWrapper request1 = new ContentCachingRequestWrapper(request); 
	    StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request1.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	        buffer.append(System.lineSeparator());
	    }
	    String data = buffer.toString();
	    ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(data);
            String email = node.get("email").asText();
            String password = node.get("password").asText();
            validateEmail(email, response);
            validatePassword(password, response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	public void validateEmail(String email, HttpServletResponse response) throws IOException{
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		 
        if (mather.find() == false) {
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	response.getWriter().write("Email mal formado");
        	response.getWriter().flush();
        	
        }
	}
	
	public void validatePassword(String password, HttpServletResponse response) throws IOException{
		ValidatePassword validatePassword = new ValidatePassword();
		 String validarPassword = validatePassword.validarPassword(password);
        if (validarPassword != "Password valido") {
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	response.getWriter().write(validarPassword);
        	response.getWriter().flush();
        	
        }
	}

}
