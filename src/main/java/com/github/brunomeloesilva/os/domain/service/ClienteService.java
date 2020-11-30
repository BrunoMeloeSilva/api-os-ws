package com.github.brunomeloesilva.os.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.brunomeloesilva.os.domain.exception.EmailJaExisteException;
import com.github.brunomeloesilva.os.domain.model.ClienteModel;
import com.github.brunomeloesilva.os.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteModel saveClienteIfNotExistsEmail(ClienteModel clienteModel) {
		//TODO: Está comparando o objeto todo, deveria comparar só o campo email.
		ClienteModel clienteModelAux = clienteRepository.findByEmail(clienteModel.getEmail());
		if (clienteModelAux != null && !clienteModel.equals(clienteModelAux)) {
			throw new EmailJaExisteException("O email informado já foi registrado, informe outro email.");
		}
		return clienteRepository.save(clienteModel);
	}

	public void excluir(Long idCliente) {
		clienteRepository.deleteById(idCliente);
	}
}
