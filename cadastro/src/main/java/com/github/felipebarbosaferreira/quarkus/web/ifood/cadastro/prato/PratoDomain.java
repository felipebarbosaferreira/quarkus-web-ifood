package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import java.math.BigDecimal;

public class PratoDomain {
    
    public String nome;
    
    public String descricao;
    
    public Long restauranteId;
    
    public BigDecimal preco;

	public PratoDomain() {
		super();
	}

	public PratoDomain(String nome, String descricao, Long restauranteId, BigDecimal preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.restauranteId = restauranteId;
		this.preco = preco;
	}
}
