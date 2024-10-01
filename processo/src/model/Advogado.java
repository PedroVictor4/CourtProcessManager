package model;

import exception.CPFException;
import exception.EmailException;
import exception.PessoaException;
import exception.TelefoneException;
import util.VerificadorNulo;

public class Advogado extends PessoaFisica {

	private static final long serialVersionUID = 485167375720379975L;
	private final String registro;

	public Advogado(String nome, String email, long telefone, String cpf, String registro)
			throws CPFException, PessoaException, TelefoneException, EmailException {
		super(nome, email, telefone, cpf);
		if (VerificadorNulo.isNull(registro)) {
			throw new PessoaException("O registro não pode ser vazio");
		}
		this.registro = registro;

	}

	public Advogado(String nome, long telefone, String cpf, String registro)
			throws CPFException, PessoaException, TelefoneException {
		super(nome, telefone, cpf);
		if (VerificadorNulo.isNull(registro)) {
			throw new PessoaException("O registro não pode ser vazio");
		}
		this.registro = registro;

	}

	public Advogado(String nome, String email, String cpf, String registro)
			throws CPFException, PessoaException, EmailException {
		super(nome, email, cpf);
		if (VerificadorNulo.isNull(registro)) {
			throw new PessoaException("O registro não pode ser vazio");
		}
		this.registro = registro;

	}

	public String getRegistro() {
		return registro;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nRegistro: ").append(registro + "\n");
		return sb.toString();
	}
}
