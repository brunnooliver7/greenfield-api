package com.greenfieldapi.unit;

import java.time.LocalDate;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenfieldapi.domain.model.Medico;

@ExtendWith(MockitoExtension.class)
public class UnitTest {
  
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

}
