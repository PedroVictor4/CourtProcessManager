package model;

import java.io.Serializable;
import java.util.Date;

import exception.PagamentoException;
import util.VerificadorNulo;

public class Pagamento implements Serializable {

	
	private static final long serialVersionUID = -4949184634423034690L;
	private final Date data;
	private final EPagamento pagamento;
	private final double valor;

	public Pagamento(EPagamento pagamento, double valor, Date data) throws PagamentoException {
		if (VerificadorNulo.isNull(data)) {
			throw new PagamentoException("A data não pode ser vazia!");
		}
		this.data = data;

		if (VerificadorNulo.isNull(pagamento)) {
			throw new PagamentoException("O pagamento não pode ser vazio !");
		}
		this.pagamento = pagamento;

		if (VerificadorNulo.isNull(valor)) {
			throw new PagamentoException("O valor não pode ser vazio ! ");
		}
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public EPagamento getPagamento() {
		return pagamento;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Data: ").append(data).append("\n");
		sb.append("Forma de pagamento: ").append(pagamento).append("\n");
		sb.append("Valor: ").append(valor).append("\n");
		return sb.toString();
	}

}
