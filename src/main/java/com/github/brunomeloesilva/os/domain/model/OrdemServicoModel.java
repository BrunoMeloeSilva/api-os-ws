package com.github.brunomeloesilva.os.domain.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "os")
public class OrdemServicoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;
	@ManyToOne
	@JoinColumn(name = "fkcliente")
	private ClienteModel cliente;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private StatusOS status;
	private OffsetDateTime abertura;
	private OffsetDateTime fechamento;
	@OneToMany(mappedBy = "fkos")
	private List<ComentarioModel> comentarios;
	
	
	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusOS getStatus() {
		return status;
	}

	public void setStatus(StatusOS status) {
		this.status = status;
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

	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		OrdemServicoModel other = (OrdemServicoModel) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

}
