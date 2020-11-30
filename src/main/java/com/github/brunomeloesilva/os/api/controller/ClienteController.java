package com.github.brunomeloesilva.os.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunomeloesilva.os.api.representation.ClienteRepresentation;
import com.github.brunomeloesilva.os.domain.model.ClienteModel;
import com.github.brunomeloesilva.os.domain.repository.ClienteRepository;
import com.github.brunomeloesilva.os.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public List<ClienteRepresentation> getAllClientes() {
		//TODO: Implement 204 No Content no getAllClientes
		return toAllClienteRepresentation(clienteRepository.findAll());
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteRepresentation> getCliente(@PathVariable Long idCliente) {
		Optional<ClienteModel> ClienteModelOptional = clienteRepository.findById(idCliente);
		
		if(ClienteModelOptional.isPresent()) {
			return ResponseEntity.ok(toClienteRepresentation(ClienteModelOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteRepresentation addCliente(@RequestBody ClienteRepresentation clienteRepresentation) {
		//TODO: Tratar o erro de ausencia de atributos, quando os objetos não casam.
		//TODO: Tratar o erro de presenca de atributos com valores errados.
		ClienteModel clienteModel = clienteService.saveClienteIfNotExistsEmail(toClienteModel(clienteRepresentation)); 
		return toClienteRepresentation(clienteModel);
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<ClienteRepresentation> updateCliente(@PathVariable Long idCliente, @RequestBody ClienteRepresentation clienteRepresentation) {
		if(!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepresentation.setId(idCliente);
		ClienteModel clienteModel = clienteService.saveClienteIfNotExistsEmail(toClienteModel(clienteRepresentation));

		return ResponseEntity.ok(toClienteRepresentation(clienteModel));
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Void> remover(@PathVariable Long idCliente) {
		if(!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.excluir(idCliente);
		return ResponseEntity.noContent().build();
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
		ClienteModel clienteModel =  modelMapper.map(clienteRepresentation, ClienteModel.class);
		clienteModel.setPk(clienteRepresentation.getId());
		return clienteModel;
	}
}
