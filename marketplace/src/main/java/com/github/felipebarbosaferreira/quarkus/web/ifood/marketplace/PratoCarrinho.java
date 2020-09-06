package com.github.felipebarbosaferreira.quarkus.web.ifood.marketplace;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

public class PratoCarrinho {
    public Long prato;
    
    public String cliente;

	public static Uni<Long> save(PgPool pgPool, String cliente2, Long idPrato) {
		return pgPool
				.preparedQuery("INSERT INTO prato_cliente (cliente, prato) VALUES ($1, $2) RETURNING (cliente) ")
				.execute(Tuple.of(cliente2, idPrato))
				.map(rowSet -> rowSet.iterator().next().getLong("cliente"));
	}

	public static Uni<List<PratoCarrinho>> findCarrinho(PgPool pgPool, String cliente2) {
		return pgPool
				.preparedQuery("SELECT * FROM prato_cliente WHERE cliente = $1 ")
				.execute(Tuple.of(cliente2))
				.map(rowSet -> {
					List<PratoCarrinho> list = new ArrayList<>(rowSet.size());
					for(Row row : rowSet) {
						list.add(toPratoCarrinho(row));
					}
					return list;
				});
	}

	private static PratoCarrinho toPratoCarrinho(Row row) {
		PratoCarrinho pratoCarrinho = new PratoCarrinho();
		pratoCarrinho.cliente = row.getString("cliente");
		pratoCarrinho.prato = row.getLong("prato");
		return pratoCarrinho;
	}

	public static Uni<Boolean> delete(PgPool pgPool, String cliente2) {
		return pgPool
				.preparedQuery("DELETE FROM prato_cliente WHERE cliente = $1")
				.execute(Tuple.of(cliente2))
                .map(pgRowSet -> pgRowSet.rowCount() == 1);

	}
}
