package com.greenfieldapi.api;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.util.Arrays;

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

import io.restassured.http.ContentType;

public class PrescricaoApiTest extends ApiTest {

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

  private Medico criarMedico(String cpf, String email, String crm) {
    return Medico.builder()
      .cpf(cpf)
      .email(email)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .crm(crm)
      .estadoRegistroCrm("estadoRegistroCrm")
      .estado("ES")
      .sexo("F")
      .senha("senha")
      .build();
  }

  private Paciente criarPaciente(String cpf) {
    return Paciente.builder()
      .cpf(cpf)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .sexo("F")
      .build();
  }

  private Medicamento criarMedicamento() {
    return Medicamento.builder()
      .descricao("descricao")
      .quantidade(1)
      .dosagem("dosagem")
      .frequencia("1 vez ao dia")
      .build();
  }

  private Prescricao criarPrescricao() {
    Medico medico = medicoRepository.save(
      criarMedico("91354036085", "a@email", "001")
    );

    Paciente paciente = pacienteRepository.save(
      criarPaciente("91354036085")
    );

    Medicamento medicamento = criarMedicamento();

    return Prescricao.builder()
      .medicoId(medico.getId())
      .pacienteId(paciente.getId())
      .medicamentos(Arrays.asList(medicamento))
      .build();
  }
  
}
