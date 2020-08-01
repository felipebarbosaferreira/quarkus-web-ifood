package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class LocalizacaoRepository implements PanacheRepository<Localizacao> {

	public Optional<Localizacao> obter(Long id) {
		return findByIdOptional(id);
	}
	
	public List<Localizacao> listarTodos() {
        return listAll();
    }
	
	public void salvar(Localizacao localizacao) {
		persist(localizacao);
	}
	
	public void deletar(Localizacao localizacao) {
		delete(localizacao);
	}
}
