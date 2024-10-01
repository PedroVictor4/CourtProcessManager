package model;

import java.io.Serializable;
import java.util.Date;

import exception.AudienciaException;
import util.VerificadorNulo;

public class Audiencia implements Serializable {

	private static final long serialVersionUID = 8599067627778996107L;
	private final Advogado advogado;
	private final Date data;
	private final String recomendacao;
	private final String descricao;

	public Audiencia(Advogado advogado, String recomendacao, String descricao, Date data) throws AudienciaException {
		if (VerificadorNulo.isNull(data)) {
			throw new AudienciaException("A data da audiência não pode estar vazia !");
		}
		this.data = data;
		if (VerificadorNulo.isNull(advogado)) {
			throw new AudienciaException("O advogado não pode ser vazio !");
		}
		this.advogado = advogado;
		if (VerificadorNulo.isNull(recomendacao)) {
			throw new AudienciaException("A recomendação não pode estar vazia !");
		}
		this.recomendacao = recomendacao;
		if (VerificadorNulo.isNull(descricao)) {
			throw new AudienciaException("A descricao não pode estar vazia !");
		}
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public Date getData() {
		return data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Advogado: \n").append(advogado.toString()).append("\n");
		sb.append("Data: ").append(data).append("\n");
		sb.append("Recomendação: ").append(recomendacao).append("\n");
		sb.append("Descrição: ").append(descricao).append("\n");
		return sb.toString();
	}
}
