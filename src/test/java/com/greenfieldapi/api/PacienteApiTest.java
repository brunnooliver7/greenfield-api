package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.PacienteDTO;
import com.greenfieldapi.api.mapper.PacienteMapper;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.repository.PacienteRepository;

import io.restassured.http.ContentType;

public class PacienteApiTest extends ApiTest {
  
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

  private Paciente criarPaciente(String cpf) {
    return Paciente.builder()
      .cpf(cpf)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .sexo("F")
      .build();
  }

}
