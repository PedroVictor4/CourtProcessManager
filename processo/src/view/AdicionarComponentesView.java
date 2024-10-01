package view;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class AdicionarComponentesView extends JFrame {

	private static final long serialVersionUID = -1599820626670005988L;
	private JPanel contentPane;

	public AdicionarComponentesView() {
		initialize();
	}

	private void initialize() {
		setTitle("Adicionar Componentes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		// Aba para Adicionar Tribunal
		TribunalView TribunalView = new TribunalView();
		tabbedPane.addTab("Adicionar Tribunal", null, TribunalView.getContentPane(), "Adicionar Tribunal");

		PessoaView PessoaView = new PessoaView();
		tabbedPane.addTab("Adicionar Pessoa Física/Juridica", null, PessoaView.getContentPane(),
				"Adicionar Pessoa");
		AdvogadoView AdicionarAdvogadoView = new AdvogadoView();
		tabbedPane.addTab("Adicionar Advogado", null, AdicionarAdvogadoView.getContentPane(),
				"Adicionar Advogado");
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
	}
	
}
