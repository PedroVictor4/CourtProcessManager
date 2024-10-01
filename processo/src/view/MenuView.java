package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuView extends JFrame {

	
	private static final long serialVersionUID = 3547066202107090835L;
	private JPanel contentPane;


	public MenuView() {
		initialize();
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

		JButton btnAdicionarComponentes = new JButton("Adicionar novos componentes");
		JButton btnCriarProcesso = new JButton("Adicionar/modificar um processo");

		Dimension buttonSize = new Dimension(400, 30);
		btnAdicionarComponentes.setPreferredSize(buttonSize);
		btnCriarProcesso.setPreferredSize(buttonSize);

		contentPane.add(btnAdicionarComponentes);
		contentPane.add(btnCriarProcesso);

		btnAdicionarComponentes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AdicionarComponentesView adicionarComponenteView = new AdicionarComponentesView();
				adicionarComponenteView.setVisible(true); // Abre o AdicionarComponentesView
			}
		});
		btnCriarProcesso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbaProcessoView ProcessoView = new AbaProcessoView();
				ProcessoView.setVisible(true); 
			}
		});
	}
}

