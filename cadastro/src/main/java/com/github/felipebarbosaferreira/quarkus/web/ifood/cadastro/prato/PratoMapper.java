package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

	public PratoDomain pratoToPratoDomain(Prato prato);
	
	public Prato pratoDomainToPrato(PratoDomain pratoDomain);
}
