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
import controller.TribunalController;
import controller.dto.TribunalDto;

public class ListarTribunalView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel painelConteudo;
	private JTable tabela;
	private ModeloTabelaImutavel modeloTabela;

	public ListarTribunalView() {
		inicializar();
	}
	
	private void inicializar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        painelConteudo = new JPanel();
        painelConteudo.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(painelConteudo);
        GridBagLayout gbl_painelConteudo = new GridBagLayout();
        gbl_painelConteudo.columnWidths = new int[] { 3, 3, 3 }; 
        gbl_painelConteudo.rowHeights = new int[] { 3, 3, 3 }; 
        gbl_painelConteudo.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE }; 
        gbl_painelConteudo.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE }; 
        painelConteudo.setLayout(gbl_painelConteudo);

       
        modeloTabela = new ModeloTabelaImutavel(new Object[]{"Sigla", "Descrição", "Seção"}, 0);
        
        // Cria a JTable com o modelo imutável e a adiciona em um JScrollPane
        tabela = new JTable(modeloTabela);
        JScrollPane painelRolagem = new JScrollPane(tabela);
        GridBagConstraints gbc_painelRolagem = new GridBagConstraints();
        gbc_painelRolagem.gridheight = 2;
        gbc_painelRolagem.gridwidth = 2;
        gbc_painelRolagem.insets = new Insets(0, 0, 5, 5);
        gbc_painelRolagem.gridx = 0;
        gbc_painelRolagem.gridy = 0;
        gbc_painelRolagem.fill = GridBagConstraints.BOTH;
        painelConteudo.add(painelRolagem, gbc_painelRolagem);

        // Cria o botão "Listar" e o coloca à direita
        JButton botaoListar = new JButton("Listar");
        GridBagConstraints gbc_botaoListar = new GridBagConstraints();
        gbc_botaoListar.insets = new Insets(0, 0, 5, 0);
        gbc_botaoListar.gridx = 2; 
        gbc_botaoListar.gridy = 0; 
        gbc_botaoListar.anchor = GridBagConstraints.EAST; 
        painelConteudo.add(botaoListar, gbc_botaoListar);
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
	}
	
	private void actionListar() {
		TribunalController tribunalController = MainController.getTribunalController();

		// Limpa a tabela antes de listar os dados
		modeloTabela.setRowCount(0);

		// Obtem a lista de tribunais e preenche a tabela
		List<TribunalDto> tribunais = tribunalController.getTribunaisDto();
		for (TribunalDto tribunal : tribunais) {
			modeloTabela.addRow(new Object[]{tribunal.getSigla(), tribunal.getDescricao(), tribunal.getSecao()});
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


