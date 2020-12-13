package com.github.brunomeloesilva.os.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "comentario")
public class ComentarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;
	@ManyToOne
	@JoinColumn(name = "fkos")
	@JsonIgnore //Para não gerar o looping infinito.
	private OrdemServicoModel fkos;
	@JsonProperty("comentario")
	private String descricao;
	private OffsetDateTime criado;
	
	
	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public OrdemServicoModel getFkos() {
		return fkos;
	}
	public void setFkos(OrdemServicoModel fkos) {
		this.fkos = fkos;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public OffsetDateTime getCriado() {
		return criado;
	}
	public void setCriado(OffsetDateTime criado) {
		this.criado = criado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkos == null) ? 0 : fkos.hashCode());
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
		ComentarioModel other = (ComentarioModel) obj;
		if (fkos == null) {
			if (other.fkos != null)
				return false;
		} else if (!fkos.equals(other.fkos))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	
}
