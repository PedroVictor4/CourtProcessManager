package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.ProcessoException;
import util.VerificadorNulo;

public class Processo implements Serializable {

	private static final long serialVersionUID = -3373483382485789407L;
	private final long numero;

	private final Date data;
	private EFaseProcesso faseProcesso;
	private final Tribunal tribunal;
	private final Conta conta;
	private final Cliente cliente;
	// Como no toString está dizendo: "das partes" , imagino que devo guardar as
	// duas partes
	private final Pessoa parteContraria;
	private List<Audiencia> audiencias = new ArrayList<>();

	public Processo(long numero, Tribunal tribunal, Cliente cliente, Pessoa parteContraria, Date data)
			throws ProcessoException {
		if (cliente.getPessoa().getCadastroRf() == parteContraria.getCadastroRf()) {
			throw new ProcessoException("Uma pessoa não pode se processar !");
		}
		if (VerificadorNulo.isNull(numero)) {
			throw new ProcessoException("O número não pode ser vazio");
		}
		this.numero = numero;
		this.faseProcesso = EFaseProcesso.INCIAL;
		if (VerificadorNulo.isNull(data)) {
			throw new ProcessoException("A data não pode ser vazia");
		}

		this.data = data;
		this.conta = new Conta();
		if (VerificadorNulo.isNull(tribunal)) {
			throw new ProcessoException("O tribunal não pode ser vazio");
		}
		this.tribunal = tribunal;
		if (VerificadorNulo.isNull(cliente)) {
			throw new ProcessoException("O cliente não pode ser vazio");
		}
		this.cliente = cliente;
		this.cliente.addProcesso(this);
		if (VerificadorNulo.isNull(parteContraria)) {
			throw new ProcessoException("A parte contraria  não pode ser vazia");
		}
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

	public List<Audiencia> getAudiencias() {
		return audiencias;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Pessoa getParteContraria() {
		return parteContraria;
	}

	public StringBuilder getAudienciasString() {
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

	public EFaseProcesso getFaseProcesso() {
		return faseProcesso;
	}

	public void setFaseProcesso(EFaseProcesso faseProcesso) throws ProcessoException {
		if (faseProcesso == EFaseProcesso.INCIAL) {
			throw new ProcessoException("Ele só pode ser inicial na criação");
		}
		this.faseProcesso = faseProcesso;
	}

	public Date getData() {
		return data;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Numero: ").append(numero + "\n");
		sb.append("Data: ").append(data + "\n");
		sb.append("Fase do Processo: ").append(faseProcesso + "\n");
		sb.append("Tribunal: \n").append(tribunal).append("\n");
		sb.append("\nConta do processo: \n").append(conta.getExtrato().toString() + "\n");
		sb.append("\nCliente: \n").append(cliente.toString() + "\n");
		sb.append("\nParte contraria: \n").append(parteContraria.toString() + "\n");

		return sb.toString();
	}

}
