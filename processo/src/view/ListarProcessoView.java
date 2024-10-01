package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import controller.ProcessoController;
import controller.dto.ProcessoDto;

public class ListarProcessoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabela;
	private ModeloTabelaImutavel modeloTabela;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public ListarProcessoView() {
		inicialize();
	}

	private void inicialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 400, 100 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		// Cria o modelo da tabela imutável com colunas
		modeloTabela = new ModeloTabelaImutavel(
				new Object[] { "Número", "Data", "Fase Processo", "Tribunal", "Cliente", "Parte Contrária" }, 0);

		// Cria a JTable com o modelo imutável e a adiciona em um JScrollPane
		tabela = new JTable(modeloTabela);
		JScrollPane painelRolagem = new JScrollPane(tabela);
		GridBagConstraints gbc_painelRolagem = new GridBagConstraints();
		gbc_painelRolagem.insets = new Insets(10, 10, 10, 10);
		gbc_painelRolagem.gridx = 0;
		gbc_painelRolagem.gridy = 0;
		gbc_painelRolagem.gridheight = 2;
		gbc_painelRolagem.fill = GridBagConstraints.BOTH;
		contentPane.add(painelRolagem, gbc_painelRolagem);

		// Cria o botão "Listar"
		JButton botaoListar = new JButton("Listar");
		GridBagConstraints gbc_botaoListar = new GridBagConstraints();
		gbc_botaoListar.insets = new Insets(10, 10, 10, 10);
		gbc_botaoListar.gridx = 1;
		gbc_botaoListar.gridy = 0;
		gbc_botaoListar.anchor = GridBagConstraints.NORTH;
		contentPane.add(botaoListar, gbc_botaoListar);

		// Ação do botão "Listar"
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
	}

	private void actionListar() {
		ProcessoController processoController = MainController.getProcessoController();

		// Limpa a tabela antes de listar os dados
		modeloTabela.setRowCount(0);

		// Obtem a lista de processos e preenche a tabela
		List<ProcessoDto> processos = processoController.getProcessosDto();
		for (ProcessoDto processo : processos) {
			modeloTabela.addRow(new Object[] { processo.getNumero(), dateFormat.format(processo.getData()),
					processo.getFaseProcesso(), processo.getTribunal(), processo.getCadastroCliente(),
					processo.getCadastroParteContraria() });
		}
	}

	private class ModeloTabelaImutavel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		public ModeloTabelaImutavel(Object[] colunas, int linhas) {
			super(colunas, linhas);
		}

		@Override
		public boolean isCellEditable(int row, int column) {

			return false;
		}
	}
}
