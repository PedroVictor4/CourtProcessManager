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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.MaskFormatter;

import controller.AdvogadoController;
import controller.MainController;
import controller.ProcessoController;
import controller.dto.AdvogadoDto;
import controller.dto.ProcessoDto;

public class AdicionarAudienciaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField recomendacaoField;
	private JTextArea descricaoField;
	private JFormattedTextField dataField;
	private JComboBox<String> advogadoComboBox;
	private String advogadoSelecionado;
	private JComboBox<Long> processoComboBox;
	private long processoSelecionado;
	ProcessoController processoController = MainController.getProcessoController();

	public AdicionarAudienciaView() {
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

		JLabel tituloLabel = new JLabel("Adicionar Audiência");
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

		// Configurações para a recomendação
		GridBagConstraints gbcRecomendacaoLabel = new GridBagConstraints();
		gbcRecomendacaoLabel.insets = new Insets(10, 10, 10, 10);
		gbcRecomendacaoLabel.gridx = 0;
		gbcRecomendacaoLabel.gridy = 2;
		gbcRecomendacaoLabel.anchor = GridBagConstraints.WEST;
		JLabel recomendacaoLabel = new JLabel("Recomendação:");
		recomendacaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(recomendacaoLabel, gbcRecomendacaoLabel);

		GridBagConstraints gbcRecomendacaoField = new GridBagConstraints();
		gbcRecomendacaoField.fill = GridBagConstraints.BOTH;
		gbcRecomendacaoField.insets = new Insets(10, 10, 10, 10);
		gbcRecomendacaoField.gridx = 1;
		gbcRecomendacaoField.gridy = 2;
		gbcRecomendacaoField.anchor = GridBagConstraints.WEST;
		recomendacaoField = new JTextField();
		recomendacaoField.setPreferredSize(new Dimension(200, 30));
		contentPane.add(recomendacaoField, gbcRecomendacaoField);

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
			e.printStackTrace();
			return;
		} // Configurações para o advogado
		dataField.setPreferredSize(new Dimension(80, 30));
		contentPane.add(dataField, gbcDataField);

		// Configurações para o JComboBox de prepostos
		GridBagConstraints gbcAdvogadoLabel = new GridBagConstraints();
		gbcAdvogadoLabel.insets = new Insets(10, 10, 10, 10);
		gbcAdvogadoLabel.anchor = GridBagConstraints.WEST;
		gbcAdvogadoLabel.gridx = 0;
		gbcAdvogadoLabel.gridy = 4;
		JLabel advogadoLabel = new JLabel("Advogado:");
		advogadoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(advogadoLabel, gbcAdvogadoLabel);

		GridBagConstraints gbcAdvogadoComboBox = new GridBagConstraints();
		gbcAdvogadoComboBox.insets = new Insets(10, 10, 10, 10);
		gbcAdvogadoComboBox.anchor = GridBagConstraints.WEST;
		gbcAdvogadoComboBox.gridx = 1;
		gbcAdvogadoComboBox.gridy = 4;
		advogadoComboBox = new JComboBox<>();
		advogadoComboBox.setPreferredSize(new Dimension(200, 30));
		contentPane.add(advogadoComboBox, gbcAdvogadoComboBox);

		// Configurações para a descrição
		GridBagConstraints gbcDescricaoLabel = new GridBagConstraints();
		gbcDescricaoLabel.insets = new Insets(10, 10, 10, 10);
		gbcDescricaoLabel.gridx = 0;
		gbcDescricaoLabel.gridy = 5;
		gbcDescricaoLabel.anchor = GridBagConstraints.WEST;
		JLabel descricaoLabel = new JLabel("Descrição:");
		descricaoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(descricaoLabel, gbcDescricaoLabel);

		GridBagConstraints gbcDescricaoField = new GridBagConstraints();
		gbcDescricaoField.fill = GridBagConstraints.BOTH;
		gbcDescricaoField.insets = new Insets(10, 10, 10, 10);
		gbcDescricaoField.gridx = 1;
		gbcDescricaoField.gridy = 5;
		gbcDescricaoField.anchor = GridBagConstraints.WEST;
		descricaoField = new JTextArea();
		descricaoField.setPreferredSize(new Dimension(200, 50));
		contentPane.add(descricaoField, gbcDescricaoField);

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

		advogadoComboBox.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				atualizarAdvogadoComboBox();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});
		advogadoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Verifica se uma opção foi realmente selecionada
				if (advogadoComboBox.getSelectedIndex() > -1) {
					advogadoSelecionado = (String) advogadoComboBox.getSelectedItem();
					// System.out.println("Pessoa selecionada: " + advogadoSelecionado.getNome());
				}
			}
		});

		// Adiciona Listeners aos ComboBoxes
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
					processoSelecionado = (long) processoComboBox.getSelectedItem();
					// System.out.println("Cliente selecionado: " +
					// processoSelecionado.getNumero());
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
			String recomendacao = recomendacaoField.getText();
			String dataDigitada = dataField.getText();
			String descricao = descricaoField.getText();
			processoController.addAudiencia(advogadoSelecionado, recomendacao, descricao, dataDigitada,
					processoSelecionado);
			dataField.setValue(null);
			recomendacaoField.setText("");
			descricaoField.setText("");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}

	public void atualizarProcessoComboBox() {
		List<ProcessoDto> listaProcessos = processoController.getProcessosDto();
		processoComboBox.removeAllItems();
		for (ProcessoDto processo : listaProcessos) {
			processoComboBox.addItem(processo.getNumero());
		}
	}

	public void atualizarAdvogadoComboBox() {
		AdvogadoController advogadoController = MainController.getAdvogadoController();
		List<AdvogadoDto> listaAdvogados = advogadoController.getAdvogadoDto();
		advogadoComboBox.removeAllItems();
		for (AdvogadoDto advogado : listaAdvogados) {
			advogadoComboBox.addItem(advogado.getRegistro());
		}
	}

}
