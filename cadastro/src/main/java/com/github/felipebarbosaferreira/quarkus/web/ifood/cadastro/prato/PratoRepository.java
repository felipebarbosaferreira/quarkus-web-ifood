package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PratoRepository implements PanacheRepository<Prato> {

	public Optional<Prato> obter(Long id) {
		return findByIdOptional(id);
	}
	
	public List<Prato> listarTodos() {
        return listAll();
    }
	
	public List<Prato> listarTodos(Long idRestaurante) {
        return list("restaurante.id", idRestaurante);
    }
	
	public void salvar(Prato prato) {
		persist(prato);
	}
	
	public void deletar(Prato prato) {
		delete(prato);
	}
}
