package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import controller.dto.PessoaDto;
import exception.CPFException;
import exception.CnpjException;
import exception.EmailException;
import exception.PessoaException;
import exception.PessoaExisteException;
import exception.TelefoneException;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import util.VerificadorNulo;

public class PessoaController implements Serializable {

	private static final long serialVersionUID = 1765114375657332531L;
	Map<String, Pessoa> pessoas = null;

	public PessoaController() {
		pessoas = new TreeMap<>();
	}

	public List<String> getPessoasToString() {
		List<String> lista = new ArrayList<>();
		for (Pessoa pessoa : pessoas.values()) {
			lista.add(String.format("%s - Cadastro na RF: %s", pessoa.getNome(), pessoa.getCadastroRf()));
		}
		return lista;
	}

	public List<PessoaFisica> getPessoasFisicas() {
		List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
		for (Pessoa pessoa : pessoas.values()) {
			if (pessoa instanceof PessoaFisica) {
				lista.add((PessoaFisica) pessoa);
			}

		}
		return lista;
	}

	public List<Pessoa> getPessoas() {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		for (Pessoa pessoa : pessoas.values()) {
			lista.add(pessoa);

		}
		return lista;
	}

	public Pessoa getPessoaPorCadastro(String cadastro) throws PessoaException {
		if (cadastro == null || !pessoas.containsKey(cadastro)) {
			throw new PessoaException("Pessoa escolhida inválida");
		}
		return pessoas.get(cadastro);

	}

	public List<PessoaDto> getPessoaDto() {

		List<PessoaDto> lista = new ArrayList<PessoaDto>();
		/*
		 * Para printar o preposto também era só ve se era uma instância da  classe converter e printar 
		 * */
		for (Pessoa pessoa : pessoas.values()) {
			PessoaDto pessoaDto = new PessoaDto();
			pessoaDto.setCadastro(pessoa.getCadastroRf());
			pessoaDto.setNome(pessoa.getNome());
			pessoaDto.setEmail(pessoa.getEmail());
			pessoaDto.setTelefone(pessoa.getTelefone());
			lista.add(pessoaDto);
		}
		

		return lista;
	}

	public List<PessoaDto> getPessoaFisicaDto() {

		List<PessoaDto> lista = new ArrayList<PessoaDto>();

		for (Pessoa pessoa : pessoas.values()) {
			if(pessoa instanceof PessoaFisica) {
				PessoaDto pessoaDto = new PessoaDto();
				pessoaDto.setCadastro(pessoa.getCadastroRf());
				pessoaDto.setNome(pessoa.getNome());
				pessoaDto.setEmail(pessoa.getEmail());
				pessoaDto.setTelefone(pessoa.getTelefone());
				System.out.println("O cpf da pessoa física é : " + pessoa.getCadastroRf());
				lista.add(pessoaDto);
			}
		}

		return lista;
	}

	Pessoa pessoa;

	public void criarPessoa(String nome, String email, String numero, String documento, String tipoPessoa,
			String cpfPreposto) throws CPFException, PessoaException, PessoaExisteException, CnpjException, TelefoneException, EmailException {
		// Criando o objeto Pessoa
		Pessoa pessoa;
		long longnumero;
		
		if(VerificadorNulo.isNull(documento)) {
			throw new PessoaException("O documento não pode ser vazio");
		}

		if ("Física".equals(tipoPessoa)) {
			System.out.println("Fisica");
			verificaExistenciaPessoa(documento);
			if (numero.isBlank() == false && email.isBlank() == true) {
				if (!numero.matches("\\d+")) {
					
					throw new PessoaException("O número tem letra!");
				} else {
					
					longnumero = Long.parseLong(numero);
				}
				pessoa = new PessoaFisica(nome, longnumero, documento);
			} else if (email.isBlank() == false && numero.isBlank() == true) {
				pessoa = new PessoaFisica(nome, email, documento);
			}else if(email.isBlank() == true && numero.isBlank() == true){
				throw new PessoaException("Vocẽ precisa colocar pelo menos 1 modo de comunicação");
			} 
			else {
				if (!numero.matches("\\d+")) {
					throw new PessoaException("O número tem letra!");
				} else {
					
					longnumero = Long.parseLong(numero);
				}
				pessoa = new PessoaFisica(nome, email, longnumero, documento);
			}
			pessoas.put(documento, pessoa);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			MainController.save();

			for (Pessoa item : pessoas.values())
				System.out.println((String.format("Nome: " + item.getNome())));

		} else if ("Jurídica".equals(tipoPessoa)) {
			System.out.println("Jurídica");
			verificaExistenciaPessoa(documento);
			PessoaFisica preposto = null;
			//Caso a view não dẽ conta de passar uma pessoaFísica e dê algum problema nesse cast
			try {
				preposto = (PessoaFisica) this.getPessoaPorCadastro(cpfPreposto);
			}
			catch (Exception e){
				throw new PessoaException("O preposto deve ser uma pessoa Física");
			}
			if (numero.isBlank() == false && email.isBlank() == true) {
				if (!numero.matches("\\d+")) {
					throw new PessoaException("O número tem letra!");
				} else {
					longnumero = Long.parseLong(numero);
				}
				pessoa = new PessoaJuridica(nome, longnumero, documento, preposto);
			} else if (email.isBlank() == false && numero.isBlank() == true) {
				pessoa = new PessoaJuridica(nome, email, documento, preposto);
			}else if(email.isBlank() == true && numero.isBlank() == true){
				throw new PessoaException("Vocẽ precisa colocar pelo menos 1 modo de comunicação");
			}else {
				if (!numero.matches("\\d+")) {
					throw new PessoaException("O número tem letra!");
				} else {
					longnumero = Long.parseLong(numero);
				}
				pessoa = new PessoaJuridica(nome, email, longnumero, documento, preposto);
			}
			pessoas.put(documento, pessoa);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			MainController.save();

		}

	}

	private int verificaExistenciaPessoa(String documento) throws PessoaException, PessoaExisteException {
		// Verifica se o CPF existe no map
		if (pessoas.containsKey(documento)) {
			System.out.println("Pessoa com documento " + documento + " já existe.");
			throw new PessoaExisteException("A pessoa já existe!");
		} else {
			return 0;
		}
	}

}
