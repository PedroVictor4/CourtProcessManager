package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.MaskFormatter;

import controller.ContaController;
import controller.MainController;
import controller.ProcessoController;
import exception.ValorException;
import model.Processo;

public class AdicionarDespesaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Long> processoComboBox;
	private long processoSelecionado = 0l;
	private JFormattedTextField dataField;
	private JFormattedTextField valorField;
	private JTextArea descricaoField;
	Date dataConvertida = null;
	double valorFloat;
	String dataDigitada;

	public AdicionarDespesaView() {
		inicialize();
	}

	private void inicialize() {
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

		JLabel tituloLabel = new JLabel("Adicionar Despesa");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		contentPane.add(tituloLabel, gbcTitulo);

		// Processo ComboBox
		GridBagConstraints gbcProcessoLabel = new GridBagConstraints();
		gbcProcessoLabel.insets = new Insets(10, 10, 10, 10);
		gbcProcessoLabel.anchor = GridBagConstraints.WEST;
		gbcProcessoLabel.gridx = 0;
		gbcProcessoLabel.gridy = 1;
		JLabel processoLabel = new JLabel("Processo:");
		processoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(processoLabel, gbcProcessoLabel);

		GridBagConstraints gbcProcessoComboBox = new GridBagConstraints();
		gbcProcessoComboBox.insets = new Insets(10, 10, 10, 10);
		gbcProcessoComboBox.anchor = GridBagConstraints.WEST;
		gbcProcessoComboBox.gridx = 1;
		gbcProcessoComboBox.gridy = 1;
		processoComboBox = new JComboBox<>();
		processoComboBox.setPreferredSize(new Dimension(200, 30));
		contentPane.add(processoComboBox, gbcProcessoComboBox);

		// Data
		GridBagConstraints gbcDataLabel = new GridBagConstraints();
		gbcDataLabel.insets = new Insets(10, 10, 10, 10);
		gbcDataLabel.gridx = 0;
		gbcDataLabel.gridy = 3;
		gbcDataLabel.anchor = GridBagConstraints.WEST;
		JLabel dataLabel = new JLabel("Data:");
		dataLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(dataLabel, gbcDataLabel);

		GridBagConstraints gbcDataField = new GridBagConstraints();
		gbcDataField.anchor = GridBagConstraints.WEST;
		gbcDataField.insets = new Insets(10, 10, 10, 10);
		gbcDataField.gridx = 1;
		gbcDataField.gridy = 3;

		// Cria o JFormattedTextField com máscara para data no formato dd/MM/yyyy
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter('_');
			dataField = new JFormattedTextField(dateMask);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			return;
		}
		dataField.setPreferredSize(new Dimension(80, 30));
		contentPane.add(dataField, gbcDataField);

		// Configurações para a descrição
		GridBagConstraints gbcDescricaoLabel = new GridBagConstraints();
		gbcDescricaoLabel.insets = new Insets(10, 10, 10, 10);
		gbcDescricaoLabel.gridx = 0;
		gbcDescricaoLabel.gridy = 4;
		gbcDescricaoLabel.anchor = GridBagConstraints.WEST;
		JLabel descricaoLabel = new JLabel("Descrição:");
		descricaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(descricaoLabel, gbcDescricaoLabel);

		GridBagConstraints gbcDescricaoField = new GridBagConstraints();
		gbcDescricaoField.fill = GridBagConstraints.BOTH;
		gbcDescricaoField.insets = new Insets(10, 10, 10, 10);
		gbcDescricaoField.gridx = 1;
		gbcDescricaoField.gridy = 4;
		gbcDescricaoField.anchor = GridBagConstraints.WEST;
		descricaoField = new JTextArea();
		descricaoField.setPreferredSize(new Dimension(200, 50));
		contentPane.add(descricaoField, gbcDescricaoField);

		// Configurações para o valor
		GridBagConstraints gbcValorLabel = new GridBagConstraints();
		gbcValorLabel.insets = new Insets(10, 10, 10, 10);
		gbcValorLabel.gridx = 0;
		gbcValorLabel.gridy = 5;
		gbcValorLabel.anchor = GridBagConstraints.WEST;
		JLabel recomendacaoLabel = new JLabel("Valor:");
		recomendacaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(recomendacaoLabel, gbcValorLabel);

		// Configurações para o campo que recebe valor em float
		GridBagConstraints gbcValorField = new GridBagConstraints();
		gbcValorField.fill = GridBagConstraints.BOTH;
		gbcValorField.insets = new Insets(10, 10, 10, 10);
		gbcValorField.gridx = 1;
		gbcValorField.gridy = 5;
		gbcValorField.anchor = GridBagConstraints.WEST;

		NumberFormat floatFormat = NumberFormat.getNumberInstance();
		floatFormat.setMinimumFractionDigits(2);
		floatFormat.setMaximumFractionDigits(2);

		valorField = new JFormattedTextField(floatFormat); // Campo formatado para float
		valorField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(valorField, gbcValorField);

		// Configurações para o botão salvar
		GridBagConstraints gbcSalvarButton = new GridBagConstraints();
		gbcSalvarButton.insets = new Insets(20, 10, 10, 10);
		gbcSalvarButton.gridx = 1;
		gbcSalvarButton.gridy = 6;
		gbcSalvarButton.gridwidth = 2;
		gbcSalvarButton.anchor = GridBagConstraints.CENTER;
		JButton salvarButton = new JButton("Salvar");
		salvarButton.setPreferredSize(new Dimension(200, 30));
		salvarButton.setBackground(java.awt.Color.BLUE);
		salvarButton.setForeground(java.awt.Color.WHITE);
		contentPane.add(salvarButton, gbcSalvarButton);

		processoComboBox.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				atualizarProcessoComboBox();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});

		processoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (processoComboBox.getSelectedIndex() > -1) {
					processoSelecionado = (Long) processoComboBox.getSelectedItem();
					System.out.println("Cliente selecionado: " + processoSelecionado);
				}
			}
		});
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

	public void actionSalvar() {
		try {
			try {

				Number valorInserido = (Number) valorField.getValue();

				valorFloat = valorInserido.floatValue();

				System.out.println("Valor inserido como float: " + valorFloat);
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
				throw new ValorException("Há um problema no valor inserido !");
			}
			String descricao = descricaoField.getText();
			ContaController contaController = MainController.getContaController();
			dataDigitada = dataField.getText();
			contaController.adicionarDespesa(descricao, valorFloat, dataDigitada, processoSelecionado);
			dataField.setValue(null);
			valorField.setValue(null);
			descricaoField.setText("");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	public void atualizarProcessoComboBox() {
		ProcessoController processoController = MainController.getProcessoController();
		List<Processo> listaProcessos = processoController.getProcessos();
		processoComboBox.removeAllItems();
		for (Processo processo : listaProcessos) {
			processoComboBox.addItem(processo.getNumero());
		}
	}

}