package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarPrescricao;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.controller.PrescricaoController;
import com.greenfieldapi.api.dto.PrescricaoDTO;
import com.greenfieldapi.api.mapper.PrescricaoMapper;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.service.PrescricaoService;

import io.restassured.http.ContentType;

@WebMvcTest(PrescricaoController.class)
public class PrescricaoControllerTest extends ControllerUnitTest {
  
  @MockBean
  private PrescricaoService prescricaoService;

  @Test
  public void deve_criar_um_prescricao() throws Exception {
    Prescricao prescricao = criarPrescricao(1L, 1L);
    PrescricaoDTO dto = PrescricaoMapper.INSTANCE.toDTO(prescricao);

    when(prescricaoService.save(any(Prescricao.class))).thenReturn(prescricao);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/prescricao")
    .then()
      .statusCode(HttpStatus.CREATED.value());

    verify(prescricaoService, times(1)).save(any(Prescricao.class));
  }

  @Test
  public void deve_alterar_um_prescricao() {

    Prescricao prescricaoSalvo = criarPrescricao(1L, 1L);
    prescricaoSalvo.setId(1L);
    
    Prescricao prescricaoAtualizado = new Prescricao();
    BeanUtils.copyProperties(prescricaoSalvo, prescricaoAtualizado);
    prescricaoAtualizado.setMedicoId(2L);

    PrescricaoDTO dto = PrescricaoMapper.INSTANCE.toDTO(prescricaoAtualizado);

    when(prescricaoService.findById(anyLong())).thenReturn(prescricaoSalvo);
    when(prescricaoService.save(any(Prescricao.class))).thenReturn(prescricaoAtualizado);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/prescricao")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("medicoId", Matchers.equalTo(2));

    verify(prescricaoService, times(1)).save(any(Prescricao.class));
  }

  @Test
  public void deve_obter_todas_as_prescricoes_de_um_medico() {
    Long medicoId = 1L;
    Prescricao prescricao1 = criarPrescricao(medicoId, 1L);
    Prescricao prescricao2 = criarPrescricao(medicoId, 2L);

    when(prescricaoService.findByMedicoId(medicoId))
      .thenReturn(Arrays.asList(prescricao1,prescricao2));

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/prescricao/medico/{medicoId}", medicoId)
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("size()", Matchers.equalTo(2));

    verify(prescricaoService, times(1)).findByMedicoId(medicoId);
  }

  @Test
  public void deve_obter_todas_as_prescricoes_de_um_paciente() {
    Long pacienteId = 1L;
    Prescricao prescricao1 = criarPrescricao(1L, pacienteId);
    Prescricao prescricao2 = criarPrescricao(2L, pacienteId);

    when(prescricaoService.findByPacienteId(pacienteId))
      .thenReturn(Arrays.asList(prescricao1,prescricao2));

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/prescricao/paciente/{pacienteId}", pacienteId)
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("size()", Matchers.equalTo(2));

    verify(prescricaoService, times(1)).findByPacienteId(pacienteId);
  }

  @Test
  public void deve_obter_um_prescricao() throws Exception {
    Prescricao prescricao = criarPrescricao(1L, 1L);
    prescricao.setId(1L);

    when(prescricaoService.findById(anyLong())).thenReturn(prescricao);

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/prescricao/{id}", prescricao.getId())
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("id", Matchers.notNullValue());

    verify(prescricaoService, times(1)).findById(prescricao.getId());
  }

  @Test
  public void deve_deletar_um_prescricao() {
    given()
      .accept(ContentType.JSON)
    .when()
      .delete("/prescricao/{id}", 1L)
    .then()
      .statusCode(HttpStatus.NO_CONTENT.value());

    verify(prescricaoService, times(1)).delete(1L);
  }

}
