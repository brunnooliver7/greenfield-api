package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarMedico;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.controller.MedicoController;
import com.greenfieldapi.api.dto.MedicoDTO;
import com.greenfieldapi.api.mapper.MedicoMapper;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.service.MedicoService;

import io.restassured.http.ContentType;

@WebMvcTest(MedicoController.class)
public class MedicoControllerTest extends ControllerUnitTest {

  @MockBean
  private MedicoService medicoService;

  @Test
  public void deve_criar_um_medico() throws Exception {
    Medico medico = criarMedico("91354036085", "a@email", "001");
    MedicoDTO dto = MedicoMapper.INSTANCE.toDTO(medico);

    when(medicoService.save(any(Medico.class))).thenReturn(medico);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/medico")
    .then()
      .statusCode(HttpStatus.CREATED.value())
      .body("cpf", Matchers.notNullValue());

    verify(medicoService, times(1)).save(any(Medico.class));
  }

  @Test
  public void deve_obter_um_medico() throws Exception {
    Medico medico = criarMedico("91354036085", "a@email", "001");
    medico.setId(1L);

    when(medicoService.findById(anyLong())).thenReturn(medico);

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/medico/{id}", medico.getId())
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("id", Matchers.notNullValue());

    verify(medicoService, times(1)).findById(medico.getId());
  }

}
