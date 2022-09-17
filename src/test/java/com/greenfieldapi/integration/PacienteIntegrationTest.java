package com.greenfieldapi.integration;

import static com.greenfieldapi.TestUtils.criarPaciente;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.PacienteDTO;
import com.greenfieldapi.api.mapper.PacienteMapper;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.repository.PacienteRepository;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PacienteIntegrationTest extends IntegrationTest {
  
  @Autowired
  private PacienteRepository pacienteRepository;

  @Test
  public void deve_criar_um_paciente() {
    
    Paciente paciente = criarPaciente("91354036085");
    PacienteDTO dto = PacienteMapper.INSTANCE.toDTO(paciente);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/paciente")
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void deve_alterar_um_paciente() {

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    PacienteDTO dto = PacienteMapper.INSTANCE.toDTO(paciente);
    dto.setNome("nome edit");

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/paciente")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("nome", equalTo(dto.getNome()));
  }

  @Test
  public void deve_obter_todos_os_pacientes() {

    pacienteRepository.save(criarPaciente("91354036085"));
    pacienteRepository.save(criarPaciente("58896718040"));
    pacienteRepository.save(criarPaciente("44981367058"));

    Response response = 
      given()
        .accept(ContentType.JSON)
      .when()
        .get("/paciente")
      .then()
        .extract()
        .response();

    assertEquals(HttpStatus.OK.value(), response.statusCode());
    
    int size = response.jsonPath().getList("id").size();
    assertEquals(3, size);
  }

  @Test
  public void deve_obter_um_paciente() {

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/paciente/" + paciente.getId().toString())
    .then()
      .statusCode(HttpStatus.OK.value());    
  }

  @Test
  public void deve_deletar_um_paciente() {

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    given()
      .accept(ContentType.JSON)
    .when()
      .delete("/paciente/" + paciente.getId().toString())
    .then()
      .statusCode(HttpStatus.NO_CONTENT.value());
  }

}
