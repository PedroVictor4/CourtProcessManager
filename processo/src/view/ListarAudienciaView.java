package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import controller.ProcessoController;
import controller.dto.AudienciaDto;
import controller.dto.ProcessoDto;

public class ListarAudienciaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabela;
	private ModeloTabelaImutavel modeloTabela;
	private JComboBox<Long> processoComboBox;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public ListarAudienciaView() {
		inicializar();
	}

	private void inicializar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_painelConteudo = new GridBagLayout();
		gbl_painelConteudo.columnWidths = new int[] { 3, 3, 3 };
		gbl_painelConteudo.rowHeights = new int[] { 3, 3, 3 };
		gbl_painelConteudo.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_painelConteudo.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_painelConteudo);

		modeloTabela = new ModeloTabelaImutavel(new Object[] { "Advogado", "Data", "Recomendação", "Descrição" }, 0);

		// Cria o JComboBox para seleção do número do processo
		GridBagConstraints gbc_processoComboBox = new GridBagConstraints();
		gbc_processoComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_processoComboBox.insets = new Insets(10, 10, 5, 5);
		gbc_processoComboBox.gridx = 0;
		gbc_processoComboBox.gridy = 0;
		gbc_processoComboBox.ipady = 10;
		processoComboBox = new JComboBox<>();
		contentPane.add(processoComboBox, gbc_processoComboBox);

		// Adiciona listeners ao JComboBox
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

		// Cria a JTable com o modelo imutável
		tabela = new JTable(modeloTabela);
		JScrollPane painelRolagem = new JScrollPane(tabela);
		GridBagConstraints gbc_painelRolagem = new GridBagConstraints();
		gbc_painelRolagem.gridheight = 2;
		gbc_painelRolagem.gridwidth = 2;
		gbc_painelRolagem.insets = new Insets(0, 0, 0, 5);
		gbc_painelRolagem.gridx = 0;
		gbc_painelRolagem.gridy = 1;
		gbc_painelRolagem.fill = GridBagConstraints.BOTH;
		contentPane.add(painelRolagem, gbc_painelRolagem);

		// Cria o botão "Listar"
		JButton botaoListar = new JButton("Listar");
		GridBagConstraints gbc_botaoListar = new GridBagConstraints();
		gbc_botaoListar.insets = new Insets(0, 0, 5, 0);
		gbc_botaoListar.gridx = 2;
		gbc_botaoListar.gridy = 0;
		gbc_botaoListar.anchor = GridBagConstraints.EAST;
		contentPane.add(botaoListar, gbc_botaoListar);
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
	}

	// Atualiza os números de processos no ComboBox
	private void atualizarProcessoComboBox() {
		ProcessoController processoController = MainController.getProcessoController();
		List<Long> listaProcessos = new ArrayList<Long>();
		for (ProcessoDto processoDto : processoController.getProcessosDto()) {
			listaProcessos.add(processoDto.numero);
		}
		processoComboBox.removeAllItems();
		for (Long numeroProcesso : listaProcessos) {
			processoComboBox.addItem(numeroProcesso); // Adiciona o número do processo ao ComboBox
		}
	}

	private void actionListar() {
		ProcessoController processoController = MainController.getProcessoController();

		// Limpa a tabela antes de listar os dados
		modeloTabela.setRowCount(0);

		// Obtem o número do processo selecionado
		
		try {
		Long numeroProcesso = (Long) processoComboBox.getSelectedItem();
		

		// Obtem a lista de audiências associadas ao processo e preenche a tabela
		List<AudienciaDto> audiencias = processoController.getAudiencias(numeroProcesso);
		for (AudienciaDto audiencia : audiencias) {
			modeloTabela.addRow(new Object[] { audiencia.getAdvogado().getNome(),
					dateFormat.format(audiencia.getData()), audiencia.getRecomendacao(), audiencia.getDescricao(), });
		}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
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
