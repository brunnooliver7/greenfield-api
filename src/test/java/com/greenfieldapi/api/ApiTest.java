package com.greenfieldapi.api;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.MedicoRepository;
import com.greenfieldapi.domain.repository.PacienteRepository;

import io.restassured.RestAssured;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase(refresh = RefreshMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@Ignore
public class ApiTest {

  @LocalServerPort
  private int port;

  @Before
  public void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;
  }

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  protected Medico criarMedico(String cpf, String email, String crm) {
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

  protected Paciente criarPaciente(String cpf) {
    return Paciente.builder()
      .cpf(cpf)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .sexo("F")
      .build();
  }

  protected Medicamento criarMedicamento() {
    return Medicamento.builder()
      .descricao("descricao")
      .quantidade(1)
      .dosagem("dosagem")
      .frequencia("1 vez ao dia")
      .build();
  }

  protected Prescricao criarPrescricao() {
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
