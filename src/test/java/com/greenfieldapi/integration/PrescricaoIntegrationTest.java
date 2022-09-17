package com.greenfieldapi.integration;

import static com.greenfieldapi.TestUtils.criarMedicamento;
import static com.greenfieldapi.TestUtils.criarMedico;
import static com.greenfieldapi.TestUtils.criarPaciente;
import static com.greenfieldapi.TestUtils.criarPrescricao;
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

public class PrescricaoIntegrationTest extends IntegrationTest {

  @Autowired
  private PrescricaoRepository prescricaoRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Test
  public void deve_criar_uma_prescricao() {
    
    Medico medico = medicoRepository.save(
      criarMedico("58896718040", "a@email", "001")
    );

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    Prescricao prescricao = criarPrescricao(medico.getId(), paciente.getId());    
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
    
    Medico medico1 = medicoRepository.save(
      criarMedico("91354036085", "a@email", "001")
    );

    Medico medico2 = medicoRepository.save(
      criarMedico("58896718040", "b@email", "002")
    );

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    Prescricao prescricao = prescricaoRepository.save(
      criarPrescricao(medico1.getId(), paciente.getId())
    );
    
    PrescricaoDTO dto = PrescricaoMapper.INSTANCE.toDTO(prescricao);
    dto.setMedicoId(medico2.getId());

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

  @Test
  public void deve_obter_uma_prescricao() {

    Medico medico = medicoRepository.save(
      criarMedico("58896718040", "a@email", "001")
    );

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    Prescricao prescricao = prescricaoRepository.save(
      criarPrescricao(medico.getId(), paciente.getId())
    );
    
    given()
      .accept(ContentType.JSON)
    .when()
      .get("/prescricao/" + prescricao.getId())
    .then()
      .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void deve_deletar_uma_prescricao() {

    Medico medico = medicoRepository.save(
      criarMedico("58896718040", "a@email", "001")
    );

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    Prescricao prescricao = prescricaoRepository.save(
      criarPrescricao(medico.getId(), paciente.getId())
    );
    
    given()
      .accept(ContentType.JSON)
    .when()
      .delete("/prescricao/" + prescricao.getId())
    .then()
      .statusCode(HttpStatus.NO_CONTENT.value());
  }

}
