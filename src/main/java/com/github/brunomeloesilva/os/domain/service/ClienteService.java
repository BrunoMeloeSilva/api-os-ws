package com.github.brunomeloesilva.os.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.brunomeloesilva.os.api.representation.ClienteRepresentation;
import com.github.brunomeloesilva.os.domain.model.ClienteModel;
import com.github.brunomeloesilva.os.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<ClienteRepresentation> getAllClientes(){
		List<ClienteModel> ListClientesModel = clienteRepository.findAll();
		return toAllClienteRepresentation(ListClientesModel);
	}
	
	public Optional<ClienteRepresentation> getCliente(Long idCliente) {
		Optional<ClienteModel> clienteModel = clienteRepository.findById(idCliente);
		return clienteModel.isPresent() ? Optional.of(toClienteRepresentation(clienteModel.get())) : Optional.empty();
	}
	
	public ClienteRepresentation save(ClienteRepresentation clienteRepresentation) {
		ClienteModel clienteModel = clienteRepository.save(toClienteModel(clienteRepresentation)); 
		return toClienteRepresentation(clienteModel);
	}
	
	public Optional<ClienteRepresentation> updateCliente(Long idCliente, ClienteRepresentation clienteRepresentation) {
		if(clienteRepresentation != null) {
			clienteRepresentation.setId(idCliente);
			ClienteModel clienteModel = clienteRepository.save(toClienteModel(clienteRepresentation));
			return Optional.of(toClienteRepresentation(clienteModel));
		}else {
			return Optional.empty();
		}
	}
    
	
	/* Métodos Utilitártios da Classe */
	
	private ClienteRepresentation toClienteRepresentation(ClienteModel clienteModel) {
		ClienteRepresentation clienteRepresentation =  modelMapper.map(clienteModel, ClienteRepresentation.class);
		clienteRepresentation.setId(clienteModel.getPk());
		return clienteRepresentation;
	}
	
	private List<ClienteRepresentation> toAllClienteRepresentation(List<ClienteModel> ClienteModelList) {
		return ClienteModelList.stream().map(
				clienteModel -> toClienteRepresentation(clienteModel)).collect(Collectors.toList());
	}
	
	private ClienteModel toClienteModel(ClienteRepresentation clienteRepresentation) {
		ClienteModel clienteModel =  modelMapper.map(clienteRepresentation, ClienteModel.class);;
		clienteModel.setPk(clienteRepresentation.getId());
		return clienteModel;
	}
}
