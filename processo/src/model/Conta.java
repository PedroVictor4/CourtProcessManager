package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.DespesaException;
import exception.PagamentoException;

public class Conta implements IConta, Serializable {

	private static final long serialVersionUID = -1998756127602111586L;
	private List<Despesa> despesas = new ArrayList<>();
	private List<Pagamento> pagamentos = new ArrayList<>();

	public Conta() {

	}

	@Override
	public void addPagamento(EPagamento formaPagamento, Date data, double valor) throws PagamentoException {
		Pagamento pagamento = new Pagamento(formaPagamento, valor, data);
		pagamentos.add(pagamento);

	}

	@Override
	public void addDespesa(String descricao, Date data, double valor) throws DespesaException {
		Despesa despesa = new Despesa(descricao, data, valor);
		despesas.add(despesa);

	}

	@Override
	public double getTotalPagamentos() {
		double soma = 0;
		for (Pagamento pagamento : pagamentos) {
			soma += pagamento.getValor();
		}
		return soma;
	}

	@Override
	public double getTotalDespesas() {

		double soma = 0;
		for (Despesa despesa : despesas) {
			soma += despesa.getValor();
		}
		return soma;
	}

	@Override
	public double getSaldoConta() {
		double soma = 0;
		for (Pagamento pagamento : pagamentos) {
			soma += pagamento.getValor();
		}
		for (Despesa despesa : despesas) {
			soma -= despesa.getValor();
		}
		return soma;
	}

	@Override
	public StringBuilder getExtrato() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\n----------------------------------------Despesas:---------------------------------------------------- \n");
		for (Despesa despesa : despesas) {
			sb.append(despesa.toString());
		}
		sb.append("\nTotal das despesas : " + this.getTotalDespesas() + "\n");
		sb.append(
				"\n--------------------------------------------------------------------------------------------------------------");
		sb.append(
				"\n---------------------------------------Pagamentos:------------------------------------------------------\n");
		for (Pagamento pagamento : pagamentos) {
			sb.append(pagamento.toString());
		}
		sb.append("\nTotal dos pagamentos : " + this.getTotalPagamentos() + "\n");
		sb.append(
				"\n--------------------------------------------------------------------------------------------------------------\n");
		sb.append("Saldo da conta do processo: " + this.getSaldoConta() + "\n");
		return sb;
	}
}
