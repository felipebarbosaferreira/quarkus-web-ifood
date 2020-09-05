package com.github.felipebarbosaferreira.quarkus.web.ifood.marketplace;

import java.math.BigDecimal;

import io.vertx.mutiny.sqlclient.Row;

public class PratoDTO {

    public Long id;
    
    public String nome;
    
    public String descricao;
    
    public BigDecimal preco;
    
    public PratoDTO() {
		super();
	}

	public PratoDTO(Long id, String nome, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	public static PratoDTO from(Row row) {
		PratoDTO pratoDTO = new PratoDTO(
    			row.getLong("id"),
    			row.getString("nome"),
    			row.getString("descricao"),
    			row.getBigDecimal("preco")
    			);
    	return pratoDTO;
    }
}
