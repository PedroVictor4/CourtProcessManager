package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import controller.dto.TribunalDto;
import exception.PessoaException;
import exception.TribunalException;
import exception.TribunalExisteException;
import model.Tribunal;

public class TribunalController implements Serializable {

	private static final long serialVersionUID = 988879547203497320L;
	Map<String, Tribunal> tribunais = null;

	public TribunalController() {
		tribunais = new TreeMap<>();
	}

	public List<String> getStringTribunais() {
		List<String> lista = new ArrayList<>();
		for (Tribunal tribunal : tribunais.values()) {
			lista.add(String.format("%s", tribunal.toString()));
		}
		return lista;
	}
	public List<TribunalDto> getTribunaisDto() {
		List<TribunalDto> lista = new ArrayList<>();
		TribunalDto t ;
		for (Tribunal tribunal : tribunais.values()) {
			t = new TribunalDto(tribunal.getSigla(),tribunal.getDescricao(),tribunal.getSecao());
			lista.add(t);
		}
		return lista;
	}
	public List<Tribunal> getObjetoTribunais() {
		List<Tribunal> lista = new ArrayList<>();
		for (Tribunal tribunal : tribunais.values()) {
			lista.add(tribunal);
		}
		return lista;
	}
	public Tribunal getTribunalPorSigla(String sigla) throws PessoaException {
		if (sigla == null || !tribunais.containsKey(sigla)) {
			throw new PessoaException("Tribunal inválido");
		}
		return tribunais.get(sigla);
	}

	public void criarTribunal(String sigla, String sessao, String descricao) throws TribunalException, TribunalExisteException{
		// Criando o objeto Tribunal
		Tribunal tribunal = new Tribunal(sigla, sessao, descricao);

		verificaExistenciaTribunal(sigla);
			System.out.println("Tribunal criado: " + tribunal);
			tribunais.put(sigla, tribunal);
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");

		
		for (Tribunal item : tribunais.values())
			System.out.println((String.format("Nome: " + item.getSigla().toString())));
		MainController.save();
	}

	private int verificaExistenciaTribunal(String sigla) throws TribunalException, TribunalExisteException {
		if (tribunais.containsKey(sigla)) {
			System.out.println("Tribunal" + sigla + " já existe.");
			throw new TribunalExisteException("O Tribunal existe!");
		} else {

			return 0;
		}
	}
}
