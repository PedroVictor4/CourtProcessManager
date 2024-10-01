package model;

import exception.CPFException;
import exception.EmailException;
import exception.PessoaException;
import exception.TelefoneException;
import util.VerificaCPF;

public class PessoaFisica extends Pessoa {

	
	private static final long serialVersionUID = 7494708313384089117L;
	private final String cpf;

	public PessoaFisica(String nome, String email, long telefone, String cpf) throws CPFException, PessoaException, TelefoneException, EmailException {
		super(nome, email, telefone);
		VerificaCPF.isCPFValido(cpf);
		this.cpf = cpf;

	}
	public PessoaFisica(String nome, String email, String cpf) throws CPFException, PessoaException, EmailException{
		super(nome, email);
		VerificaCPF.isCPFValido(cpf);
		this.cpf = cpf;
		

	}
	public PessoaFisica(String nome, long telefone, String cpf) throws CPFException, PessoaException, TelefoneException {
		super(nome, telefone);
		VerificaCPF.isCPFValido(cpf);
        this.cpf = cpf;
        

	}

	@Override
	public String getCadastroRf() {
		return cpf;
	}
	
}
