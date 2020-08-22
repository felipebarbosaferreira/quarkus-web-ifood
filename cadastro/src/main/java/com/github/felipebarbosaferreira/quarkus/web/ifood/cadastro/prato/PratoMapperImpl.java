package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PratoMapperImpl implements PratoMapper {

	@Override
	public PratoDomain pratoToPratoDomain(Prato prato) {
		PratoDomain pratoDomain = new PratoDomain(prato.nome, prato.descricao, prato.restaurante.id, prato.preco);
		return pratoDomain;
	}

	@Override
	public Prato pratoDomainToPrato(PratoDomain pratoDomain) {
		Prato prato = new Prato();
		prato.nome = pratoDomain.nome;
		prato.descricao = pratoDomain.descricao;
		prato.preco = pratoDomain.preco;
		return prato;
	}

}
