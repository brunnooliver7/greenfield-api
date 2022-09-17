package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarPaciente;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.controller.PacienteController;
import com.greenfieldapi.api.dto.PacienteDTO;
import com.greenfieldapi.api.mapper.PacienteMapper;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.service.PacienteService;

import io.restassured.http.ContentType;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest extends ControllerUnitTest {
  
  @MockBean
  private PacienteService pacienteService;

  @Test
  public void deve_criar_um_paciente() throws Exception {
    Paciente paciente = criarPaciente("91354036085");
    PacienteDTO dto = PacienteMapper.INSTANCE.toDTO(paciente);

    when(pacienteService.save(any(Paciente.class))).thenReturn(paciente);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .post("/paciente")
    .then()
      .statusCode(HttpStatus.CREATED.value())
      .body("cpf", Matchers.notNullValue());

    verify(pacienteService, times(1)).save(any(Paciente.class));
  }

}
