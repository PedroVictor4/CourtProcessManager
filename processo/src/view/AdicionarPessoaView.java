package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import controller.PessoaController;
import controller.dto.PessoaDto;

public class AdicionarPessoaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField emailField;
	private JTextField numeroField;
	private JTextField documentoField;
	private JComboBox<String> prepostosComboBox;
	private JComboBox<String> tipoPessoaComboBox; 
	private JLabel documentoLabel; 
	private String pessoaSelecionada = null;

	public AdicionarPessoaView() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());

		// Configurações para o título
		GridBagConstraints gbcTitulo = new GridBagConstraints();
		gbcTitulo.insets = new Insets(10, 10, 10, 10);
		gbcTitulo.anchor = GridBagConstraints.CENTER;
		gbcTitulo.gridx = 0;
		gbcTitulo.gridy = 0;
		gbcTitulo.gridwidth = 3;
		JLabel tituloLabel = new JLabel("Adicionar Pessoa/Jurídica");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		contentPane.add(tituloLabel, gbcTitulo);

		// Configurações para o tipo de pessoa
		GridBagConstraints gbcTipoPessoaLabel = new GridBagConstraints();
		gbcTipoPessoaLabel.insets = new Insets(10, 10, 10, 10);
		gbcTipoPessoaLabel.anchor = GridBagConstraints.WEST;
		gbcTipoPessoaLabel.gridx = 0;
		gbcTipoPessoaLabel.gridy = 1;
		gbcTipoPessoaLabel.gridwidth = 1;
		JLabel tipoPessoaLabel = new JLabel("Tipo de Pessoa:");
		tipoPessoaLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(tipoPessoaLabel, gbcTipoPessoaLabel);

		GridBagConstraints gbcTipoPessoaComboBox = new GridBagConstraints();
		gbcTipoPessoaComboBox.insets = new Insets(10, 10, 10, 10);
		gbcTipoPessoaComboBox.anchor = GridBagConstraints.WEST;
		gbcTipoPessoaComboBox.gridx = 1;
		gbcTipoPessoaComboBox.gridy = 1;
		tipoPessoaComboBox = new JComboBox<>(new String[] { "Física", "Jurídica" });
		tipoPessoaComboBox.setPreferredSize(new Dimension(200, 30));
		contentPane.add(tipoPessoaComboBox, gbcTipoPessoaComboBox);

		// Configurações para o nome
		GridBagConstraints gbcNomeLabel = new GridBagConstraints();
		gbcNomeLabel.insets = new Insets(10, 10, 10, 10);
		gbcNomeLabel.anchor = GridBagConstraints.WEST;
		gbcNomeLabel.gridx = 0;
		gbcNomeLabel.gridy = 2;
		JLabel nomeLabel = new JLabel("Nome:");
		nomeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(nomeLabel, gbcNomeLabel);

		GridBagConstraints gbcNomeField = new GridBagConstraints();
		gbcNomeField.insets = new Insets(10, 10, 10, 10);
		gbcNomeField.anchor = GridBagConstraints.WEST;
		gbcNomeField.gridx = 1;
		gbcNomeField.gridy = 2;
		nomeField = new JTextField();
		nomeField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(nomeField, gbcNomeField);

		// Configurações para o email
		GridBagConstraints gbcEmailLabel = new GridBagConstraints();
		gbcEmailLabel.insets = new Insets(10, 10, 10, 10);
		gbcEmailLabel.anchor = GridBagConstraints.WEST;
		gbcEmailLabel.gridx = 0;
		gbcEmailLabel.gridy = 3;
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(emailLabel, gbcEmailLabel);

		GridBagConstraints gbcEmailField = new GridBagConstraints();
		gbcEmailField.insets = new Insets(10, 10, 10, 10);
		gbcEmailField.anchor = GridBagConstraints.WEST;
		gbcEmailField.gridx = 1;
		gbcEmailField.gridy = 3;
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(emailField, gbcEmailField);

		// Configurações para o número
		GridBagConstraints gbcNumeroLabel = new GridBagConstraints();
		gbcNumeroLabel.insets = new Insets(10, 10, 10, 10);
		gbcNumeroLabel.anchor = GridBagConstraints.WEST;
		gbcNumeroLabel.gridx = 0;
		gbcNumeroLabel.gridy = 4;
		JLabel numeroLabel = new JLabel("Número:");
		numeroLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(numeroLabel, gbcNumeroLabel);

		GridBagConstraints gbcNumeroField = new GridBagConstraints();
		gbcNumeroField.insets = new Insets(10, 10, 10, 10);
		gbcNumeroField.anchor = GridBagConstraints.WEST;
		gbcNumeroField.gridx = 1;
		gbcNumeroField.gridy = 4;
		numeroField = new JTextField();
		numeroField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(numeroField, gbcNumeroField);

		// Configurações para o CPF/CNPJ
		GridBagConstraints gbcDocumentoLabel = new GridBagConstraints();
		gbcDocumentoLabel.insets = new Insets(10, 10, 10, 10);
		gbcDocumentoLabel.anchor = GridBagConstraints.WEST;
		gbcDocumentoLabel.gridx = 0;
		gbcDocumentoLabel.gridy = 5;
		documentoLabel = new JLabel("(números) CPF:");
		documentoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(documentoLabel, gbcDocumentoLabel);

		GridBagConstraints gbcDocumentoField = new GridBagConstraints();
		gbcDocumentoField.insets = new Insets(10, 10, 10, 10);
		gbcDocumentoField.anchor = GridBagConstraints.WEST;
		gbcDocumentoField.gridx = 1;
		gbcDocumentoField.gridy = 5;
		documentoField = new JTextField();
		documentoField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(documentoField, gbcDocumentoField);

		// Configurações para o JComboBox de prepostos
		GridBagConstraints gbcPrepostosLabel = new GridBagConstraints();
		gbcPrepostosLabel.insets = new Insets(10, 10, 10, 10);
		gbcPrepostosLabel.anchor = GridBagConstraints.WEST;
		gbcPrepostosLabel.gridx = 0;
		gbcPrepostosLabel.gridy = 6;
		JLabel prepostosLabel = new JLabel("(CPF) Prepostos:");
		prepostosLabel.setVisible(false);
		prepostosLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(prepostosLabel, gbcPrepostosLabel);

		GridBagConstraints gbcPrepostosComboBox = new GridBagConstraints();
		gbcPrepostosComboBox.insets = new Insets(10, 10, 10, 10);
		gbcPrepostosComboBox.anchor = GridBagConstraints.WEST;
		gbcPrepostosComboBox.gridx = 1;
		gbcPrepostosComboBox.gridy = 6;
		prepostosComboBox = new JComboBox<>();
		prepostosComboBox.setPreferredSize(new Dimension(200, 30));
		prepostosComboBox.setVisible(false); // Inicialmente oculto
		contentPane.add(prepostosComboBox, gbcPrepostosComboBox);

		// Configurações para o botão salvar
		GridBagConstraints gbcSalvarButton = new GridBagConstraints();
		gbcSalvarButton.insets = new Insets(20, 10, 10, 10);
		gbcSalvarButton.anchor = GridBagConstraints.CENTER;
		gbcSalvarButton.gridx = 0;
		gbcSalvarButton.gridy = 7;
		gbcSalvarButton.gridwidth = 2;
		JButton salvarButton = new JButton("Salvar");
		salvarButton.setPreferredSize(new Dimension(200, 30));
		salvarButton.setBackground(java.awt.Color.BLUE);
		salvarButton.setForeground(java.awt.Color.WHITE);
		contentPane.add(salvarButton, gbcSalvarButton);

		// Listener para alterar os campos conforme o tipo de pessoa
		tipoPessoaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedTipo = (String) tipoPessoaComboBox.getSelectedItem();
				if ("Física".equals(selectedTipo)) {
					documentoLabel.setText("(números) CPF:");
					documentoField.setToolTipText("Digite o CPF");
					prepostosComboBox.setVisible(false);
					prepostosLabel.setVisible(false);
				} else {
					documentoLabel.setText("(números) CNPJ:");
					documentoField.setToolTipText("Digite o CNPJ");
					prepostosComboBox.setVisible(true);
					prepostosLabel.setVisible(true);
					atualizarListaPrepostos(); // Chama a função quando "Jurídica" é selecionada
				}
			}
		});
		prepostosComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (prepostosComboBox.getSelectedIndex() > -1) {
					pessoaSelecionada = (String) prepostosComboBox.getSelectedItem();
					//System.out.println("Pessoa selecionada: " + pessoaSelecionada.getNome());
				}
			}
		});
		salvarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				actionSalvar();
			}
		});

	}

	private void atualizarListaPrepostos() {
		PessoaController pessoaController = MainController.getPessoaController();
		List<PessoaDto> listaPessoasFisicas = pessoaController.getPessoaFisicaDto();

		prepostosComboBox.removeAllItems();
		for (PessoaDto pessoa : listaPessoasFisicas) {
			prepostosComboBox.addItem(pessoa.getCadastro());
			System.out.println(pessoa.getNome());
		}
	}

	public void actionSalvar() {
		try {
			PessoaController pessoaController = MainController.getPessoaController();
			// Captura os valores dos campos
			String nome = nomeField.getText();
			String email = emailField.getText();
			String numero = numeroField.getText();
			String documento = documentoField.getText();
			String tipoPessoa = (String) tipoPessoaComboBox.getSelectedItem();
			// Usa o PessoaController para criar uma nova Pessoa/Jurídica
			pessoaController.criarPessoa(nome, email, numero, documento, tipoPessoa, pessoaSelecionada);
			nomeField.setText("");
			emailField.setText("");
			numeroField.setText("");
			documentoField.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}
}
