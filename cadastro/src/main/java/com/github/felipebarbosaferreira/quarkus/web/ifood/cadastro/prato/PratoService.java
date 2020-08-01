package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class PratoService {
	@Inject
	PratoRepository pratoRepository;
	
	public Prato obter(Long id) {
		Optional<Prato> prato = pratoRepository.obter(id);
		return prato.orElseThrow(() -> new NotFoundException());
	}
	
	public List<Prato> listarTodos() {
		return pratoRepository.listarTodos();
	}
	
	public void adicionar(Prato prato) {
		pratoRepository.salvar(prato);
	}
	
	public void atualizar(Long id, Prato prato) {
		Prato pratoDB = obter(id);
		pratoDB.descricao = prato.descricao;
		pratoDB.nome = prato.nome;
		pratoDB.preco = prato.preco;
	}
	
	public void deletar(Long id) {
		Prato prato = obter(id);
		pratoRepository.deletar(prato);
	}
}
