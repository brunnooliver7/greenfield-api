package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenfieldapi.domain.model.Medico;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MedicoControllerTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;
    RestAssured.basePath = "/medico";
  }

  @Test
  void deveCriarMedico() throws Exception {
    given()
      .body(criarJsonMedico())
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post()
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }

  private String criarJsonMedico() {
    Medico medico = Medico.builder()
      .cpf("07978344002")
      .email("email@example.com")
      .nome("nome")
      .dtNascimento(new Date())
      .crm("crm")
      .estadoRegistroCrm("estadoRegistroCrm")
      .estado("ES")
      .sexo("F")
      .senha("senha")
      .build();

    Gson gson = new GsonBuilder()
      .setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
      .create();

    return gson.toJson(medico);
  }
}
