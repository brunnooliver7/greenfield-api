package com.greenfieldapi.unit.service;

import static com.greenfieldapi.TestUtils.criarPaciente;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.repository.PacienteRepository;
import com.greenfieldapi.domain.service.PacienteService;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
  
  @InjectMocks
  private PacienteService pacienteService;

  @Mock
  private PacienteRepository pacienteRepository;

  @Test
  public void deve_criar_um_paciente() {
    Paciente paciente = criarPaciente("91354036085");

    when(pacienteRepository.save(paciente)).thenReturn(paciente);

    paciente = pacienteService.save(paciente);

    assertNotNull(paciente);
  }

}
