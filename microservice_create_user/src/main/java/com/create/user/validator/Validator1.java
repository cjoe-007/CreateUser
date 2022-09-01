package com.create.user.validator;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.create.user.interceptor.ValidateInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class Validator1 extends WebMvcConfigurerAdapter {
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
//	      registry.addInterceptor(new ValidateInterceptor())
//	      .addPathPatterns("/createUser");
	}
}
