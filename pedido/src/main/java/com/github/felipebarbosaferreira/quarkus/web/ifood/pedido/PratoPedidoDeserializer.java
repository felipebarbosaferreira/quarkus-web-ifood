package com.github.felipebarbosaferreira.quarkus.web.ifood.pedido;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PratoPedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {

	public PratoPedidoDeserializer() {
		super(PedidoRealizadoDTO.class);
	}

}
