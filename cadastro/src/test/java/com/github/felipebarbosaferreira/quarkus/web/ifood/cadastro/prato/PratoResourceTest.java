package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.prato;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

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
public class PratoResourceTest {

	@Test
	@DataSet("test-prato-listar-todos-1.yml")
	public void testListarTodosEndpoint() {
		String retorno = given().when().get("/pratos").then().statusCode(200).extract().asString();
		Approvals.verifyJson(retorno);
	}

	@Test
	@DataSet("test-prato-listar-todos-1.yml")
	public void testListarTodosPorRestauranteEndpoint() {
		// TODO - yml com objetos restaurantes
		String retorno = given().when().get("/pratos/restaurante/8").then().statusCode(200).extract().asString();
		Approvals.verifyJson(retorno);
	}

	@Test
	@DataSet("test-prato-listar-todos-1.yml")
	public void testObterEndpoint() {
		String retorno = given().when().get("/pratos/7").then().statusCode(200).extract().asString();
		Approvals.verifyJson(retorno);
	}

	@Test
	public void testAdicionarEndpoint() {
		PratoDomain pratoDomainMock = getMockSavePratoTest();
		given().body(pratoDomainMock).contentType(MediaType.APPLICATION_JSON).when().post("/pratos").then()
				.statusCode(204);
		given().when().get("/pratos/1").then().statusCode(200).body("nome", equalTo(pratoDomainMock.nome));
	}

	@Test
	@DataSet("test-prato-listar-todos-1.yml")
	public void testAtualizarEndpoint() {
		PratoDomain pratoDomainMock = getMockSavePratoTest();
		given().body(pratoDomainMock).contentType(MediaType.APPLICATION_JSON).when().put("/pratos/7").then()
				.statusCode(204);
		given().when().get("/pratos/7").then().statusCode(200).body("nome", equalTo(pratoDomainMock.nome));
	}

	@Test
	public void testDeletarEndpoint() {
		given().contentType(MediaType.APPLICATION_JSON).when().delete("/pratos/8").then().statusCode(204);
		given().when().get("/pratos/8").then().statusCode(404);
	}

	private PratoDomain getMockSavePratoTest() {
		return new PratoDomain("Peixada Cearense", 
				"Filé de peixe, tomate, cebola, pimentões, leite de coco, batatas, cenouras, repolho e ovos cozidos. Acompanha pirão e arroz branco.", 
				1L, 
				new BigDecimal(24.12));
	}

}