package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarMedicamento;
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

import com.greenfieldapi.api.controller.MedicamentoController;
import com.greenfieldapi.api.dto.MedicamentoDTO;
import com.greenfieldapi.api.mapper.MedicamentoMapper;
import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.service.MedicamentoService;

import io.restassured.http.ContentType;

@WebMvcTest(MedicamentoController.class)
public class MedicamentoControllerTest extends ControllerUnitTest {
  
  @MockBean
  private MedicamentoService medicamentoService;

  @Test
  public void deve_criar_um_medicamento() throws Exception {
    Medicamento medicamento = criarMedicamento();
    MedicamentoDTO dto = MedicamentoMapper.INSTANCE.toDTO(medicamento);

    when(medicamentoService.save(any(Medicamento.class))).thenReturn(medicamento);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/medicamento")
    .then()
      .statusCode(HttpStatus.CREATED.value());

    verify(medicamentoService, times(1)).save(any(Medicamento.class));
  }

  @Test
  public void deve_alterar_um_medicamento() {

    Medicamento medicamentoSalvo = criarMedicamento();
    medicamentoSalvo.setId(1L);
    
    Medicamento medicamentoAtualizado = new Medicamento();
    BeanUtils.copyProperties(medicamentoSalvo, medicamentoAtualizado);
    medicamentoAtualizado.setDescricao("descricao edit");

    MedicamentoDTO dto = MedicamentoMapper.INSTANCE.toDTO(medicamentoAtualizado);

    when(medicamentoService.findById(anyLong())).thenReturn(medicamentoSalvo);
    when(medicamentoService.save(any(Medicamento.class))).thenReturn(medicamentoAtualizado);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/medicamento")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("descricao", Matchers.equalTo("descricao edit"));

    verify(medicamentoService, times(1)).findById(anyLong());
    verify(medicamentoService, times(1)).save(any(Medicamento.class));
  }

  @Test
  public void deve_obter_um_medicamento() throws Exception {
    Medicamento medicamento = criarMedicamento();
    medicamento.setId(1L);

    when(medicamentoService.findById(anyLong())).thenReturn(medicamento);

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/medicamento/{id}", medicamento.getId())
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("id", Matchers.notNullValue());

    verify(medicamentoService, times(1)).findById(medicamento.getId());
  }

}
