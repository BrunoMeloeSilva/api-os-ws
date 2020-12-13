package com.github.brunomeloesilva.os.api.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunomeloesilva.os.api.representation.ComentarioInput;
import com.github.brunomeloesilva.os.domain.model.ComentarioModel;
import com.github.brunomeloesilva.os.domain.model.OrdemServicoModel;
import com.github.brunomeloesilva.os.domain.repository.ComentarioRepository;
import com.github.brunomeloesilva.os.domain.repository.OrdemServicoRepository;

@RestController
@RequestMapping("/ordensservicos/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	//TODO Deveria retornar 204 No Content, quando não houver comentarios registrados no DB
	//TODO Deveria usar representation model
	//TODO Deveria usar algo mais especifico que RuntimeException
	@GetMapping()
	public List<ComentarioModel> getAllComentarioModels(@PathVariable Long ordemServicoId){
		OrdemServicoModel ordemServicoModel = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new RuntimeException("Não existe Ordem de Serviço para o Id informado."));
		List<ComentarioModel> listComentarioModels = ordemServicoModel.getComentarios();
		return listComentarioModels;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel addComentarioModel(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
		OrdemServicoModel ordemServicoModel = ordemServicoRepository.findById(ordemServicoId).orElseThrow(()->new RuntimeException("Não existe Ordem de Serviço para o Id informado."));
		ComentarioModel comentarioModel = new ComentarioModel();
		comentarioModel.setFkos(ordemServicoModel);
		comentarioModel.setDescricao(comentarioInput.getComentario());
		comentarioModel.setCriado(OffsetDateTime.now());
		return comentarioRepository.save(comentarioModel);
	}

}
