package model;

import java.io.Serializable;
import exception.TribunalException;
import util.VerificadorNulo;

public class Tribunal implements Serializable {

	private static final long serialVersionUID = -5242369707305899479L;
	private final String sigla;
	private final String descricao;
	private final String secao;

	public Tribunal(String sigla, String sessao, String descricao) throws TribunalException {
		if (VerificadorNulo.isNull(sigla)) {
			throw new TribunalException("A sigla não pode ser vazia");
		}
		this.sigla = sigla;
		if (VerificadorNulo.isNull(sessao)) {
			throw new TribunalException("A sessão não pode ser vazia");
		}
		this.secao = sessao;
		if (VerificadorNulo.isNull(descricao)) {
			throw new TribunalException("A descrição não pode ser vazia");
		}
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSecao() {
		return secao;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sigla: ").append(sigla + "\n");
		sb.append("Descrição: ").append(descricao + "\n");
		sb.append("Seção: ").append(secao);
		return sb.toString();
	}
}
