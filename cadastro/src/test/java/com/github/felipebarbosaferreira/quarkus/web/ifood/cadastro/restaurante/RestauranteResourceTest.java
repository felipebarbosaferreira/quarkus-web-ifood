package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.restaurante;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;

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
		String retorno = given().when().get("/restaurantes/6").then().statusCode(200).extract().asString();
		Approvals.verifyJson(retorno);
	}

	@Test
	public void testAdicionarEndpoint() {
		RestauranteDomain restauranteDomainMock = getMockSaveRestauranteTest();
		given().body(restauranteDomainMock).contentType(MediaType.APPLICATION_JSON).when().post("/restaurantes").then()
				.statusCode(204);
		given().when().get("/restaurantes/1").then().statusCode(200).body("nome", equalTo(restauranteDomainMock.nome));
	}

	@Test
	@DataSet("test-restaurante-listar-todos-1.yml")
	public void testAtualizarEndpoint() {
		RestauranteDomain restauranteDomainMock = getMockSaveRestauranteTest();
		given().body(restauranteDomainMock).contentType(MediaType.APPLICATION_JSON).when().put("/restaurantes/7").then()
				.statusCode(204);
		given().when().get("/restaurantes/7").then().statusCode(200).body("nome", equalTo(restauranteDomainMock.nome));
	}

	@Test
	public void testDeletarEndpoint() {
		given().contentType(MediaType.APPLICATION_JSON).when().delete("/restaurantes/6").then().statusCode(204);
		given().when().get("/restaurantes/6").then().statusCode(404);
	}

	private RestauranteDomain getMockSaveRestauranteTest() {
		return new RestauranteDomain("Felipe Barbosa", "001002003", "Res FelipB", null);
	}

}