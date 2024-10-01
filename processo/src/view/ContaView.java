package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ContaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ContaView() {
		initialize();	
	}
	private void initialize() {
		setTitle("Adicionar Prcoesso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		AdicionarPagamentoView adicionarPagamentoView = new AdicionarPagamentoView();
		tabbedPane.addTab("Adicionar Pagamento", null, adicionarPagamentoView.getContentPane(), "Adicionar Pagamento");
		AdicionarDespesaView adiconarDespesaView = new AdicionarDespesaView();
		tabbedPane.addTab("Adicionar Despesa", null, adiconarDespesaView.getContentPane(), "Adicionar Despesa");
		ListarExtratoView listarExtratoView = new ListarExtratoView();
		tabbedPane.addTab("Listar extrato", null, listarExtratoView.getContentPane(), "Listar extrato");

		contentPane.add(tabbedPane, BorderLayout.CENTER);

	}

}
