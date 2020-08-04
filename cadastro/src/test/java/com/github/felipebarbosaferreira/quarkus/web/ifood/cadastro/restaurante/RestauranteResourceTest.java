package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import static io.restassured.RestAssured.given;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.PostgresDBLifeCycleControlTest;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(PostgresDBLifeCycleControlTest.class)
public class RestauranteResourceTest {

	@Test
	@DataSet("test-restaurante-listar-todos-1.yml")
	public void testListarTodosEndpoint() {
		String retorno = given().when().get("/restaurantes").then().statusCode(200).extract().asString();
		Approvals.verifyJson(retorno);
	}

	@Test
	@DataSet("test-restaurante-listar-todos-1.yml")
	public void testObterEndpoint() {
		String retorno = given().when().get("/restaurantes/2").then().statusCode(200).extract().asString();
		Approvals.verifyJson(retorno);
	}

}