package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.PrescricaoDTO;
import com.greenfieldapi.api.mapper.PrescricaoMapper;
import com.greenfieldapi.domain.model.Prescricao;

import io.restassured.http.ContentType;

public class PrescricaoApiTest extends ApiTest {

  @Test
  public void deve_criar_uma_prescricao() {
    
    Prescricao prescricao = criarPrescricao();
    PrescricaoDTO dto = PrescricaoMapper.INSTANCE.toDTO(prescricao);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/prescricao")
    .then()
      .statusCode(HttpStatus.CREATED.value());
  }

}
