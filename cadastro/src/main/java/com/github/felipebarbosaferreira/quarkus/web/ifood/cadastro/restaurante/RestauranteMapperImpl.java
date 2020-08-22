package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import javax.enterprise.context.ApplicationScoped;

import com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao.LocalizacaoDomain;

@ApplicationScoped
public class RestauranteMapperImpl implements RestauranteMapper {

	@Override
	public RestauranteDomain restauranteToRestauranteDomain(Restaurante restaurante) {
		return new RestauranteDomain(restaurante.proprietario, restaurante.cnpj, restaurante.nome, new LocalizacaoDomain(restaurante.localizacao.latitude, restaurante.localizacao.longitude));
	}

	@Override
	public Restaurante restauranteDomainToRestaurante(RestauranteDomain restauranteDomain) {
		Restaurante restaurante = new Restaurante();
		restaurante.proprietario = restauranteDomain.proprietario;
		restaurante.cnpj = restauranteDomain.cnpj;
		restaurante.nome = restauranteDomain.nome;
		return restaurante;
	}

}
