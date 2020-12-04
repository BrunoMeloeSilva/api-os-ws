package com.github.brunomeloesilva.os.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunomeloesilva.os.domain.model.OrdemServicoModel;
import com.github.brunomeloesilva.os.domain.repository.OrdemServicoRepository;

@RestController
@RequestMapping("/ordensservicos")
public class OrdemServicoController {
	
	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public ResponseEntity<List<OrdemServicoModel>> getAllOrdemServicoModel(){
		return ResponseEntity.ok(ordemServicoRepository.findAll());
	}
	
}
