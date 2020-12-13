package com.github.brunomeloesilva.os.api.controller;

import java.util.List;
import java.util.Optional;

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
import com.github.brunomeloesilva.os.domain.repository.ClienteRepository;
import com.github.brunomeloesilva.os.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteRepresentation>> getAllClientes() {
		List<ClienteRepresentation> ListClientes = clienteService.getAllClientes();
		if(ListClientes.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(ListClientes);
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteRepresentation> getCliente(@PathVariable Long idCliente) {
		Optional<ClienteRepresentation> clienteRepresentation = clienteService.getCliente(idCliente);
		if(clienteRepresentation.isPresent()) 
			return ResponseEntity.ok(clienteRepresentation.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteRepresentation addCliente(@RequestBody ClienteRepresentation clienteRepresentation) {
		return clienteService.save(clienteRepresentation);
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<ClienteRepresentation> updateCliente(@PathVariable Long idCliente, @RequestBody ClienteRepresentation clienteRepresentation) {
		Optional<ClienteRepresentation> clienteRepresentationOptional = clienteService.updateCliente(idCliente, clienteRepresentation);
		if(clienteRepresentationOptional.isPresent()) 
			return ResponseEntity.ok(clienteRepresentationOptional.get());
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Void> remover(@PathVariable Long idCliente) {
		if(!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(idCliente);
		return ResponseEntity.noContent().build();
	}
}
