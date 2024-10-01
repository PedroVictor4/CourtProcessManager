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
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.MaskFormatter;

import controller.ContaController;
import controller.MainController;
import controller.ProcessoController;
import exception.ValorException;
import model.EPagamento;
import model.Processo;

public class AdicionarPagamentoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Long> processoComboBox;
	private JComboBox<EPagamento> tipoPagamentoComboBox;
	private long processoSelecionado = 0;
	private JFormattedTextField dataField;
	private JFormattedTextField valorField;
	Date dataConvertida = null;
	EPagamento tipoSelecionado;
	double valorFloat;

	public AdicionarPagamentoView() {
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

		JLabel tituloLabel = new JLabel("Adicionar Pagamento");
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

		// Configurações para o valor
		GridBagConstraints gbcValorLabel = new GridBagConstraints();
		gbcValorLabel.insets = new Insets(10, 10, 10, 10);
		gbcValorLabel.gridx = 0;
		gbcValorLabel.gridy = 4;
		gbcValorLabel.anchor = GridBagConstraints.WEST;
		JLabel recomendacaoLabel = new JLabel("Valor:");
		recomendacaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(recomendacaoLabel, gbcValorLabel);

		// Configurações para o campo que recebe valor em float
		GridBagConstraints gbcValorField = new GridBagConstraints();
		gbcValorField.fill = GridBagConstraints.BOTH;
		gbcValorField.insets = new Insets(10, 10, 10, 10);
		gbcValorField.gridx = 1;
		gbcValorField.gridy = 4;
		gbcValorField.anchor = GridBagConstraints.WEST;

		NumberFormat floatFormat = NumberFormat.getNumberInstance(); // Usa o formato de número
		floatFormat.setMinimumFractionDigits(2); // Define o mínimo de casas decimais
		floatFormat.setMaximumFractionDigits(2); // Define o máximo de casas decimais

		valorField = new JFormattedTextField(floatFormat); // Campo formatado para float
		valorField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(valorField, gbcValorField);

		// Configurações para o label de Tipo de Pagamento
		GridBagConstraints gbcTipoPagamentoLabel = new GridBagConstraints();
		gbcTipoPagamentoLabel.insets = new Insets(10, 10, 10, 10);
		gbcTipoPagamentoLabel.anchor = GridBagConstraints.WEST;
		gbcTipoPagamentoLabel.gridx = 0;
		gbcTipoPagamentoLabel.gridy = 5;
		JLabel tipoPagamentoLabel = new JLabel("Tipo de Pagamento:");
		tipoPagamentoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(tipoPagamentoLabel, gbcTipoPagamentoLabel);

		// Configurações para o ComboBox que mostra os valores do enum EPagamento
		GridBagConstraints gbcTipoPagamentoComboBox = new GridBagConstraints();
		gbcTipoPagamentoComboBox.insets = new Insets(10, 10, 10, 10);
		gbcTipoPagamentoComboBox.anchor = GridBagConstraints.WEST;
		gbcTipoPagamentoComboBox.gridx = 1;
		gbcTipoPagamentoComboBox.gridy = 5;

		tipoPagamentoComboBox = new JComboBox<>(EPagamento.values());
		tipoPagamentoComboBox.setPreferredSize(new Dimension(200, 30));
		contentPane.add(tipoPagamentoComboBox, gbcTipoPagamentoComboBox);

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

		tipoPagamentoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoSelecionado = (EPagamento) tipoPagamentoComboBox.getSelectedItem();
			}
		});

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

				actionSalvar();

			}
		});
	}

	public void actionSalvar() {
		try {
			try {
				// Obtém o valor do campo formatado
				Number valorInserido = (Number) valorField.getValue();

				// Converte para float
				valorFloat = valorInserido.floatValue();
				System.out.println("Valor inserido como float: " + valorFloat);
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
				throw new ValorException("Há um problema no valor inserido !");
			}
			String dataDigitada = dataField.getText();
			ContaController contaController = MainController.getContaController();
			contaController.adicionarPagamento(tipoSelecionado, valorFloat, dataDigitada, processoSelecionado);
			dataField.setValue(null);
			valorField.setValue(null);

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
