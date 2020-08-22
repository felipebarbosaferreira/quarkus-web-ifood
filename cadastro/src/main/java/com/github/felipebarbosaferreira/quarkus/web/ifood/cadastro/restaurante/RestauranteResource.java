package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "restaurantes")
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirements(value = {@SecurityRequirement(name = "ifood-oauth", scopes = {})})
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
    public void adicionar(RestauranteDomain restauranteDomain) {
    	restauranteService.adicionar(restauranteDomain);
    	Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, RestauranteDomain restauranteDomain) {
    	restauranteService.atualizar(id, restauranteDomain);
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
    	restauranteService.deletar(id);
    }
}