package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.MedicamentoDTO;
import com.greenfieldapi.api.mapper.MedicamentoMapper;
import com.greenfieldapi.domain.model.Medicamento;

import io.restassured.http.ContentType;

public class MedicamentoApiTest extends ApiTest {
  
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

  private Medicamento criarMedicamento() {
    return Medicamento.builder()
      .descricao("descricao")
      .quantidade(1)
      .dosagem("dosagem")
      .frequencia("1 vez ao dia")
      .build();
  }
}
