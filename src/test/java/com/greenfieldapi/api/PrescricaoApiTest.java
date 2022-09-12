package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.PrescricaoDTO;
import com.greenfieldapi.api.mapper.PrescricaoMapper;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.MedicoRepository;
import com.greenfieldapi.domain.repository.PrescricaoRepository;

import io.restassured.http.ContentType;

public class PrescricaoApiTest extends ApiTest {

  @Autowired
  private PrescricaoRepository prescricaoRepository;

  @Autowired
  private MedicoRepository medicoRepository;

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

  @Test
  public void deve_alterar_uma_prescricao() {

    Prescricao prescricao = prescricaoRepository.save(criarPrescricao());
    PrescricaoDTO dto = PrescricaoMapper.INSTANCE.toDTO(prescricao);
    
    Medico outroMedico = medicoRepository.save(
      criarMedico("58896718040", "b@email", "002")
    );

    dto.setMedicoId(outroMedico.getId());

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/prescricao")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("medicoId", equalTo(dto.getMedicoId().intValue()));
  }

}
