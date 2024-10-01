package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import controller.TribunalController;

public class AdicionarTribunalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField siglaField;
	private JTextField sessaoField;
	private JTextArea descricaoField;

	public AdicionarTribunalView() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		// Configurações para o título
		GridBagConstraints gbcTitulo = new GridBagConstraints();
		gbcTitulo.insets = new Insets(10, 10, 5, 10);
		gbcTitulo.gridx = 1;
		gbcTitulo.gridy = 0;
		gbcTitulo.gridwidth = 2;
		gbcTitulo.anchor = GridBagConstraints.CENTER;

		JLabel tituloLabel = new JLabel("Adicionar Tribunal");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		contentPane.add(tituloLabel, gbcTitulo);

		// Configurações para a sigla
		GridBagConstraints gbcSiglaLabel = new GridBagConstraints();
		gbcSiglaLabel.insets = new Insets(10, 10, 10, 10);
		gbcSiglaLabel.gridx = 1;
		gbcSiglaLabel.gridy = 1;
		gbcSiglaLabel.anchor = GridBagConstraints.EAST;
		JLabel siglaLabel = new JLabel("Sigla:");
		siglaLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(siglaLabel, gbcSiglaLabel);

		GridBagConstraints gbcSiglaField = new GridBagConstraints();
		gbcSiglaField.fill = GridBagConstraints.BOTH;
		gbcSiglaField.insets = new Insets(10, 10, 10, 10);
		gbcSiglaField.gridx = 2;
		gbcSiglaField.gridy = 1;
		gbcSiglaField.anchor = GridBagConstraints.WEST;
		siglaField = new JTextField();
		siglaField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(siglaField, gbcSiglaField);

		// Configurações para a sessão
		GridBagConstraints gbcSessaoLabel = new GridBagConstraints();
		gbcSessaoLabel.insets = new Insets(10, 10, 10, 10);
		gbcSessaoLabel.gridx = 1;
		gbcSessaoLabel.gridy = 2;
		gbcSessaoLabel.anchor = GridBagConstraints.EAST;
		JLabel sessaoLabel = new JLabel("Sessão:");
		sessaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(sessaoLabel, gbcSessaoLabel);

		GridBagConstraints gbcSessaoField = new GridBagConstraints();
		gbcSessaoField.fill = GridBagConstraints.BOTH;
		gbcSessaoField.insets = new Insets(10, 10, 10, 10);
		gbcSessaoField.gridx = 2;
		gbcSessaoField.gridy = 2;
		gbcSessaoField.anchor = GridBagConstraints.WEST;
		sessaoField = new JTextField();
		sessaoField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(sessaoField, gbcSessaoField);

		// Configurações para a descrição
		GridBagConstraints gbcDescricaoLabel = new GridBagConstraints();
		gbcDescricaoLabel.insets = new Insets(10, 10, 10, 10);
		gbcDescricaoLabel.gridx = 1;
		gbcDescricaoLabel.gridy = 3;
		gbcDescricaoLabel.anchor = GridBagConstraints.EAST;
		JLabel descricaoLabel = new JLabel("Descrição:");
		descricaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(descricaoLabel, gbcDescricaoLabel);

		GridBagConstraints gbcDescricaoField = new GridBagConstraints();
		gbcDescricaoField.fill = GridBagConstraints.BOTH;
		gbcDescricaoField.insets = new Insets(10, 10, 10, 10);
		gbcDescricaoField.gridx = 2;
		gbcDescricaoField.gridy = 3;
		gbcDescricaoField.anchor = GridBagConstraints.WEST;

		// Alteração para JTextArea dentro de um JScrollPane
		descricaoField = new JTextArea(3, 20); // 3 linhas, 20 colunas
		descricaoField.setLineWrap(true);
		descricaoField.setWrapStyleWord(true);
		JScrollPane descricaoScrollPane = new JScrollPane(descricaoField);
		descricaoScrollPane.setPreferredSize(new Dimension(200, 50));
		contentPane.add(descricaoScrollPane, gbcDescricaoField);

		// Configurações para o botão salvar
		GridBagConstraints gbcSalvarButton = new GridBagConstraints();
		gbcSalvarButton.insets = new Insets(20, 10, 10, 10);
		gbcSalvarButton.gridx = 1;
		gbcSalvarButton.gridy = 4;
		gbcSalvarButton.gridwidth = 2;
		gbcSalvarButton.anchor = GridBagConstraints.CENTER;
		JButton salvarButton = new JButton("Salvar");
		salvarButton.setPreferredSize(new Dimension(200, 30));
		salvarButton.setBackground(java.awt.Color.BLUE);
		salvarButton.setForeground(java.awt.Color.WHITE);
		contentPane.add(salvarButton, gbcSalvarButton);

		salvarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				actionSalvar();

			}
		});
	}

	public void actionSalvar() {
		TribunalController tribunalController = MainController.getTribunalController();
		String sigla = siglaField.getText();
		String sessao = sessaoField.getText();
		String descricao = descricaoField.getText();

		System.out.println("sessão " + sessao);

		try {
			tribunalController.criarTribunal(sigla, sessao, descricao);
			siglaField.setText("");
	        sessaoField.setText("");
	        descricaoField.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
