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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.MainController;
import controller.ProcessoController;
import controller.dto.ProcessoDto;
import model.Processo;

public class ListarExtratoProcessoView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Long> processoComboBox;
	private JTextArea extratoArea;
	private JLabel lblNewLabel;
	private long processoSelecionado = 0;
	ProcessoController processoController = MainController.getProcessoController();

	public ListarExtratoProcessoView() {
		inicializar();
	}

	private void inicializar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_painelConteudo = new GridBagLayout();
		gbl_painelConteudo.columnWidths = new int[] { 150, 200, 100 }; 
		gbl_painelConteudo.rowHeights = new int[] { 30, 30, 0 }; 
		gbl_painelConteudo.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_painelConteudo.rowWeights = new double[] { 0.0, 0.0, 1.0 };
		contentPane.setLayout(gbl_painelConteudo);

		lblNewLabel = new JLabel("(Número) Processo:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		// Cria o JComboBox para seleção de cliente
		GridBagConstraints gbc_clienteComboBox = new GridBagConstraints();
		gbc_clienteComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_clienteComboBox.insets = new Insets(10, 10, 5, 5);
		gbc_clienteComboBox.gridx = 1;
		gbc_clienteComboBox.gridy = 0;
		gbc_clienteComboBox.ipady = 10; 
		processoComboBox = new JComboBox<>();
		contentPane.add(processoComboBox, gbc_clienteComboBox);

		// Cria o JButton "Listar"
		JButton botaoListar = new JButton("Listar");
		GridBagConstraints gbc_botaoListar = new GridBagConstraints();
		gbc_botaoListar.insets = new Insets(10, 0, 5, 10);
		gbc_botaoListar.gridx = 2;
		gbc_botaoListar.gridy = 0;
		gbc_botaoListar.anchor = GridBagConstraints.EAST;
		contentPane.add(botaoListar, gbc_botaoListar);

		// Cria o JTextArea para exibir o extrato
		extratoArea = new JTextArea();
		extratoArea.setEditable(false);
		extratoArea.setLineWrap(true);
		extratoArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(extratoArea);
		scrollPane.setPreferredSize(new Dimension(400, 200)); // Ajuste o tamanho do JScrollPane
		GridBagConstraints gbc_extratoArea = new GridBagConstraints();
		gbc_extratoArea.insets = new Insets(10, 10, 10, 10);
		gbc_extratoArea.fill = GridBagConstraints.BOTH;
		gbc_extratoArea.gridx = 0;
		gbc_extratoArea.gridy = 2;
		gbc_extratoArea.gridwidth = 3; // Ocupa todas as colunas
		gbc_extratoArea.weightx = 1.0;
		gbc_extratoArea.weighty = 1.0;
		contentPane.add(scrollPane, gbc_extratoArea);

		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
		// Adiciona Listeners aos ComboBoxes
		processoComboBox.addPopupMenuListener(new PopupMenuListener() {
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
		processoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (processoComboBox.getSelectedIndex() > -1) {
					processoSelecionado = (long) processoComboBox.getSelectedItem();
					//System.out.println("Cliente selecionado: " + processoSelecionado.getNumero());
				}
			}
		});


	}

	private void actionListar() {
		//if (processoSelecionado == 0 ) {
			//JOptionPane.showMessageDialog(this, "Selecione um processo primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
			//return;
		//}
		// Obtem o extrato do cliente selecionado e exibe no JTextArea
		try {
		StringBuilder extrato = new StringBuilder();
		Processo processo = processoController.getProcessoPorNumero(processoSelecionado);
		extrato.append(processo.getExtrato());
		extratoArea.setText(extrato.toString());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}

	private void atualizarClienteComboBox() {
		List<ProcessoDto> listaProcessos = processoController.getProcessosDto();
		processoComboBox.removeAllItems();
		for (ProcessoDto processo : listaProcessos) {
			processoComboBox.addItem(processo.getNumero());
		}
	}
}