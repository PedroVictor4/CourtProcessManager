package exception;

public class PessoaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public PessoaExisteException(String message) {
		super(message);
	}

}
