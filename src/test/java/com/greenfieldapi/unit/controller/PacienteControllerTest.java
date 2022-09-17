package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarPaciente;
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

  @Test
  public void deve_alterar_um_paciente() {

    Paciente pacienteSalvo = criarPaciente("91354036085");
    pacienteSalvo.setId(1L);
    
    Paciente pacienteAtualizado = new Paciente();
    BeanUtils.copyProperties(pacienteSalvo, pacienteAtualizado);
    pacienteAtualizado.setNome("novo nome");

    PacienteDTO dto = PacienteMapper.INSTANCE.toDTO(pacienteAtualizado);

    when(pacienteService.findById(anyLong())).thenReturn(pacienteSalvo);
    when(pacienteService.save(any(Paciente.class))).thenReturn(pacienteAtualizado);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/paciente")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("nome", Matchers.equalTo("novo nome"));

    verify(pacienteService, times(1)).findById(anyLong());
    verify(pacienteService, times(1)).save(any(Paciente.class));
  }

  @Test
  public void deve_obter_todos_os_pacientes() {

    Paciente paciente1 = criarPaciente("91354036085");
    Paciente paciente2 = criarPaciente("58896718040");

    when(pacienteService.findAll()).thenReturn(Arrays.asList(paciente1, paciente2));

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/paciente")
    .then()
      .statusCode(HttpStatus.OK.value())
      .assertThat().body("size()", Matchers.is(2));

    verify(pacienteService, times(1)).findAll();
  }

  @Test
  public void deve_obter_um_paciente() throws Exception {
    Paciente paciente = criarPaciente("91354036085");
    paciente.setId(1L);

    when(pacienteService.findById(anyLong())).thenReturn(paciente);

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/paciente/{id}", paciente.getId())
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("id", Matchers.notNullValue());

    verify(pacienteService, times(1)).findById(paciente.getId());
  }

}
