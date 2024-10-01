package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import controller.dto.ClienteDto;
import exception.ClienteException;
import model.Cliente;
import model.Pessoa;

public class ClienteController implements Serializable {

	private static final long serialVersionUID = -8741365841150387253L;
	Map<String, Cliente> clientes = null;

	public ClienteController() {
		clientes = new TreeMap<>();
	}

	public Cliente criarCliente(Pessoa pessoa) throws ClienteException {
		Cliente clienteExistente = verificaExistenciaCliente(pessoa.getCadastroRf());
		// Criando o objeto Cliente

        if (clienteExistente == null) {  // Se o cliente não existe, criar um novo
            Cliente novoCliente = new Cliente(pessoa);
            clientes.put(pessoa.getCadastroRf(), novoCliente);
            MainController.save();
            JOptionPane.showMessageDialog(null, "O cliente foi salvo com sucesso");

            // Exibir todos os clientes salvos até o momento
            System.out.println("############################################################");
            for (Cliente item : clientes.values()) {
                System.out.println(String.format("Cliente do for : " + item.getPessoa().toString()));
            }
            System.out.println("############################################################");
            return novoCliente;
        } else {
            JOptionPane.showMessageDialog(null, "O cliente já existe.");
            return clienteExistente;
        }
	}
	public Cliente verificaExistenciaCliente(String cadastro) {
        if (clientes.containsKey(cadastro)) {
            Cliente clienteEncontrado = clientes.get(cadastro);
            return clienteEncontrado;
        } else {
            return null;
        }
    }

	public List<Cliente> getClientes() {
		List<Cliente> lista = new ArrayList<Cliente>();
		for (Cliente cliente : clientes.values()) {
			lista.add(cliente);

		}
		return lista;
	}
	public List<ClienteDto> getClientesDto() {
		List<ClienteDto> lista = new ArrayList<ClienteDto>();
		for (Cliente cliente : clientes.values()) {
			ClienteDto clienteDto = new ClienteDto();
			clienteDto.setPessoaCpf(cliente.getPessoa().getCadastroRf());
			clienteDto.setNome(cliente.getPessoa().getNome());
			clienteDto.setProcessos(cliente.getProcessos());
			lista.add(clienteDto);
			
		}
		return lista;
	}
	public Cliente getClientePorCadastro(String cadastro) {
		return clientes.get(cadastro);
	}

}