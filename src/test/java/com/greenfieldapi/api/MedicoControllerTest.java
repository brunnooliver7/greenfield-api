package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.domain.model.Medico;

import io.restassured.http.ContentType;

public class MedicoControllerTest extends ApiTest {

  @Test
  void deveCriarMedico() throws Exception {
    given()
      .body(criarJson(criarMedico()))
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/medico")
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }

  private Medico criarMedico() {
    return Medico.builder()
      .cpf("07978344002")
      .email("email@example.com")
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .crm("crm")
      .estadoRegistroCrm("estadoRegistroCrm")
      .estado("ES")
      .sexo("F")
      .senha("senha")
      .build();
  }
}
