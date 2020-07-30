package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RestauranteRepository implements PanacheRepository<Restaurante> {

	public Optional<Restaurante> obter(Long id) {
		return findByIdOptional(id);
	}
	
	public List<Restaurante> listarTodos() {
        return listAll();
    }
	
	public void salvar(Restaurante restaurante) {
		persist(restaurante);
	}
	
	public void deletar(Restaurante restaurante) {
		delete(restaurante);
	}
}