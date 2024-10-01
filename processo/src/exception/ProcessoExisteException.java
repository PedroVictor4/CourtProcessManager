package exception;

public class ProcessoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProcessoExisteException(String message) {
		super(message);
	}

}
