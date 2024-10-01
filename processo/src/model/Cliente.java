package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exception.ClienteException;
import util.VerificadorNulo;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 7054263824641743233L;
	private final Pessoa pessoa;
	private List<Processo> processos;
	

	//Não tornei obrigatório o cliente ter um processo
	public Cliente(Pessoa pessoa) throws ClienteException {
		if (VerificadorNulo.isNull(pessoa)) {
			throw new ClienteException("O cliente não pode ser vazio ");
		}
		this.pessoa = pessoa;
		this.processos = new ArrayList<Processo>();
	}

	public StringBuilder getExtratoConta() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: ").append(this.pessoa.getNome()).append("\n");
		for (Processo processo : processos) {
			sb.append("Processo: \n").append(processo.toString());
			sb.append(processo.getExtrato()).append("\n");
		}
		return sb;

	}

	public double getSaldoContas() {
		double soma = 0;
		for (Processo processo : processos) {
			soma += processo.getConta().getSaldoConta();
		}
		return soma;
	}

	public void addProcesso(Processo processo) {
		this.processos.add(processo);
	}

	public void listaProcessos() {
		System.out.println("Vou printar um processo" + processos.size());
		for (Processo processo : processos) {
			System.out.println("Processo :" + processo);
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	public List<Processo> getProcessos() {
		return processos;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getPessoa().toString());
		return sb.toString();
	}

}
