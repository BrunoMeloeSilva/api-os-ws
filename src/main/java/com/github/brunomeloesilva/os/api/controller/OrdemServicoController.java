package com.github.brunomeloesilva.os.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunomeloesilva.os.api.representation.OrdemServicoInput;
import com.github.brunomeloesilva.os.api.representation.OrdemServicoRepresentation;
import com.github.brunomeloesilva.os.domain.model.OrdemServicoModel;
import com.github.brunomeloesilva.os.domain.repository.OrdemServicoRepository;
import com.github.brunomeloesilva.os.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordensservicos")
public class OrdemServicoController {

	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	OrdemServicoService ordemServicoService;
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoRepresentation> getOrdemServico(@PathVariable Long ordemServicoId) {
		Optional<OrdemServicoModel> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		if(ordemServico.isPresent()) {
			return ResponseEntity.ok(ordemServicoService.getOrdemServicoRepresentation(ordemServicoId));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemServicoRepresentation>> getAllOrdemServico(){
		List<OrdemServicoRepresentation> ordemServicoRepresentations = ordemServicoService.getAllOrdemServicoRepresentation();
		if(ordemServicoRepresentations.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ordemServicoRepresentations);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoRepresentation AddOrdemServicoRepresentation(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {	
		return ordemServicoService.save(ordemServicoInput);
	}
	
	@PutMapping("/{ordemServicoId}/fechar")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void fecharOS(@PathVariable Long ordemServicoId) {
		ordemServicoService.fecharOrdemServico(ordemServicoId);
	}
	
}
