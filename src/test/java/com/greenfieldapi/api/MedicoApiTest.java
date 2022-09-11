package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.MedicoDTO;
import com.greenfieldapi.api.mapper.MedicoMapper;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.repository.MedicoRepository;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MedicoApiTest extends ApiTest {

  @Autowired
  private MedicoRepository medicoRepository;
  
  @Test
  public void deve_criar_um_medico() {

    Medico medico = criarMedico("91354036085", "a@email.com", "001");
    MedicoDTO dto = MedicoMapper.INSTANCE.toDTO(medico);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/medico")
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void deve_alterar_um_medico() {

    Medico medico = medicoRepository.save(
      criarMedico("91354036085", "a@email", "001")
    );

    MedicoDTO dto = MedicoMapper.INSTANCE.toDTO(medico);
    dto.setCrm("002");

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/medico")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("crm", equalTo(dto.getCrm()));
  }

  @Test
  public void deve_obter_todos_os_medicos() {

    medicoRepository.save(criarMedico("91354036085", "a@email", "001"));
    medicoRepository.save(criarMedico("58896718040", "b@email", "002"));
    medicoRepository.save(criarMedico("44981367058", "c@email", "003"));

    Response response = 
      given()
        .accept(ContentType.JSON)
      .when()
        .get("/medico")
      .then()
        .extract()
        .response();

    assertEquals(HttpStatus.OK.value(), response.statusCode());
    
    int size = response.jsonPath().getList("id").size();
    assertEquals(3, size);
  }

  @Test
  public void deve_obter_um_medico() {

    Medico medico = medicoRepository.save(
      criarMedico("91354036085", "a@email", "001")
    );

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/medico/" + medico.getId().toString())
    .then()
      .statusCode(HttpStatus.OK.value());    
  }

  @Test
  public void deve_deletar_um_medico() {

    Medico medico = medicoRepository.save(
      criarMedico("91354036085", "a@email", "001")
    );

    given()
      .accept(ContentType.JSON)
    .when()
      .delete("/medico/" + medico.getId().toString())
    .then()
      .statusCode(HttpStatus.NO_CONTENT.value());
  }

  private Medico criarMedico(String cpf, String email, String crm) {
    return Medico.builder()
      .cpf(cpf)
      .email(email)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .crm(crm)
      .estadoRegistroCrm("estadoRegistroCrm")
      .estado("ES")
      .sexo("F")
      .senha("senha")
      .build();
  }

}
