package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante.RestauranteService;

@ApplicationScoped
public class PratoService {
	@Inject
	PratoRepository pratoRepository;
	
	@Inject
	PratoMapper pratoMapper;
	
	@Inject
	RestauranteService restauranteService;
	
	public Prato obter(Long id) {
		Optional<Prato> prato = pratoRepository.obter(id);
		return prato.orElseThrow(() -> new NotFoundException());
	}
	
	public List<Prato> listarTodos() {
		return pratoRepository.listarTodos();
	}
	
	public List<Prato> listarTodos(Long idRestaurante) {
		return pratoRepository.listarTodos(idRestaurante);
	}
	
	public void adicionar(PratoDomain pratoDomain) {
		Prato prato = pratoMapper.pratoDomainToPrato(pratoDomain);
		prato.restaurante = restauranteService.obter(pratoDomain.restauranteId);
		pratoRepository.salvar(prato);
	}
	
	public void atualizar(Long id, PratoDomain pratoDomain) {
		Prato pratoDB = obter(id);
		pratoDB.descricao = pratoDomain.descricao;
		pratoDB.nome = pratoDomain.nome;
		pratoDB.preco = pratoDomain.preco;
	}
	
	public void deletar(Long id) {
		Prato prato = obter(id);
		pratoRepository.deletar(prato);
	}
}
