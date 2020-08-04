package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class PostgresDBLifeCycleControlTest implements QuarkusTestResourceLifecycleManager {

	private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");

	@Override
	public Map<String, String> start() {
		POSTGRES.start();
		Map<String, String> propriedades = new HashMap<>();
		propriedades.put("quarkus.datasource.username", POSTGRES.getUsername());
		propriedades.put("quarkus.datasource.password", POSTGRES.getPassword());
		propriedades.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
		return propriedades;
	}

	@Override
	public void stop() {
		if (POSTGRES.isCreated())
			POSTGRES.start();
	}

}
