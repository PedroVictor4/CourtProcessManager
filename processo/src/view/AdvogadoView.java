package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class AdvogadoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AdvogadoView() {
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

		AdicionarNovaPessoaAdvogadoView adicionarNovaPessoaAdvogadoView = new AdicionarNovaPessoaAdvogadoView();
		tabbedPane.addTab("Adicionar Advogado", null, adicionarNovaPessoaAdvogadoView.getContentPane(),
				"Adicionar Advogado");
		ListarAdvogadoView listarAdvogadoView = new ListarAdvogadoView();
		tabbedPane.addTab("Listar Advogado", null, listarAdvogadoView.getContentPane(), "Listar Advogado");

		contentPane.add(tabbedPane, BorderLayout.CENTER);

	}
}
