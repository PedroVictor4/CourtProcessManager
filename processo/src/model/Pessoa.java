package model;

import java.io.Serializable;

import exception.EmailException;
import exception.PessoaException;
import exception.TelefoneException;
import util.VerificaEmail;
import util.VerificaTelefone;
import util.VerificadorNulo;

public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 6073609040816063526L;
	private String nome;
	private String email;
	private long telefone;

	public Pessoa(String nome, String email, long telefone) throws PessoaException, TelefoneException, EmailException {
		super();
		if (VerificadorNulo.isNull(nome)) {
			throw new PessoaException("O nome não pode ser vazio");
		}
		this.nome = nome;
		VerificaEmail.validateEmail(email);

		this.email = email;
		VerificaTelefone.isTelefoneValido(telefone);
		this.telefone = telefone;
	}

	public Pessoa(String nome, String email) throws PessoaException, EmailException {
		super();
		if (VerificadorNulo.isNull(nome)) {
			throw new PessoaException("O nome não pode ser vazio");
		}
		this.nome = nome;
		VerificaEmail.validateEmail(email);

		this.email = email;
	}

	public Pessoa(String nome, long telefone) throws PessoaException, TelefoneException {
		super();
		if (VerificadorNulo.isNull(nome)) {
			throw new PessoaException("O nome não pode ser vazio");
		}
		this.nome = nome;
		VerificaTelefone.isTelefoneValido(telefone);
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public abstract String getCadastroRf();

	@Override

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Nome: %s\n", nome));
		if (email != null) {
			sb.append("Email: ").append(email).append("\n");
		}
		if (telefone != 0) {
			sb.append("Telefone: ").append(telefone).append("\n");
		}
		sb.append(String.format("\nRegistro na Receita: %s", this.getCadastroRf()));
		return sb.toString();
	}

}
