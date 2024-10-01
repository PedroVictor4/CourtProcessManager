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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AdvogadoController;
import controller.MainController;
import exception.AdvogadoException;
import exception.CPFException;
import exception.PessoaException;

public class AdicionarNovaPessoaAdvogadoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField emailField;
	private JTextField numeroField;
	private JTextField documentoField;

	private JLabel documentoLabel;
	private JLabel registroLabel;
	private JTextField registroField;

	public AdicionarNovaPessoaAdvogadoView() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 0.1, 0.9 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1 };
		contentPane.setLayout(gbl_contentPane);

		// Título
		GridBagConstraints gbcTitulo = new GridBagConstraints();
		gbcTitulo.insets = new Insets(10, 10, 10, 10);
		gbcTitulo.gridx = 0;
		gbcTitulo.gridy = 0;
		gbcTitulo.gridwidth = 2;
		gbcTitulo.anchor = GridBagConstraints.CENTER;
		JLabel tituloLabel = new JLabel("Adicionar Advogado");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		contentPane.add(tituloLabel, gbcTitulo);

		// Nome
		GridBagConstraints gbcNomeLabel = new GridBagConstraints();
		gbcNomeLabel.insets = new Insets(10, 10, 10, 10);
		gbcNomeLabel.gridx = 0;
		gbcNomeLabel.gridy = 1;
		gbcNomeLabel.anchor = GridBagConstraints.WEST;
		JLabel nomeLabel = new JLabel("Nome:");
		nomeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(nomeLabel, gbcNomeLabel);

		GridBagConstraints gbcNomeField = new GridBagConstraints();
		gbcNomeField.insets = new Insets(10, 10, 10, 10);
		gbcNomeField.gridx = 1;
		gbcNomeField.gridy = 1;
		gbcNomeField.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbcNomeField.weightx = 1.0; // Expansão proporcional
		nomeField = new JTextField();
		nomeField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(nomeField, gbcNomeField);

		// Email
		GridBagConstraints gbcEmailLabel = new GridBagConstraints();
		gbcEmailLabel.insets = new Insets(10, 10, 10, 10);
		gbcEmailLabel.gridx = 0;
		gbcEmailLabel.gridy = 2;
		gbcEmailLabel.anchor = GridBagConstraints.WEST;
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(emailLabel, gbcEmailLabel);

		GridBagConstraints gbcEmailField = new GridBagConstraints();
		gbcEmailField.insets = new Insets(10, 10, 10, 10);
		gbcEmailField.gridx = 1;
		gbcEmailField.gridy = 2;
		gbcEmailField.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbcEmailField.weightx = 1.0; // Expansão proporcional
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(emailField, gbcEmailField);

		// Número
		GridBagConstraints gbcNumeroLabel = new GridBagConstraints();
		gbcNumeroLabel.insets = new Insets(10, 10, 10, 10);
		gbcNumeroLabel.gridx = 0;
		gbcNumeroLabel.gridy = 3;
		gbcNumeroLabel.anchor = GridBagConstraints.WEST;
		JLabel numeroLabel = new JLabel("Número:");
		numeroLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(numeroLabel, gbcNumeroLabel);

		GridBagConstraints gbcNumeroField = new GridBagConstraints();
		gbcNumeroField.insets = new Insets(10, 10, 10, 10);
		gbcNumeroField.gridx = 1;
		gbcNumeroField.gridy = 3;
		gbcNumeroField.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbcNumeroField.weightx = 1.0; // Expansão proporcional
		numeroField = new JTextField();
		numeroField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(numeroField, gbcNumeroField);

		// Documento (CPF/CNPJ)
		GridBagConstraints gbcDocumentoLabel = new GridBagConstraints();
		gbcDocumentoLabel.insets = new Insets(10, 10, 10, 10);
		gbcDocumentoLabel.gridx = 0;
		gbcDocumentoLabel.gridy = 4;
		gbcDocumentoLabel.anchor = GridBagConstraints.WEST;
		documentoLabel = new JLabel("(números) CPF:");
		documentoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(documentoLabel, gbcDocumentoLabel);

		GridBagConstraints gbcDocumentoField = new GridBagConstraints();
		gbcDocumentoField.insets = new Insets(10, 10, 10, 10);
		gbcDocumentoField.gridx = 1;
		gbcDocumentoField.gridy = 4;
		gbcDocumentoField.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbcDocumentoField.weightx = 1.0; // Expansão proporcional
		documentoField = new JTextField();
		documentoField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(documentoField, gbcDocumentoField);

		// Registro
		GridBagConstraints gbcRegistroLabel = new GridBagConstraints();
		gbcRegistroLabel.insets = new Insets(10, 10, 10, 10);
		gbcRegistroLabel.gridx = 0;
		gbcRegistroLabel.gridy = 5;
		gbcRegistroLabel.anchor = GridBagConstraints.WEST;
		registroLabel = new JLabel("Registro:");
		registroLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(registroLabel, gbcRegistroLabel);

		GridBagConstraints gbcRegistroField = new GridBagConstraints();
		gbcRegistroField.insets = new Insets(10, 10, 10, 10);
		gbcRegistroField.gridx = 1;
		gbcRegistroField.gridy = 5;
		gbcRegistroField.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbcRegistroField.weightx = 1.0; // Expansão proporcional
		registroField = new JTextField();
		registroField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(registroField, gbcRegistroField);

		// Botão Salvar
		GridBagConstraints gbcSalvarButton = new GridBagConstraints();
		gbcSalvarButton.insets = new Insets(20, 10, 10, 10);
		gbcSalvarButton.gridx = 0;
		gbcSalvarButton.gridy = 6;
		gbcSalvarButton.gridwidth = 2;
		gbcSalvarButton.anchor = GridBagConstraints.CENTER;
		gbcSalvarButton.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
		gbcSalvarButton.weightx = 1.0; // Expansão proporcional
		JButton salvarButton = new JButton("Salvar");
		salvarButton.setPreferredSize(new Dimension(200, 30));
		salvarButton.setBackground(java.awt.Color.BLUE);
		salvarButton.setForeground(java.awt.Color.WHITE);
		contentPane.add(salvarButton, gbcSalvarButton);

		// Ação do botão Salvar
		salvarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					actionSalvar();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public void actionSalvar() throws CPFException, PessoaException, AdvogadoException {
		try {
			String nome = nomeField.getText();
			String email = emailField.getText();
			String numero = numeroField.getText();
			String documento = documentoField.getText();
			String registro = registroField.getText();

			AdvogadoController advogadoController = MainController.getAdvogadoController();
			advogadoController.criarAdvogado(nome, email, numero, documento, registro);

			nomeField.setText("");
			emailField.setText("");
			numeroField.setText("");
			documentoField.setText("");
			registroField.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

}
