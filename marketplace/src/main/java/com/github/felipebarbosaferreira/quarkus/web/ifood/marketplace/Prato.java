package com.github.felipebarbosaferreira.quarkus.web.ifood.marketplace;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Prato {
    public Long id;
    
    public String nome;
    
    public String descricao;
    
    public Restaurante restaurante;
    
    public BigDecimal preco;

	public static Multi<PratoDTO> findAll(PgPool pgPool) {
		Uni<RowSet<Row>> queryResult = pgPool.preparedQuery("SELECT * FROM prato").execute();
		
		return uniToMulti(queryResult);
	}
	
	public static Multi<PratoDTO> findAll(PgPool pgPool, Long idRestaurante) {
		Uni<RowSet<Row>> queryResult = pgPool
				.preparedQuery("SELECT * FROM prato WHERE prato.restaurante_id = $1 ORDER BY nome ASC")
				.execute(Tuple.of(idRestaurante));
		
		return uniToMulti(queryResult);
	}
	
	public static Multi<PratoDTO> uniToMulti(Uni<RowSet<Row>> queryResult) {
		return queryResult.onItem()
				.produceMulti(rowSet -> Multi
						.createFrom()
						.items(() -> { 
							return StreamSupport.stream(rowSet.spliterator(), false); 
						}))
				.onItem()
				.apply(PratoDTO::from);
	}

	public static Uni<PratoDTO> findById(PgPool pgPool, Long prato) {
		return pgPool
				.preparedQuery("SELECT * FROM prato WHERE prato.id = $1 ORDER BY nome ASC")
				.execute(Tuple.of(prato))
				.map(RowSet::iterator)
		        .map(iterator -> iterator.hasNext() ? PratoDTO.from(iterator.next()) : null);
	}
}
