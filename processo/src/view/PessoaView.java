package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class PessoaView extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public PessoaView() {
		inicialize();
	}
	
	private void inicialize() {
		setTitle("Adicionar/Listar Pessoas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		// Aba para adicionar ou listar um Tribunal
		AdicionarPessoaView adicionarPessoaView = new AdicionarPessoaView();
		tabbedPane.addTab("Adicionar Pessoa", null, adicionarPessoaView.getContentPane(), "Adcionar Pessoa");
		ListarPessoasView listarPessoasView = new ListarPessoasView();
		tabbedPane.addTab("Listar Pessoas", null, listarPessoasView.getContentPane(), "Listar Pessoas");

		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

}
