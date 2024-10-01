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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.MaskFormatter;

import controller.MainController;
import controller.PessoaController;
import controller.ProcessoController;
import controller.TribunalController;
import controller.dto.PessoaDto;
import controller.dto.TribunalDto;

public class AdicionarProcessoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numeroField;
	private JComboBox<String> parteContrariaComboBox;
	private JComboBox<String> clienteComboBox;
	private JComboBox<String> tribunalComboBox;

	private String pessoaSelecionada;
	private String clienteSelecionado;
	private String tribunalSelecionado;

	private JFormattedTextField dataField;

	public AdicionarProcessoView() {
		inicialize();
	}

	private void inicialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 0.1, 0.9 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		// Título
		GridBagConstraints gbcTitulo = new GridBagConstraints();
		gbcTitulo.insets = new Insets(10, 10, 10, 10);
		gbcTitulo.gridx = 0;
		gbcTitulo.gridy = 0;
		gbcTitulo.gridwidth = 2;
		gbcTitulo.anchor = GridBagConstraints.CENTER;
		JLabel tituloLabel = new JLabel("Adicionar Processo");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		contentPane.add(tituloLabel, gbcTitulo);

		// Numero
		GridBagConstraints gbcNumeroLabel = new GridBagConstraints();
		gbcNumeroLabel.insets = new Insets(10, 10, 10, 10);
		gbcNumeroLabel.gridx = 0;
		gbcNumeroLabel.gridy = 1;
		gbcNumeroLabel.anchor = GridBagConstraints.WEST;
		JLabel numeroLabel = new JLabel("Numero:");
		numeroLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(numeroLabel, gbcNumeroLabel);

		GridBagConstraints gbcNumeroField = new GridBagConstraints();
		gbcNumeroField.insets = new Insets(10, 10, 10, 10);
		gbcNumeroField.gridx = 1;
		gbcNumeroField.gridy = 1;
		gbcNumeroField.fill = GridBagConstraints.HORIZONTAL;
		gbcNumeroField.weightx = 1.0;
		numeroField = new JTextField();
		numeroField.setPreferredSize(new Dimension(300, 30));
		contentPane.add(numeroField, gbcNumeroField);

		// Data
		GridBagConstraints gbcDataLabel = new GridBagConstraints();
		gbcDataLabel.insets = new Insets(10, 10, 10, 10);
		gbcDataLabel.gridx = 0;
		gbcDataLabel.gridy = 2;
		gbcDataLabel.anchor = GridBagConstraints.WEST;
		JLabel dataLabel = new JLabel("Data:");
		dataLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(dataLabel, gbcDataLabel);

		GridBagConstraints gbcDataField = new GridBagConstraints();
		gbcDataField.anchor = GridBagConstraints.WEST;
		gbcDataField.insets = new Insets(10, 10, 10, 10);
		gbcDataField.gridx = 1;
		gbcDataField.gridy = 2;

		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter('_');
			dataField = new JFormattedTextField(dateMask);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		dataField.setPreferredSize(new Dimension(80, 30));
		contentPane.add(dataField, gbcDataField);

		// Cliente ComboBox
		GridBagConstraints gbcClienteLabel = new GridBagConstraints();
		gbcClienteLabel.insets = new Insets(10, 10, 10, 10);
		gbcClienteLabel.anchor = GridBagConstraints.WEST;
		gbcClienteLabel.gridx = 0;
		gbcClienteLabel.gridy = 3;
		JLabel clienteLabel = new JLabel("Cliente:");
		clienteLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(clienteLabel, gbcClienteLabel);

		GridBagConstraints gbcClienteComboBox = new GridBagConstraints();
		gbcClienteComboBox.insets = new Insets(10, 10, 10, 10);
		gbcClienteComboBox.anchor = GridBagConstraints.WEST;
		gbcClienteComboBox.gridx = 1;
		gbcClienteComboBox.gridy = 3;
		clienteComboBox = new JComboBox<>();
		clienteComboBox.setPreferredSize(new Dimension(300, 30));
		contentPane.add(clienteComboBox, gbcClienteComboBox);

		// Combobox parte contraria
		GridBagConstraints gbcParteContrariaLabel = new GridBagConstraints();
		gbcParteContrariaLabel.insets = new Insets(10, 10, 10, 10);
		gbcParteContrariaLabel.anchor = GridBagConstraints.WEST;
		gbcParteContrariaLabel.gridx = 0;
		gbcParteContrariaLabel.gridy = 4;
		JLabel parteContrariaLabel = new JLabel("Parte Contraria:");
		parteContrariaLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(parteContrariaLabel, gbcParteContrariaLabel);

		GridBagConstraints gbcParteContrariaComboBox = new GridBagConstraints();
		gbcParteContrariaComboBox.insets = new Insets(10, 10, 10, 10);
		gbcParteContrariaComboBox.anchor = GridBagConstraints.WEST;
		gbcParteContrariaComboBox.gridx = 1;
		gbcParteContrariaComboBox.gridy = 4;
		// gbcParteContrariaComboBox.fill = GridBagConstraints.HORIZONTAL;
		// gbcParteContrariaComboBox.weightx = 1.0;
		parteContrariaComboBox = new JComboBox<>();
		parteContrariaComboBox.setPreferredSize(new Dimension(300, 30));
		contentPane.add(parteContrariaComboBox, gbcParteContrariaComboBox);

		// Tribunal ComboBox
		GridBagConstraints gbcTribunalLabel = new GridBagConstraints();
		gbcTribunalLabel.insets = new Insets(10, 10, 10, 10);
		gbcTribunalLabel.anchor = GridBagConstraints.WEST;
		gbcTribunalLabel.gridx = 0;
		gbcTribunalLabel.gridy = 5;
		JLabel tribunalLabel = new JLabel("Tribunal:");
		tribunalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(tribunalLabel, gbcTribunalLabel);

		GridBagConstraints gbcTribunalComboBox = new GridBagConstraints();
		gbcTribunalComboBox.insets = new Insets(10, 10, 10, 10);
		gbcTribunalComboBox.anchor = GridBagConstraints.WEST;
		gbcTribunalComboBox.gridx = 1;
		gbcTribunalComboBox.gridy = 5;
		tribunalComboBox = new JComboBox<>();
		tribunalComboBox.setPreferredSize(new Dimension(300, 30));
		contentPane.add(tribunalComboBox, gbcTribunalComboBox);

		// Botão Salvar
		GridBagConstraints gbcSalvarButton = new GridBagConstraints();
		gbcSalvarButton.insets = new Insets(20, 10, 10, 10);
		gbcSalvarButton.gridx = 0;
		gbcSalvarButton.gridy = 6;
		gbcSalvarButton.gridwidth = 2;
		gbcSalvarButton.anchor = GridBagConstraints.CENTER;
		gbcSalvarButton.fill = GridBagConstraints.HORIZONTAL;
		gbcSalvarButton.weightx = 1.0;
		JButton salvarButton = new JButton("Salvar");
		salvarButton.setPreferredSize(new Dimension(200, 30));
		salvarButton.setBackground(java.awt.Color.BLUE);
		salvarButton.setForeground(java.awt.Color.WHITE);
		contentPane.add(salvarButton, gbcSalvarButton);

		clienteComboBox.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				atualizarClienteComboBox();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});

		clienteComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (clienteComboBox.getSelectedIndex() > -1) {
					clienteSelecionado = (String) clienteComboBox.getSelectedItem();
					// System.out.println("Cliente selecionado: " + clienteSelecionado.getNome());
				}
			}
		});

		parteContrariaComboBox.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				atualizarParteContrariaComboBox();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});

		parteContrariaComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parteContrariaComboBox.getSelectedIndex() > -1) {
					pessoaSelecionada = (String) parteContrariaComboBox.getSelectedItem();
					// System.out.println("Pessoa selecionada: " + pessoaSelecionada.getNome());
				}
			}
		});

		tribunalComboBox.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				atualizarTribunalComboBox();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});

		tribunalComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tribunalComboBox.getSelectedIndex() > -1) {
					tribunalSelecionado = (String) tribunalComboBox.getSelectedItem();
					// System.out.println("Tribunal selecionado: " +
					// tribunalSelecionado.getSigla());
				}
			}
		});

		// Ação do botão Salvar
		salvarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				actionSalvar();

			}
		});
	}

	public void actionSalvar() {
		try {
			ProcessoController processoController = MainController.getProcessoController();
			String dataDigitada = dataField.getText();

			String numero = numeroField.getText();
			processoController.criarProcesso(numero, tribunalSelecionado, clienteSelecionado, dataDigitada,
					pessoaSelecionada);
			//Decidi manter a escolha feita no ComboBox
			numeroField.setText("");
			dataField.setValue(null);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}

	public void atualizarClienteComboBox() {
		PessoaController pessoaController = MainController.getPessoaController();
		List<PessoaDto> listaPessoas = pessoaController.getPessoaDto();
		clienteComboBox.removeAllItems();
		for (PessoaDto pessoa : listaPessoas) {
			clienteComboBox.addItem(pessoa.getCadastro());
		}
	}

	public void atualizarParteContrariaComboBox() {
		PessoaController pessoaController = MainController.getPessoaController();
		List<PessoaDto> listaPessoas = pessoaController.getPessoaDto();
		parteContrariaComboBox.removeAllItems();
		for (PessoaDto pessoa : listaPessoas) {
			parteContrariaComboBox.addItem(pessoa.getCadastro());
		}
	}

	public void atualizarTribunalComboBox() {
		TribunalController tribunalController = MainController.getTribunalController();
		List<TribunalDto> listaTribunais = tribunalController.getTribunaisDto();
		tribunalComboBox.removeAllItems();
		for (TribunalDto tribunal : listaTribunais) {
			tribunalComboBox.addItem(tribunal.getSigla());
		}
	}
}
