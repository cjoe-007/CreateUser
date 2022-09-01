package com.create.user.util;

import java.io.Serializable;

public class Respuesta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
