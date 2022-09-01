package com.create.user.interceptor;

public class ValidatePassword {

	public String validarPassword(String password) {
		String message = "";
		// Especificar el numero de letras en el password
		final int MAX = 8;
		// Especificar el numero de letras mayusculas en el password
		final int MIN_Uppercase = 2;
		// Especificar el numero de letras minusculas en el password
		final int MIN_Lowercase = 2;
		// Especificar el numero de digitos en el password
		final int NUM_Digits = 2;
		// Especificar el numero de caracteres especiales en el password
		final int special = 2;
		// Contar el numero de letras mayusculas en el password
		int uppercaseCounter = 0;
		// Contar el numero de letras minusculas en el password
		int lowercaseCounter = 0;
		// Contar el numero de digitos en el password
		int digitCounter = 0;
		// Contar el numero de letras especiales en el password
		int specialCounter = 0;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c))
				uppercaseCounter++;
			else if (Character.isLowerCase(c))
				lowercaseCounter++;
			else if (Character.isDigit(c))
				digitCounter++;
			if (c >= 33 && c <= 46 || c == 64) {
				specialCounter++;
			}

		}

		if (password.length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase
				&& digitCounter >= NUM_Digits && specialCounter >= special) {
			message = "Password valido";
		} else {
			if (password.length() < MAX)
				message = "Su password no contiene lo siguiente: Al menos 8 carácteres";
			if (lowercaseCounter < MIN_Lowercase)
				message = "Su password no contiene lo siguiente: Letras minúsculas mínimas: "+MIN_Lowercase;
			if (uppercaseCounter < MIN_Uppercase)
				message = "Su password no contiene lo siguiente: Letras mayúsculas mínimas: "+MIN_Uppercase;
			if (digitCounter < NUM_Digits)
				message = "Su password no contiene lo siguiente: Número mínimo de dígitos numéricos: "+NUM_Digits;
			if (specialCounter < special)
				message = "Su password no contiene lo siguiente: La contraseña debe contener al menos "+ special + " caracteres especiales";

		}
		return message;
	}

}
