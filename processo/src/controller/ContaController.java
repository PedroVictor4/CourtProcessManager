package controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import exception.DataException;
import exception.DespesaException;
import exception.PagamentoException;
import exception.ProcessoException;
import model.Conta;
import model.EPagamento;
import model.Processo;

public class ContaController implements Serializable {

	private static final long serialVersionUID = 1843307467781445150L;

	public void adicionarPagamento(EPagamento pagamento, double valor, String dataDigitada, long numProcesso)
			throws PagamentoException, DataException, ProcessoException {
		// Criando o objeto Pagamento
		Date dataConvertida = null;
		System.out.println("Entrei para adcionar pagamento");
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			dataConvertida = dateFormat.parse(dataDigitada);
			System.out.println(dataConvertida);
			String dataFormatada = dateFormat.format(dataConvertida);
			System.out.println("Data convertida: " + dataFormatada);
		} catch (Exception ex) {
			System.out.println("Erro ao converter a data: " + ex.getMessage());
			throw new DataException("Formato da data inválido !");
		}
		ProcessoController processoController = MainController.getProcessoController();
		System.out.println("!!!!");
		Processo processo = processoController.getProcessoPorNumero(numProcesso);
		System.out.println("Aqui!!!!");
		Conta conta = processo.getConta();
		conta.addPagamento(pagamento, dataConvertida, valor);
		JOptionPane.showMessageDialog(null, "Pagamento feito com sucesso !");
		System.out.println(conta.getTotalPagamentos());
		MainController.save();
	}

	public void adicionarDespesa(String descricao, double valor, String dataDigitada, long numProcesso)
			throws DespesaException, DataException, ProcessoException {
		Date dataConvertida = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			dataConvertida = dateFormat.parse(dataDigitada);
			System.out.println(dataConvertida);
			String dataFormatada = dateFormat.format(dataConvertida);
			System.out.println("Data convertida: " + dataFormatada);
		} catch (Exception ex) {
			System.out.println("Erro ao converter a data: " + ex.getMessage());
			throw new DataException("Formato da data inválido !");
		}
		ProcessoController processoController = MainController.getProcessoController();
		Processo processo = processoController.getProcessoPorNumero(numProcesso);

		Conta conta = processo.getConta();
		conta.addDespesa(descricao, dataConvertida, valor);
		JOptionPane.showMessageDialog(null, "Despesa adicionada com sucesso !");
		System.out.println(conta.getTotalDespesas());
		MainController.save();
	}

}
