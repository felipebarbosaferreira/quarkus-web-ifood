package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

	public RestauranteDomain restauranteToRestauranteDomain(Restaurante restaurante);
	
	public Restaurante restauranteDomainToRestaurante(RestauranteDomain restauranteDomain);
}
