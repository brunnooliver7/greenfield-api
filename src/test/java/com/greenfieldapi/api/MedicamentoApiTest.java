package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.MedicamentoDTO;
import com.greenfieldapi.api.mapper.MedicamentoMapper;
import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.repository.MedicamentoRepository;

import io.restassured.http.ContentType;

public class MedicamentoApiTest extends ApiTest {
  
  @Autowired
  private MedicamentoRepository medicamentoRepository;
  
  @Test
  public void deve_criar_um_medicamento() {
    
    Medicamento medicamento = criarMedicamento();
    MedicamentoDTO dto = MedicamentoMapper.INSTANCE.toDTO(medicamento);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/medicamento")
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void deve_alterar_um_medicamento() {

    Medicamento medicamento = medicamentoRepository.save(
      criarMedicamento()
    );

    MedicamentoDTO dto = MedicamentoMapper.INSTANCE.toDTO(medicamento);
    dto.setDescricao("descricao edit");

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/medicamento")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("descricao", equalTo(dto.getDescricao()));
  }

  @Test
  public void deve_obter_um_medicamento() {

    Medicamento medicamento = medicamentoRepository.save(
      criarMedicamento()
    );

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/medicamento/" + medicamento.getId().toString())
    .then()
      .statusCode(HttpStatus.OK.value());    
  }

  private Medicamento criarMedicamento() {
    return Medicamento.builder()
      .descricao("descricao")
      .quantidade(1)
      .dosagem("dosagem")
      .frequencia("1 vez ao dia")
      .build();
  }
}
