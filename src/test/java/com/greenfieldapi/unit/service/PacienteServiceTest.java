package com.greenfieldapi.unit.service;

import static com.greenfieldapi.TestUtils.criarPaciente;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

  @Test
  public void deve_obter_todos_os_pacientes() {
    Paciente paciente1 = criarPaciente("91354036085");
    Paciente paciente2 = criarPaciente("58896718040");
    Paciente paciente3 = criarPaciente("44981367058");

    when(pacienteRepository.findAll()).thenReturn(Arrays.asList(paciente1, paciente2, paciente3));

    List<Paciente> pacientes = pacienteService.findAll();

    assertEquals(3, pacientes.size());
  }

  @Test
  public void deve_obter_um_paciente() {
    Paciente paciente = criarPaciente("91354036085");
    paciente.setId(1L);

    when(pacienteRepository.findById(paciente.getId()))
        .thenReturn(Optional.ofNullable(paciente));

    paciente = pacienteService.findById(paciente.getId());

    assertNotNull(paciente);
  }

}
