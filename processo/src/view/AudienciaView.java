package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class AudienciaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AudienciaView() {
		setTitle("Adicionar Prcoesso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		AdicionarAudienciaView adiconarAudienciaView = new AdicionarAudienciaView();
		tabbedPane.addTab("Adicionar Audiência", null, adiconarAudienciaView.getContentPane(), "Adicionar Audiência");
		ListarAudienciaView listarAudienciaProcessoView = new ListarAudienciaView();
		tabbedPane.addTab("Lista audiência", null, listarAudienciaProcessoView.getContentPane(), "Lista audiência");

		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

}
