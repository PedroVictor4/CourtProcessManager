package controller.dto;

import java.util.List;

import model.Processo;

public class ClienteDto {

	public String pessoaCpf;
	public String nome;
	public List<Processo> processos;

	public ClienteDto() {

	}

	public ClienteDto(String pessoaCpf, String nome, List<Processo> processos) {
		super();
		this.pessoaCpf = pessoaCpf;
		this.nome = nome;
		this.processos = processos;
	}

	public String getPessoaCpf() {
		return pessoaCpf;
	}

	public void setPessoaCpf(String pessoaCpf) {
		this.pessoaCpf = pessoaCpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

}
