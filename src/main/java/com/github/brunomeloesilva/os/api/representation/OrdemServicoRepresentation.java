package com.github.brunomeloesilva.os.api.representation;

import java.time.OffsetDateTime;

import com.github.brunomeloesilva.os.domain.model.StatusOS;

public class OrdemServicoRepresentation {
	
	private Long id;
	private ClienteRepresentation cliente;
	private String descricao;
	private StatusOS statusOS;
	private OffsetDateTime abertura;
	private OffsetDateTime fechamento;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClienteRepresentation getCliente() {
		return cliente;
	}
	public void setCliente(ClienteRepresentation cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusOS getStatusOS() {
		return statusOS;
	}
	public void setStatusOS(StatusOS statusOS) {
		this.statusOS = statusOS;
	}
	public OffsetDateTime getAbertura() {
		return abertura;
	}
	public void setAbertura(OffsetDateTime abertura) {
		this.abertura = abertura;
	}
	public OffsetDateTime getFechamento() {
		return fechamento;
	}
	public void setFechamento(OffsetDateTime fechamento) {
		this.fechamento = fechamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServicoRepresentation other = (OrdemServicoRepresentation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
