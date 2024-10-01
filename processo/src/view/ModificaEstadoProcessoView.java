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
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.MainController;
import controller.ProcessoController;
import controller.dto.ProcessoDto;
import model.EFaseProcesso;

public class ModificaEstadoProcessoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Long> processoComboBox;
	private JComboBox<EFaseProcesso> EFaseProcessoComboBox;
	private long processoSelecionado;
	EFaseProcesso tipoSelecionado;

	public ModificaEstadoProcessoView() {
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

		JLabel tituloLabel = new JLabel("Modificar Fase");
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

		// Configurações para o label de fase processo
		GridBagConstraints gbcFaseProcessoLabel = new GridBagConstraints();
		gbcFaseProcessoLabel.insets = new Insets(10, 10, 10, 10);
		gbcFaseProcessoLabel.anchor = GridBagConstraints.WEST;
		gbcFaseProcessoLabel.gridx = 0;
		gbcFaseProcessoLabel.gridy = 2;
		JLabel faseProcessoLabel = new JLabel("Fase:");
		faseProcessoLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(faseProcessoLabel, gbcFaseProcessoLabel);

		// Configurações para o ComboBox que mostra os valores do enum EPagamento
		GridBagConstraints gbcFaseProcessoComboBox = new GridBagConstraints();
		gbcFaseProcessoComboBox.insets = new Insets(10, 10, 10, 10);
		gbcFaseProcessoComboBox.anchor = GridBagConstraints.WEST;
		gbcFaseProcessoComboBox.gridx = 1;
		gbcFaseProcessoComboBox.gridy = 2;

		// ComboBox para exibir os valores do enum EPagamento
		EFaseProcessoComboBox = new JComboBox<>(EFaseProcesso.values());
		EFaseProcessoComboBox.setPreferredSize(new Dimension(200, 30));
		contentPane.add(EFaseProcessoComboBox, gbcFaseProcessoComboBox);

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

		EFaseProcessoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoSelecionado = (EFaseProcesso) EFaseProcessoComboBox.getSelectedItem();
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
					processoSelecionado = (long) processoComboBox.getSelectedItem();
					//System.out.println("Cliente selecionado: " + processoSelecionado.getNumero());
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

			ProcessoController processoController = MainController.getProcessoController();
			processoController.modificaFaseProcesso(tipoSelecionado, processoSelecionado);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}

	public void atualizarProcessoComboBox() {
		ProcessoController processoController = MainController.getProcessoController();
		List<ProcessoDto> listaProcessos = processoController.getProcessosDto();
		processoComboBox.removeAllItems();
		for (ProcessoDto processo : listaProcessos) {
			processoComboBox.addItem(processo.getNumero());
		}
	}

}
