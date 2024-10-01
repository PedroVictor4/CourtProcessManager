package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class TribunalView extends JFrame {


	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public TribunalView() {
		initialize();
	}
	private void initialize() {
		setTitle("Adicionar/Listar tribunais");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(580, 100));

		// Aba para adicionar ou listar um Tribunal
		AdicionarTribunalView  adicionarTribunalView = new AdicionarTribunalView();
		tabbedPane.addTab("Adicionar Tribunal", null, adicionarTribunalView.getContentPane(), "Adicionar Tribunal");
		ListarTribunalView listarTirbunalView = new ListarTribunalView();
		tabbedPane.addTab("Listar Tribunal", null, listarTirbunalView .getContentPane(), "Listar Tribunal");

		contentPane.add(tabbedPane, BorderLayout.CENTER);

	}


}
