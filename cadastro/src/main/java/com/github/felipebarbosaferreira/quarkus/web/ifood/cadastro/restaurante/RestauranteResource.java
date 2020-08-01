package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

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

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "restaurantes")
public class RestauranteResource {
    @Inject
    RestauranteService restauranteService;

    @GET
    public List<Restaurante> listarTodos() {
        return restauranteService.listarTodos();
    }
    
    @GET
    @Path("{id}")
    public Restaurante obter(@PathParam("id") Long id) {
    	return restauranteService.obter(id);
    }
    
    @POST
    @Transactional
    public void adicionar(Restaurante restaurante) {
    	restauranteService.adicionar(restaurante);
    	Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, Restaurante restaurante) {
    	restauranteService.atualizar(id, restaurante);
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
    	restauranteService.deletar(id);
    }
}