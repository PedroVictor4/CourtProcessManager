package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class AbaProcessoView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AbaProcessoView() {
		initialize();
	}

	private void initialize() {
		setTitle("Adicionar Prcoesso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		ProcessoView ProcessoView = new ProcessoView();
		tabbedPane.addTab("Processo", null, ProcessoView.getContentPane(), "Proesso");
		AudienciaView audienciaView = new AudienciaView();
		tabbedPane.addTab("Audiência", null, audienciaView.getContentPane(), "Audiência");
		ContaView contaView = new ContaView();
		tabbedPane.addTab("Conta", null, contaView.getContentPane(), "Conta");


		contentPane.add(tabbedPane, BorderLayout.CENTER);

	}

}
