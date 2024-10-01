package controller.dto;

import java.util.Date;

import model.Advogado;

public class AudienciaDto {
	public Advogado advogado;
	public Date data;
	public String recomendacao;
	public String descricao;

	public AudienciaDto(Advogado advogado, Date data, String recomendacao, String descricao) {
		super();
		this.advogado = advogado;
		this.data = data;
		this.recomendacao = recomendacao;
		this.descricao = descricao;
	}

	public AudienciaDto() {

	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
