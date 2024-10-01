package util;

import exception.CPFException;

public class VerificaCPF {

	public static boolean isCPFValido(String cpf) throws CPFException {

		cpf = cpf.replaceAll("\\D", "");

		if (cpf.length() != 11) {
			throw new CPFException("O CPF deve conter 11 dígitos.");
		}

		if (cpf.matches("(\\d)\\1{10}")) {
			throw new CPFException("O CPF não pode ter todos os dígitos iguais.");
		}

		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * (10 - i);
		}

		int primeiroVerificador = 11 - (soma % 11);
		if (primeiroVerificador >= 10) {
			primeiroVerificador = 0;
		}

		if (primeiroVerificador != (cpf.charAt(9) - '0')) {
			throw new CPFException("Primeiro dígito verificador inválido.");
		}

		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += (cpf.charAt(i) - '0') * (11 - i);
		}

		int segundoVerificador = 11 - (soma % 11);
		if (segundoVerificador >= 10) {
			segundoVerificador = 0;
		}

		if (segundoVerificador != (cpf.charAt(10) - '0')) {
			throw new CPFException("Segundo dígito verificador inválido.");
		}

		return true;
	}
}
