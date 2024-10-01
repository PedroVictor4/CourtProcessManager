package util;

import exception.CnpjException;

public class VerificaCnpj {

	public static boolean isCNPJValido(String cnpj) throws CnpjException {

		cnpj = cnpj.replaceAll("\\D", "");

		if (cnpj.length() != 14) {
			throw new CnpjException("O CNPJ deve conter 14 dígitos.");
		}

		if (!cnpj.matches("\\d+")) {
			throw new CnpjException("O CNPJ deve conter apenas números.");
		}

		if (cnpj.matches("(\\d)\\1{13}")) {
			throw new CnpjException("O CNPJ não pode ter todos os dígitos iguais.");
		}

		int soma = 0;
		int peso = 5;

		for (int i = 0; i < 12; i++) {
			soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
			peso = (peso == 2) ? 9 : peso - 1;
		}

		int primeiroVerificador = 11 - (soma % 11);
		if (primeiroVerificador >= 10) {
			primeiroVerificador = 0;
		}

		if (primeiroVerificador != Integer.parseInt(cnpj.substring(12, 13))) {
			throw new CnpjException("Primeiro dígito verificador inválido.");
		}

		soma = 0;
		peso = 6;

		for (int i = 0; i < 13; i++) {
			soma += Integer.parseInt(cnpj.substring(i, i + 1)) * peso;
			peso = (peso == 2) ? 9 : peso - 1;
		}

		int segundoVerificador = 11 - (soma % 11);
		if (segundoVerificador >= 10) {
			segundoVerificador = 0;
		}

		if (segundoVerificador != Integer.parseInt(cnpj.substring(13, 14))) {
			throw new CnpjException("Segundo dígito verificador inválido.");
		}

		return true;
	}
}
