package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao.LocalizacaoDomain;


public class RestauranteDomain {
    
    public String proprietario;
    
    public String cnpj;
    
    public String nome;
    
    public LocalizacaoDomain localizacao;

	public RestauranteDomain() {
		super();
	}

	public RestauranteDomain(String proprietario, String cnpj, String nome, LocalizacaoDomain localizacao) {
		super();
		this.proprietario = proprietario;
		this.cnpj = cnpj;
		this.nome = nome;
		this.localizacao = localizacao;
	}
}
