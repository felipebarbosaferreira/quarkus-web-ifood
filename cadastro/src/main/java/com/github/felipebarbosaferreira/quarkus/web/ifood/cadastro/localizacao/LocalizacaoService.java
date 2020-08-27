package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.opentracing.Traced;

@ApplicationScoped
@Traced
public class LocalizacaoService {
	@Inject
	LocalizacaoRepository localizacaoRepository;
	
	public Localizacao obter(Long id) {
		Optional<Localizacao> localizacao = localizacaoRepository.obter(id);
		return localizacao.orElseThrow(() -> new NotFoundException());
	}
	
	public List<Localizacao> listarTodos() {
		return localizacaoRepository.listarTodos();
	}
	
	public void adicionar(Localizacao localizacao) {
		localizacaoRepository.salvar(localizacao);
	}
	
	public void atualizar(Long id, Localizacao localizacao) {
		Localizacao localizacaoDB = obter(id);
		localizacaoDB.latitude = localizacao.latitude;
		localizacaoDB.longitude = localizacao.longitude;
	}
	
	public void deletar(Long id) {
		Localizacao localizacao = obter(id);
		localizacaoRepository.deletar(localizacao);
	}
}
