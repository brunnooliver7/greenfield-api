package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarPrescricao;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}
