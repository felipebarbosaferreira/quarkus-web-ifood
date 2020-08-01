package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class RestauranteService {
	@Inject
	RestauranteRepository restauranteRepository;

	public Restaurante obter(Long id) {
		Optional<Restaurante> restaurante = restauranteRepository.obter(id);
		return restaurante.orElseThrow(() -> new NotFoundException());
	}
	
	public List<Restaurante> listarTodos() {
        return restauranteRepository.listarTodos();
    }
	
	public void adicionar(Restaurante restaurante) {
		restauranteRepository.salvar(restaurante);
	}
	
	public void atualizar(Long id, Restaurante restaurante) {
		Restaurante restauranteDB = obter(id);
		restauranteDB.nome = restaurante.nome;
	}
	
	public void deletar(Long id) {
		Restaurante restaurante = obter(id);
		restauranteRepository.deletar(restaurante);
	}
}