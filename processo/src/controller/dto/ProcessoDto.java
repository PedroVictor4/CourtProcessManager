package controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Audiencia;
import model.Cliente;
import model.Conta;
import model.EFaseProcesso;
import model.Pessoa;
import model.Tribunal;

public class ProcessoDto {
	public long numero;
	public Date data;
	public EFaseProcesso faseProcesso;
	public Tribunal tribunal;
	public Conta conta;
	public Cliente cliente;
	public Pessoa parteContraria;
	public List<Audiencia> audiencias = new ArrayList<>();
	public String cadastroCliente;
	public String cadastroParteContraria;

	public ProcessoDto(long numero, Date data, Tribunal tribunal, Cliente cliente, Conta conta, String cadastroCliente,
			String cadastroParteContraria) {
		this.numero = numero;
		this.data = data;
		this.tribunal = tribunal;
		this.cliente = cliente;
		this.conta = conta;
		this.cadastroCliente = cadastroCliente;
		this.cadastroParteContraria = cadastroParteContraria;
	}

	public String getCadastroCliente() {
		return cadastroCliente;
	}

	public void setCadastroCliente(String cadastroCliente) {
		this.cadastroCliente = cadastroCliente;
	}

	public String getCadastroParteContraria() {
		return cadastroParteContraria;
	}

	public void setCadastroParteContraria(String cadastroParteContraria) {
		this.cadastroParteContraria = cadastroParteContraria;
	}

	public void setParteContraria(Pessoa parteContraria) {
		this.parteContraria = parteContraria;
	}

	public ProcessoDto() {

	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public EFaseProcesso getFaseProcesso() {
		return faseProcesso;
	}

	public void setFaseProcesso(EFaseProcesso faseProcesso) {
		this.faseProcesso = faseProcesso;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	public void setTribunal(Tribunal tribunal) {
		this.tribunal = tribunal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pessoa getParteContraria() {
		return parteContraria;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setAudiencias(List<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}

	public ProcessoDto(long numero, Tribunal tribunal, Pessoa cliente, Pessoa parteContraria) {
		this.numero = numero;
		this.faseProcesso = EFaseProcesso.INCIAL;
		this.data = new Date();
		this.conta = new Conta();
		this.tribunal = tribunal;
		this.parteContraria = parteContraria;

	}

	public long getNumero() {
		return numero;
	}

	public Conta getConta() {
		return conta;
	}

	public double getTotalCustas() {
		return this.conta.getTotalDespesas();
	}

	public void addAudiencia(Audiencia audiencia) {
		audiencias.add(audiencia);
	}

	public StringBuilder getAudiencias() {
		StringBuilder sb = new StringBuilder();
		for (Audiencia audiencia : audiencias) {
			// É função da audiencia se apresentar;
			sb.append(audiencia.toString());
		}
		return sb;
	}

	public String getExtrato() {
		return this.conta.getExtrato().toString();
	}

}
