package controller.dto;

public class TribunalDto {
	public String sigla;
	public String descricao;
	public String secao;

	public TribunalDto() {

	}

	public TribunalDto(String sigla, String descricao, String secao) {
		this.sigla = sigla;
		this.descricao = descricao;
		this.secao = secao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

}
