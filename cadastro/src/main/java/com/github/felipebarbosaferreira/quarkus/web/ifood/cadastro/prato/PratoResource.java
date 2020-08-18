package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

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

@Path("/pratos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "pratos")
public class PratoResource {
	@Inject
	PratoService pratoService;
	
	@GET
	public List<Prato> listarTodos() {
		return pratoService.listarTodos();
	}
	
	@GET
	@Path("restaurante/{idrestaurante}")
	public List<Prato> listarTodos(@PathParam("idrestaurante") Long idRestaurante) {
		return pratoService.listarTodos(idRestaurante);
	}
	
	@GET
	@Path("{id}")
	public Prato obter(@PathParam("id") Long id) {
		return pratoService.obter(id);
	}
	
	@POST
	@Transactional
	public void adicionar(PratoDomain pratoDomain) {
		pratoService.adicionar(pratoDomain);
		Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void atualizar(@PathParam("id") Long id, PratoDomain pratoDomain) {
		pratoService.atualizar(id, pratoDomain);
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void deletar(@PathParam("id") Long id) {
		pratoService.deletar(id);
	}
}
