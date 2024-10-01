package model;

import exception.CnpjException;
import exception.EmailException;
import exception.PessoaException;
import exception.TelefoneException;
import util.VerificaCnpj;
import util.VerificadorNulo;

public class PessoaJuridica extends Pessoa {
	
	private static final long serialVersionUID = 4452793162086482488L;
	private final String cnpj;
	private PessoaFisica preposto;
	public PessoaJuridica(String nome, String email, long telefone, String cnpj, PessoaFisica preposto) throws PessoaException, CnpjException, TelefoneException, EmailException{
		super(nome, email, telefone);
		VerificaCnpj.isCNPJValido(cnpj);
		this.cnpj = cnpj;
		if(VerificadorNulo.isNull(preposto)) {
			throw new PessoaException("O preposto não pode ser vazio");
		}
		this.preposto = preposto;
	}
	
	public PessoaJuridica(String nome, String email, String cnpj, PessoaFisica preposto) throws PessoaException, CnpjException, EmailException {
		super(nome, email);
		VerificaCnpj.isCNPJValido(cnpj);
		this.cnpj = cnpj;
		if(VerificadorNulo.isNull(preposto)) {
			throw new PessoaException("O preposto não pode ser vazio");
		}
		this.preposto = preposto;
	}
	
	public PessoaJuridica(String nome, long telefone, String cnpj, PessoaFisica preposto) throws PessoaException, CnpjException, TelefoneException {
		super(nome, telefone);
		VerificaCnpj.isCNPJValido(cnpj);
		this.cnpj = cnpj;
		if(VerificadorNulo.isNull(preposto)) {
			throw new PessoaException("O preposto não pode ser vazio");
		}
		this.preposto = preposto;
	}

	public PessoaFisica getPreposto() {
		return preposto;
	}

	public void setPreposto(PessoaFisica preposto) throws PessoaException {
		if(VerificadorNulo.isNull(preposto)) {
			throw new PessoaException("O preposto não pode ser vazio");
		}
		this.preposto = preposto;
	}

	@Override
	public String getCadastroRf() {
		return this.cnpj;

	}

}
	