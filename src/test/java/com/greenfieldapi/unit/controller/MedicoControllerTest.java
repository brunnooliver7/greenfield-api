package com.greenfieldapi.unit.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
    Medico medico = Medico.builder()
      .cpf("91354036085")
      .email("a@email")
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .crm("001")
      .estadoRegistroCrm("estadoRegistroCrm")
      .estado("ES")
      .sexo("F")
      .senha("senha")
      .build();
    
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

}
