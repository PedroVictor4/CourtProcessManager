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

import controller.ClienteController;
import controller.MainController;
import controller.dto.ClienteDto;
import model.Cliente;

public class ListarExtratoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> clienteComboBox;
	private JTextArea extratoArea;
	private JLabel lblNewLabel;
	ClienteController clienteController = MainController.getClienteController();

	public ListarExtratoView() {
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

		lblNewLabel = new JLabel("(Cadastro) Cliente:");
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
		clienteComboBox = new JComboBox<>();
		contentPane.add(clienteComboBox, gbc_clienteComboBox);

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
		scrollPane.setPreferredSize(new Dimension(400, 200));
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

	}

	private void actionListar() {
		String cadastro = (String) clienteComboBox.getSelectedItem();
		if (cadastro == null || cadastro.isBlank()) {
			JOptionPane.showMessageDialog(this, "Selecione um cliente primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Obtem o extrato do cliente selecionado e exibe no JTextArea
		StringBuilder extrato = new StringBuilder();
		Cliente cliente = clienteController.verificaExistenciaCliente(cadastro);
		extrato.append(cliente.getExtratoConta().toString());
		extrato.append(String.format("\nSaldo na conta do cliente: %.2f\n", cliente.getSaldoContas()));
		extratoArea.setText(extrato.toString());

	}

	private void atualizarClienteComboBox() {
		List<ClienteDto> listaClientes = clienteController.getClientesDto();
		clienteComboBox.removeAllItems();
		for (ClienteDto cliente : listaClientes) {
			clienteComboBox.addItem(cliente.getPessoaCpf());
		}
	}
}
