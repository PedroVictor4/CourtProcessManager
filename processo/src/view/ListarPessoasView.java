package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import controller.PessoaController;
import controller.dto.PessoaDto;

public class ListarPessoasView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabelaPessoas;

	public ListarPessoasView() {
		inicialize();
	}

	private void inicialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 100, 200, 120, 160, 10 };
		gbl_contentPane.rowHeights = new int[] { 300, 50 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Cria a tabela com um modelo padrão
		tabelaPessoas = new JTable();
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Email", "Telefone", "Cadastro" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};
		tabelaPessoas.setModel(model);

		tabelaPessoas.getColumnModel().getColumn(0).setPreferredWidth(200); // Nome
		tabelaPessoas.getColumnModel().getColumn(1).setPreferredWidth(200); // Email
		tabelaPessoas.getColumnModel().getColumn(2).setPreferredWidth(200); // Telefone
		tabelaPessoas.getColumnModel().getColumn(3).setPreferredWidth(200); // Cadastro

		JScrollPane scrollPane = new JScrollPane(tabelaPessoas);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 1;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH; // Expande o JScrollPane
		contentPane.add(scrollPane, gbc_scrollPane);

		// Cria o botão "Listar"
		JButton btnListar = new JButton("Listar");
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.insets = new Insets(0, 0, 0, 0);
		gbc_btnListar.gridx = 4;
		gbc_btnListar.gridy = 1;
		gbc_btnListar.anchor = GridBagConstraints.EAST;
		contentPane.add(btnListar, gbc_btnListar);

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
	}

	private void actionListar() {
		// Obtém a lista de pessoas do controlador
		PessoaController pessoaController = MainController.getPessoaController();
		List<PessoaDto> listaPessoas = pessoaController.getPessoaDto();

		// Limpa a tabela antes de inserir novos dados
		DefaultTableModel model = (DefaultTableModel) tabelaPessoas.getModel();
		model.setRowCount(0);

		// Preenche a tabela com as informações de cada pessoa
		for (PessoaDto pessoa : listaPessoas) {
			String cadastro = pessoa.getCadastro(); // Retorna o CPF ou CNPJ
			model.addRow(new Object[] { pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), cadastro });
		}
	}
}
