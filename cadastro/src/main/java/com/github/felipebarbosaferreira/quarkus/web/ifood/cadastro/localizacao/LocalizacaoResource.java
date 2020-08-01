package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.localizacao;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/localizacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocalizacaoResource {
	@Inject
	LocalizacaoService localizacaoService;

    @GET
    public List<Localizacao> listarTodos() {
        return localizacaoService.listarTodos();
    }
    
    @GET
    @Path("{id}")
    public Localizacao obter(@PathParam("id") Long id) {
    	return localizacaoService.obter(id);
    }
    
    @POST
    @Transactional
    public void adicionar(Localizacao restaurante) {
    	localizacaoService.adicionar(restaurante);
    	Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, Localizacao restaurante) {
    	localizacaoService.atualizar(id, restaurante);
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
    	localizacaoService.deletar(id);
    }
}
