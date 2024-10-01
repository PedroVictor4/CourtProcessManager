package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import controller.dto.AdvogadoDto;
import exception.AdvogadoException;
import exception.AdvogadoExisteException;
import exception.CPFException;
import exception.EmailException;
import exception.PessoaException;
import exception.TelefoneException;
import model.Advogado;
import util.VerificadorNulo;

public class AdvogadoController implements Serializable {

	private static final long serialVersionUID = -3812387256652362916L;

	Map<String, Advogado> advogados = null;

	public AdvogadoController() {
		advogados = new TreeMap<>();
	}

	public void criarAdvogado(String nome, String email, String numero, String cpf, String registro)
			throws CPFException,   PessoaException, AdvogadoException, AdvogadoExisteException, TelefoneException, EmailException {
		// Criando o objeto Advogado

		long longnumero;
		Advogado advogado;
		if(VerificadorNulo.isNull(cpf)) {
			throw new PessoaException("O CPF não pode ser vazio");
		}
		if(VerificadorNulo.isNull(registro)) {
			throw new AdvogadoException("O registro não pode ser vazio");
		}
		if (!cpf.matches("\\d+")) {
			throw new PessoaException("O CPF tem letras");
		}
		if (!registro.matches("\\d+")) {
			throw new AdvogadoException("O Registro tem letra!");
		}
		verificaExistenciaCpfAdvogado(cpf);

		System.out.println("Fisica");
		verificaExistenciaAdvogado(registro);
		if (numero.isBlank() == false && email.isBlank() == true) {
			if (!numero.matches("\\d+")) {
				System.out.println("Entrei2 " + numero);
				throw new AdvogadoException("O número tem letra!");
			} else {
				System.out.println("Entrei para transformar em long");
				longnumero = Long.parseLong(numero);
			}
			advogado = new Advogado(nome, longnumero, cpf, registro);
			advogados.put(registro, advogado);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			MainController.save();
		} else if (email.isBlank() == false && numero.isBlank() == true) {
			advogado = new Advogado(nome, email, cpf, registro);
			advogados.put(registro, advogado);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			MainController.save();
		} else if (email.isBlank() == true && numero.isBlank() == true) {
			throw new AdvogadoException("Vocẽ precisa colocar pelo menos 1 modo de comunicação");
		} else {
			if (!numero.matches("\\d+")) {
				System.out.println("Entrei2 " + numero);
				throw new AdvogadoException("O número tem letra!");
			} else {
				System.out.println("Entrei para transformar em long");
				longnumero = Long.parseLong(numero);
			}
			advogado = new Advogado(nome, email, longnumero, cpf, registro);
			advogados.put(registro, advogado);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			MainController.save();
		}

		for (Advogado item : advogados.values()) {
			System.out.println(String.format("Nome: " + item.getNome()));
		}
	}

	private int verificaExistenciaAdvogado(String registro) throws AdvogadoException, AdvogadoExisteException {
		// Verifica se o registro existe no map
		if (advogados.containsKey(registro)) {
			System.out.println("Advogado com registro " + registro + " já existe.");
			throw new AdvogadoExisteException("O advogado existe!");
		} else {
			return 0;
		}
	}

	private int verificaExistenciaCpfAdvogado(String cpf) throws AdvogadoException, AdvogadoExisteException {
		// Verifica se o cpf existe no map
		for (Advogado advogado : advogados.values()) {
			if (advogado.getCadastroRf().equals(cpf)) {
				System.out.println("Advogado com registro " + cpf + " já existe.");
				throw new AdvogadoExisteException("O CPF do advogado existe!");
			}
		}
		return 0;
	}

	public List<AdvogadoDto> getAdvogadoDto() {
		List<AdvogadoDto> lista = new ArrayList<AdvogadoDto>();
		
		for (Advogado advogado : advogados.values()) {
			AdvogadoDto advogadoDto = new AdvogadoDto();
			advogadoDto.setCpf(advogado.getCadastroRf());
			advogadoDto.setNome(advogado.getNome());
			advogadoDto.setRegistro(advogado.getRegistro());
			advogadoDto.setEmail(advogado.getEmail());
			advogadoDto.setTelefone(advogado.getTelefone());
			lista.add(advogadoDto);
		}
		return lista;
	}
	public List<Advogado> getAdvogados() {
		List<Advogado> lista = new ArrayList<>();
		for (Advogado advogado : advogados.values()) {
			lista.add(advogado);
		}
		return lista;
	}
	public Advogado getAdvogadoPorRegistro(String registro) throws AdvogadoException {
		if(registro == null || advogados.get(registro) == null) {
			throw new AdvogadoException("O advogado não pode ser vazio !");
		}
		return advogados.get(registro);
	}
}
