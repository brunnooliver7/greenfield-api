package com.greenfieldapi;

import java.time.LocalDate;
import java.util.Arrays;

import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.model.Prescricao;

public class TestUtils {
  
  public static Medico criarMedico(String cpf, String email, String crm) {
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

  public static Paciente criarPaciente(String cpf) {
    return Paciente.builder()
      .cpf(cpf)
      .nome("nome")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .sexo("F")
      .build();
  }

  public static Medicamento criarMedicamento() {
    return Medicamento.builder()
      .descricao("descricao")
      .quantidade(1)
      .dosagem("dosagem")
      .frequencia("1 vez ao dia")
      .build();
  }

  public static Prescricao criarPrescricao(Long medicoId, Long pacienteId) {    
    return Prescricao.builder()
      .medicoId(medicoId)
      .pacienteId(pacienteId)
      .medicamentos(Arrays.asList(criarMedicamento()))
      .build();
  }

}
