package model;

import java.util.Date;

import exception.DespesaException;
import exception.PagamentoException;

public interface IConta {
	
	public void addPagamento(EPagamento pagamento, Date data, double valor	) throws PagamentoException;
	public void addDespesa(String descricao, Date data, double valor) throws DespesaException;
	public double getTotalPagamentos();
	public double getTotalDespesas();
	public double getSaldoConta();
	public StringBuilder getExtrato();
	
}
