package com.greenfieldapi.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.repository.MedicoRepository;
import com.greenfieldapi.domain.service.MedicoService;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {
  
  @InjectMocks
  private MedicoService medicoService;

  @Mock
  private MedicoRepository medicoRepository;

  @Test
  public void deve_criar_um_medico() {
    Medico medico =  Medico.builder()
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

    when(medicoRepository.save(medico)).thenReturn(medico);

    medico = medicoService.save(medico);

    assertNotNull(medico);
  }

}
