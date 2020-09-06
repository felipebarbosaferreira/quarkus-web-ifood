package com.github.felipebarbosaferreira.quarkus.web.ifood.pedido;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PedidoRealizadoIncoming {

	@Incoming("pedidos")
	public void lerPedidos(PedidoRealizadoDTO dto) {
		System.out.println("------------------------");
		System.out.println(dto);
		System.out.println("------------------------");
		
		Pedido pedido = new Pedido();
		pedido.cliente = dto.cliente;
		pedido.pratos = new ArrayList<>();
		dto.pratos.forEach(prato -> pedido.pratos.add(from(prato)));
		Restaurante restaurante = new Restaurante();
		restaurante.nome = dto.restaurante.nome;
		pedido.persist();
	}

	private Prato from(PratoPedidoDTO pratoPedidoDTO) {
		Prato prato = new Prato();
		prato.descricao = pratoPedidoDTO.descricao;
		prato.nome = pratoPedidoDTO.nome;
		prato.preco = new Decimal128(pratoPedidoDTO.preco);
		return prato;
	}
}
