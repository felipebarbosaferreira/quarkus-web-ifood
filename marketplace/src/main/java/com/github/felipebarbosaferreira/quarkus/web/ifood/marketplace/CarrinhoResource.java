package com.github.felipebarbosaferreira.quarkus.web.ifood.marketplace;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

@Path("carrinho")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

	private static final String CLIENTE = "clienteA";
	
	@Inject
	PgPool pgPool;
	
	@Inject
	@Channel("pedidos")
	Emitter<PedidoRealizadoDTO> emitterPedidoRealizado;
	
	@GET
	public Uni<List<PratoCarrinho>> buscarCarrinho() {
		return PratoCarrinho.findCarrinho(pgPool, CLIENTE);
	}
	
	@POST
	@Path("/prato/{idPrato}")
	public Uni<Long> adicionarPrato(@PathParam("idPrato") Long idPrato) {
		PratoCarrinho pratoCarrinho = new PratoCarrinho();
		pratoCarrinho.cliente = CLIENTE;
		pratoCarrinho.prato = idPrato;
		return PratoCarrinho.save(pgPool, CLIENTE, idPrato);
	}
	
	@POST
	@Path("/realizar-pedido")
	public Uni<Boolean> realizarPedido() {
		PedidoRealizadoDTO pedido = new PedidoRealizadoDTO();
		String cliente = CLIENTE;
		pedido.cliente = cliente;
		List<PratoCarrinho> pratoCarrinho = PratoCarrinho.findCarrinho(pgPool, cliente).await().indefinitely();
		List<PratoPedidoDTO> pratos = pratoCarrinho.stream().map(pc -> from(pc)).collect(Collectors.toList());
		pedido.pratos = pratos;
		pedido.restaurante = new RestauranteDTO("nome restaurante");
		emitterPedidoRealizado.send(pedido);
		return PratoCarrinho.delete(pgPool, cliente);
	}

	private PratoPedidoDTO from(PratoCarrinho pc) {
		PratoDTO dto = Prato.findById(pgPool, pc.prato).await().indefinitely();
		return new PratoPedidoDTO(dto.nome, dto.descricao, dto.preco);
	}
}
