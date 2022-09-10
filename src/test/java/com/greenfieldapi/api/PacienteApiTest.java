package com.greenfieldapi.api;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

import com.greenfieldapi.api.dto.PacienteDTO;
import com.greenfieldapi.api.mapper.PacienteMapper;
import com.greenfieldapi.domain.model.Paciente;

import io.restassured.http.ContentType;

public class PacienteApiTest extends ApiTest {
  
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

  private Paciente criarPaciente(String cpf) {
    return Paciente.builder()
      .cpf(cpf)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .sexo("F")
      .build();
  }

}
