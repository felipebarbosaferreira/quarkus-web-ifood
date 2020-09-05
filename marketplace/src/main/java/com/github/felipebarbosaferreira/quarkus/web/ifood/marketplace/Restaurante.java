package com.github.felipebarbosaferreira.quarkus.web.ifood.marketplace;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurante {

	public Long id;
	
	public String nome;
	
	public Localizacao localizacao;

	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + "]";
	}

	public void persist(PgPool pgPool) {
		pgPool
		.preparedQuery("INSERT INTO localizacao (id, latitude, longitude) VALUES ($1, $2, $3)")
		.execute(Tuple.of(localizacao.id, localizacao.latitude, localizacao.longitude))
		.await()
		.indefinitely();

		pgPool
		.preparedQuery("INSERT INTO restaurante (id, nome, localizacao_id) VALUES ($1, $2, $3)")
		.execute(Tuple.of(id, nome, localizacao.id))
		.await()
		.indefinitely();
	}
}
