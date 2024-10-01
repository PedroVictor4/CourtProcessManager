package model;

import java.io.Serializable;
import java.util.Date;

import exception.DespesaException;
import util.VerificadorNulo;

public class Despesa implements Serializable {

	private static final long serialVersionUID = 8621687365625096915L;
	private final Date data;
	private final String descricao;
	private final double valor;

	public Despesa(String descricao, Date data, double valor) throws DespesaException {
		if (VerificadorNulo.isNull(descricao)) {
			throw new DespesaException("A descrição não pode ser vazia !");
		}
		this.descricao = descricao;
		if (VerificadorNulo.isNull(data)) {
			throw new DespesaException("A data não pode ser vazia !");
		}
		this.data = data;
		if (VerificadorNulo.isNull(descricao)) {
			throw new DespesaException("O valor não pode ser vazio !");
		}
		this.valor = valor;

	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Data: ").append(data).append("\n");
		sb.append("Descrição: ").append(descricao).append("\n");
		sb.append("Valor: ").append(valor).append("\n");
		return sb.toString();
	}

}
