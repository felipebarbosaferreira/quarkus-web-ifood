package com.github.felipebarbosaferreira.quarkus.web.ifood.pedido;

public class RestauranteDTO {

	public String nome;

	public RestauranteDTO() {
		super();
	}

	public RestauranteDTO(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "RestauranteDTO [nome=" + nome + "]";
	}
	
	
}
