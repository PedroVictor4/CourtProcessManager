package controller;

import java.io.Serializable;

import persistence.Serializer;

/* 
 * Design Pattern Singleton 
 */
public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private TribunalController tribunalController;
	private PessoaController pessoaController;
	private AdvogadoController advogadoController;
	private ProcessoController processoController;
	private ClienteController clienteController;
	private ContaController contaController;

	private MainController() {

		tribunalController = new TribunalController();
		pessoaController = new PessoaController();
		advogadoController = new AdvogadoController();
		processoController = new ProcessoController();
		clienteController = new ClienteController();
		contaController = new ContaController();

	}

	public static MainController getInstance() {
		return instance;
	}

	public static TribunalController getTribunalController() {
		return instance.tribunalController;
	}

	public static PessoaController getPessoaController() {
		return instance.pessoaController;
	}

	public static AdvogadoController getAdvogadoController() {
		return instance.advogadoController;
	}
	public static ProcessoController getProcessoController() {
		return instance.processoController;
	}
	public static ClienteController getClienteController() {
		return instance.clienteController;
	}
	public static ContaController getContaController() {
		return instance.contaController;
	}

	public static void load() {

		instance = Serializer.readFile();

		if (instance == null) {
			instance = new MainController();
		}
	}

	public static void save() {
		Serializer.writeFile(instance);
	}
}
