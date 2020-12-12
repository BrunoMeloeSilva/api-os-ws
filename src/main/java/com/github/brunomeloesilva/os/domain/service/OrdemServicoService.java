package com.github.brunomeloesilva.os.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.brunomeloesilva.os.api.representation.OrdemServicoInput;
import com.github.brunomeloesilva.os.api.representation.OrdemServicoRepresentation;
import com.github.brunomeloesilva.os.domain.exception.ClienteNaoExisteException;
import com.github.brunomeloesilva.os.domain.exception.OrdemServicoNaoExisteException;
import com.github.brunomeloesilva.os.domain.model.ClienteModel;
import com.github.brunomeloesilva.os.domain.model.OrdemServicoModel;
import com.github.brunomeloesilva.os.domain.model.StatusOS;
import com.github.brunomeloesilva.os.domain.repository.ClienteRepository;
import com.github.brunomeloesilva.os.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public OrdemServicoRepresentation getOrdemServicoRepresentation(Long ordemServicoId) {
		
		return toOrdemServicoRepresentation(ordemServicoRepository.findById(ordemServicoId).get());
	}
	
	public List<OrdemServicoRepresentation> getAllOrdemServicoRepresentation(){
		return toAllOrdemServicoRepresentation(ordemServicoRepository.findAll());
	}
	
	public OrdemServicoRepresentation save(OrdemServicoInput ordemServicoInput) {
		OrdemServicoModel ordemServicoModel = toOrdemServicoModel(ordemServicoInput);
		ordemServicoModel.setStatus(StatusOS.ABERTA);
		ordemServicoModel.setAbertura(OffsetDateTime.now());
		return toOrdemServicoRepresentation(ordemServicoRepository.save(ordemServicoModel));	
	}
	
	
/* Métodos Utilitártios da Classe */
	
	private OrdemServicoRepresentation toOrdemServicoRepresentation(OrdemServicoModel ordemServicoModel) {
		OrdemServicoRepresentation ordemServicoRepresentation =  modelMapper.map(ordemServicoModel, OrdemServicoRepresentation.class);
		ordemServicoRepresentation.setId(ordemServicoModel.getPk());
		//TODO: Esse getFkcliente na verdade retorna um cliente inteiro e a chamada do metodo nao deixa isso claro
		ordemServicoRepresentation.setCliente(clienteService.toClienteRepresentation(ordemServicoModel.getFkcliente()));
		return ordemServicoRepresentation;
	}
	
	private List<OrdemServicoRepresentation> toAllOrdemServicoRepresentation(List<OrdemServicoModel> ordemServicoModelList) {
		return ordemServicoModelList.stream().map(
				ordemServicoModel -> toOrdemServicoRepresentation(ordemServicoModel)).collect(Collectors.toList());
	}
	
	private OrdemServicoModel toOrdemServicoModel(OrdemServicoInput ordemServicoInput) {
		OrdemServicoModel ordemServicoModel =  modelMapper.map(ordemServicoInput, OrdemServicoModel.class);
		ClienteModel clienteModel = clienteRepository.findById(ordemServicoInput.getClienteId()).orElseThrow(
				() -> new ClienteNaoExisteException("O id do cliente passado não existe."));
		ordemServicoModel.setFkcliente(clienteModel);
		return ordemServicoModel;
	}

	public void fecharOrdemServico(Long ordemServicoId) {
		OrdemServicoModel ordemServicoModel = 
				ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new OrdemServicoNaoExisteException("O id da OS passada não existe."));
		if(!ordemServicoModel.getStatus().equals(StatusOS.ABERTA)) {
			throw new RuntimeException("A OS não pode ser fechada, pois não está aberta.");
		}
		ordemServicoModel.setStatus(StatusOS.FINALIZADA);
		ordemServicoModel.setFechamento(OffsetDateTime.now());
		ordemServicoRepository.save(ordemServicoModel);
	}
}

