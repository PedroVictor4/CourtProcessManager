package util;

import java.util.regex.Pattern;

import exception.EmailException;

public class VerificaEmail {

	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	public static void validateEmail(String email) throws EmailException {
		if (email == null || !pattern.matcher(email).matches()) {
			throw new EmailException("Email inválido: " + email);
		}
	}

}
