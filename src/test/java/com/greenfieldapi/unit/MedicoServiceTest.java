package com.greenfieldapi.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.repository.MedicoRepository;
import com.greenfieldapi.domain.service.MedicoService;

public class MedicoServiceTest extends UnitTest {
  
  @InjectMocks
  private MedicoService medicoService;

  @Mock
  private MedicoRepository medicoRepository;

  @Test
  public void deve_criar_um_medico() {
    Medico medico = criarMedico("91354036085", "a@email", "001");

    when(medicoRepository.save(medico)).thenReturn(medico);

    medico = medicoService.save(medico);

    assertNotNull(medico);
  }

}
