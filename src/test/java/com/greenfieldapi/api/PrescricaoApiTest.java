package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.greenfieldapi.api.dto.PrescricaoDTO;
import com.greenfieldapi.api.mapper.PrescricaoMapper;
import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.MedicoRepository;
import com.greenfieldapi.domain.repository.PacienteRepository;
import com.greenfieldapi.domain.repository.PrescricaoRepository;

import io.restassured.http.ContentType;

public class PrescricaoApiTest extends ApiTest {

  @Autowired
  private PrescricaoRepository prescricaoRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

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

  @Test
  public void deve_obter_todas_as_prescricoes_de_um_medico() {
    Medico medico = criarMedico("58896718040", "a@email", "001");
    medico = medicoRepository.save(medico);

    Paciente paciente = criarPaciente("44981367058");
    paciente = pacienteRepository.save(paciente);

    Medicamento medicamento1 = criarMedicamento();

    Medicamento medicamento2 = criarMedicamento();

    Prescricao prescricao1 = Prescricao.builder()
      .medicoId(medico.getId())
      .pacienteId(paciente.getId())
      .medicamentos(Arrays.asList(medicamento1))
      .build();

    Prescricao prescricao2 = Prescricao.builder()
      .medicoId(medico.getId())
      .pacienteId(paciente.getId())
      .medicamentos(Arrays.asList(medicamento2))
      .build();

    prescricaoRepository.save(prescricao1);
    prescricaoRepository.save(prescricao2);

    given()
      .accept(ContentType.JSON)
    .when()
      .get("prescricao/medico/" + medico.getId())
    .then()
      .body("", Matchers.hasSize(2))
      .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deve_obter_todas_as_prescricoes_de_um_paciente() {
    Medico medico = criarMedico("58896718040", "a@email", "001");
    medico = medicoRepository.save(medico);

    Paciente paciente = criarPaciente("44981367058");
    paciente = pacienteRepository.save(paciente);

    Medicamento medicamento1 = criarMedicamento();

    Medicamento medicamento2 = criarMedicamento();

    Prescricao prescricao1 = Prescricao.builder()
      .medicoId(medico.getId())
      .pacienteId(paciente.getId())
      .medicamentos(Arrays.asList(medicamento1))
      .build();

    Prescricao prescricao2 = Prescricao.builder()
      .medicoId(medico.getId())
      .pacienteId(paciente.getId())
      .medicamentos(Arrays.asList(medicamento2))
      .build();

    prescricaoRepository.save(prescricao1);
    prescricaoRepository.save(prescricao2);

    given()
      .accept(ContentType.JSON)
    .when()
      .get("prescricao/paciente/" + paciente.getId())
    .then()
      .body("", Matchers.hasSize(2))
      .statusCode(HttpStatus.OK.value());
  }
}
