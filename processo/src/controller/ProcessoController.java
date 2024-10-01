package controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import controller.dto.AudienciaDto;
import controller.dto.ProcessoDto;
import exception.AdvogadoException;
import exception.AudienciaException;
import exception.ClienteException;
import exception.DataException;
import exception.PessoaException;
import exception.ProcessoException;
import exception.ProcessoExisteException;
import model.Advogado;
import model.Audiencia;
import model.Cliente;
import model.EFaseProcesso;
import model.Pessoa;
import model.Processo;
import model.Tribunal;
import util.VerificadorNulo;

public class ProcessoController implements Serializable {

	private static final long serialVersionUID = 6755982137177681220L;
	Map<Long, Processo> processos = null;

	public ProcessoController() {
		processos = new TreeMap<>();
	}

	public List<String> getStringProcessos() {
		List<String> lista = new ArrayList<>();
		for (Processo processo : processos.values()) {
			lista.add(String.format("%s", processo.toString()));
		}
		return lista;
	}

	public List<Processo> getProcessos() {
		List<Processo> lista = new ArrayList<>();
		for (Processo processo : processos.values()) {
			lista.add(processo);
		}
		return lista;
	}

	public void criarProcesso(String numero, String tribunal, String possivelCliente, String dataDigitada,
			String parteContraria)
			throws ProcessoException, ProcessoExisteException, ClienteException, DataException, PessoaException {
		// Criando o objeto Processo

		long longNumero;
		ClienteController clienteController = MainController.getClienteController();
		PessoaController pessoaController = MainController.getPessoaController();
		TribunalController tribunalController = MainController.getTribunalController();
		Pessoa pessoaParteContraria = pessoaController.getPessoaPorCadastro(parteContraria);
		Tribunal objetoTribunal = tribunalController.getTribunalPorSigla(tribunal);
		System.out.println("Cheguei aqui !");
		if (VerificadorNulo.isNull(numero)) {
			throw new ProcessoException("O numero não pode ser vazio");
		}
		if (!numero.matches("\\d+")) {
			throw new ProcessoException("O Número tem letra!");
		} else {
			longNumero = Long.parseLong(numero);
		}
		if (VerificadorNulo.isNull(possivelCliente)) {
			throw new ProcessoException("O cliente não pode ser vazio");
		}
		if (VerificadorNulo.isNull(tribunal)) {
			throw new ProcessoException("O tribunal não pode ser vazio");
		}
		if (VerificadorNulo.isNull(parteContraria)) {
			throw new ProcessoException("A parte contraria não pode ser vazia");
		}
		Date dataConvertida = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			dataConvertida = dateFormat.parse(dataDigitada);
			System.out.println(dataConvertida);
			String dataFormatada = dateFormat.format(dataConvertida);
			System.out.println("Data convertida: " + dataFormatada);
		} catch (Exception ex) {
			System.out.println("Erro ao converter a data: " + ex.getMessage());
			throw new DataException("Formato da data inválido !");
		}
		verificaExistenciaProcesso(longNumero);
		Pessoa possivelClientePessoa = pessoaController.getPessoaPorCadastro(possivelCliente);
		System.out.println("O possivel cliente é : " + possivelCliente);
		Cliente cliente = clienteController.criarCliente(possivelClientePessoa);
		Processo processo = new Processo(longNumero, objetoTribunal, cliente, pessoaParteContraria, dataConvertida);
		processos.put(longNumero, processo);
		JOptionPane.showMessageDialog(null, "Salvo com sucesso");
		MainController.save();

		for (Processo item : processos.values())
			System.out.println((String.format("Numero: " + item.getNumero())));

	}

	private int verificaExistenciaProcesso(long numero) throws ProcessoException, ProcessoExisteException {
		if (processos.containsKey(numero)) {
			System.out.println("Processo" + numero + " já existe.");
			throw new ProcessoExisteException("O Processo já  existe!");
		} else {
			return 0;
		}
	}

	public Processo getProcessoPorNumero(long numero) throws ProcessoException {
		if (numero != 0l || processos.containsKey(numero)) {
			System.out.println("Entrei no if");
			Processo processoEncontrado = processos.get(numero);
			return processoEncontrado;
		} else {
			throw new ProcessoException("Escolha um processo válido");
		}
	}

	public void addAudiencia(String registroAdvogado, String recomendacao, String descricao, String data, long numero)
			throws ProcessoException, AudienciaException, AdvogadoException {
		if (processos.containsKey(numero)) {
			Date dataConvertida = null;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				dateFormat.setLenient(false);
				dataConvertida = dateFormat.parse(data);
				System.out.println(dataConvertida);
				String dataFormatada = dateFormat.format(dataConvertida);
				System.out.println("Data convertida: " + dataFormatada);
			} catch (Exception ex) {
				System.out.println("Erro ao converter a data: " + ex.getMessage());
				throw new ProcessoException("Data inválida !");
			}

			Processo processoEncontrado = processos.get(numero);
			verificaExistenciaAudiencia(descricao, processoEncontrado);
			AdvogadoController advogadoController = MainController.getAdvogadoController();

			Advogado advogado = advogadoController.getAdvogadoPorRegistro(registroAdvogado);

			Audiencia audiencia = new Audiencia(advogado, recomendacao, descricao, dataConvertida);
			processoEncontrado.addAudiencia(audiencia);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			System.out.println("Audiência : " + audiencia);
			MainController.save();
			return;

		} else {
			throw new ProcessoException("O processo não existe !");
		}
	}

	private int verificaExistenciaAudiencia(String descricao, Processo processo) throws AudienciaException {
		// Percorre a lista de audiências associadas ao processo
		for (Audiencia audiencia : processo.getAudiencias()) {
			if (audiencia.getDescricao().equalsIgnoreCase(descricao)) {
				System.out.println("Audiência com a descrição " + descricao + " já existe.");
				throw new AudienciaException("A audiência com essa descrição já existe!");
			}
		}
		return 0;
	}

	public void modificaFaseProcesso(EFaseProcesso faseProcesso, long numero) throws ProcessoException {
		if (processos.containsKey(numero)) {
			Processo processoEncontrado = processos.get(numero);
			System.out.println(processoEncontrado.getFaseProcesso());
			processoEncontrado.setFaseProcesso(faseProcesso);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			System.out.println(processoEncontrado.getFaseProcesso());
			return;
		} else {
			throw new ProcessoException("O processo não existe !");
		}
	}

	public List<AudienciaDto> getAudiencias(long numero) throws ProcessoException {
		Processo processo = getProcessoPorNumero(numero);
		List<AudienciaDto> lista = new ArrayList<AudienciaDto>();
		for (Audiencia audiencia : processo.getAudiencias()) {
			AudienciaDto a = new AudienciaDto();
			a.setAdvogado(audiencia.getAdvogado());
			a.setData(audiencia.getData());
			a.setDescricao(audiencia.getDescricao());
			a.setRecomendacao(audiencia.getRecomendacao());
			lista.add(a);

		}

		return lista;
	}

	public List<ProcessoDto> getProcessosDto() {
		List<ProcessoDto> lista = new ArrayList<ProcessoDto>();
		for (Processo processo : processos.values()) {
			ProcessoDto a = new ProcessoDto();
			a.setNumero(processo.getNumero());
			a.setData(processo.getData());
			a.setFaseProcesso(processo.getFaseProcesso());
			a.setTribunal(processo.getTribunal());
			a.setCadastroCliente(processo.getCliente().getPessoa().getCadastroRf());
			a.setCadastroParteContraria(processo.getParteContraria().getCadastroRf());
			lista.add(a);

		}

		return lista;
	}

}
