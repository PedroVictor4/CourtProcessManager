package util;

import exception.TelefoneException;

public class VerificaTelefone {

	public static boolean isTelefoneValido(long telefone) throws TelefoneException {
		// Converter o número de telefone para String
		String telefoneStr = String.valueOf(telefone);

		// Verificar se o telefone tem 10 ou 11 dígitos
		if (telefoneStr.length() < 10 || telefoneStr.length() > 11) {
			throw new TelefoneException("O telefone deve conter 10 ou 11 dígitos.");
		}

		if (telefoneStr.matches("(\\d)\\1{9,10}")) {
			throw new TelefoneException("O telefone não pode ter todos os dígitos iguais.");
		}

		int dddPrimeiroDigito = Integer.parseInt(telefoneStr.substring(0, 1));
		if (dddPrimeiroDigito < 2 || dddPrimeiroDigito > 9) {
			throw new TelefoneException("O DDD não pode começar com 0 ou 1.");
		}

		int primeiroDigitoNumero = Integer.parseInt(telefoneStr.substring(2, 3));
		if (telefoneStr.length() == 11 && primeiroDigitoNumero != 9) {
			throw new TelefoneException("O número com 11 dígitos deve começar com 9.");
		}

		return true;
	}
}
