package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ProcessoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ProcessoView() {
		inicialize();
	}
	
	
	private void inicialize() {
		setTitle("Adicionar Prcoesso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		AdicionarProcessoView adicionarProcessoView = new AdicionarProcessoView();
		tabbedPane.addTab("Adicionar Processo", null, adicionarProcessoView.getContentPane(), "Adicionar Proesso");
		ModificaEstadoProcessoView modificaEstadoProcessoView = new ModificaEstadoProcessoView();
		tabbedPane.addTab("Muda estado", null, modificaEstadoProcessoView.getContentPane(), "Muda estado");
		ListarProcessoView listarEstadoProcessoView = new ListarProcessoView();
		tabbedPane.addTab("Listar Processos", null, listarEstadoProcessoView.getContentPane(), "Listar Processos");
		ListarExtratoProcessoView listarExtratoProcessoView = new ListarExtratoProcessoView();
		tabbedPane.addTab("Listar Extrato Processo", null, listarExtratoProcessoView.getContentPane(), "Listar Extrato Processo");


		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

}
