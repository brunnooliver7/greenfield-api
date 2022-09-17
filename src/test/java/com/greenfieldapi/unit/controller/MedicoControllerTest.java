package com.greenfieldapi.unit.controller;

import static com.greenfieldapi.TestUtils.criarMedico;
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
  public void deve_alterar_um_medico() {

    Medico medicoSalvo = criarMedico("91354036085", "a@email", "001");
    medicoSalvo.setId(1L);
    
    Medico medicoAtualizado = new Medico();;
    BeanUtils.copyProperties(medicoSalvo, medicoAtualizado);
    medicoAtualizado.setCrm("002");

    MedicoDTO dto = MedicoMapper.INSTANCE.toDTO(medicoAtualizado);

    when(medicoService.findById(anyLong())).thenReturn(medicoSalvo);
    when(medicoService.save(any(Medico.class))).thenReturn(medicoAtualizado);

    given()
      .body(dto)
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
    .when()
      .put("/medico")
    .then()
      .statusCode(HttpStatus.OK.value())
      .body("crm", Matchers.equalTo("002"));

    verify(medicoService, times(1)).findById(anyLong());
    verify(medicoService, times(1)).save(any(Medico.class));
  }

  @Test
  public void deve_obter_todos_os_medicos() {

    Medico medico1 = criarMedico("91354036085", "a@email", "001");    
    Medico medico2 = criarMedico("58896718040", "b@email", "002");

    when(medicoService.findAll()).thenReturn(Arrays.asList(medico1, medico2));

    given()
      .accept(ContentType.JSON)
    .when()
      .get("/medico")
    .then()
      .statusCode(HttpStatus.OK.value())
      .assertThat().body("size()", Matchers.is(2));

    verify(medicoService, times(1)).findAll();
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

  @Test
  public void deve_deletar_um_medico() {
    given()
      .accept(ContentType.JSON)
    .when()
      .delete("/medico/{id}", 1L)
    .then()
      .statusCode(HttpStatus.NO_CONTENT.value());

    verify(medicoService, times(1)).delete(1L);
  }
}
