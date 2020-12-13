package com.github.brunomeloesilva.os.api.representation;

import javax.validation.constraints.NotBlank;

public class ComentarioInput {
	
	@NotBlank
	private String comentario;

	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
