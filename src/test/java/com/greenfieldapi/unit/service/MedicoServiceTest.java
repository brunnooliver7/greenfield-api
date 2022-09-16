package com.greenfieldapi.unit.service;

import static com.greenfieldapi.TestUtils.criarMedico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.MedicoNaoEncontradoException;
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
    Medico medico = criarMedico("91354036085", "a@email", "001");

    when(medicoRepository.save(medico)).thenReturn(medico);

    medico = medicoService.save(medico);

    assertNotNull(medico);
  }

  @Test
  public void deve_obter_todos_os_medicos() {
    Medico medico1 = criarMedico("91354036085", "a@email", "001");
    Medico medico2 = criarMedico("58896718040", "b@email", "002");
    Medico medico3 = criarMedico("44981367058", "c@email", "003");

    when(medicoRepository.findAll()).thenReturn(Arrays.asList(medico1, medico2, medico3));

    List<Medico> medicos = medicoService.findAll();

    assertEquals(3, medicos.size());
  }

  @Test
  public void deve_obter_um_medico() throws MedicoNaoEncontradoException {
    Medico medico = criarMedico("91354036085", "a@email", "001");
    medico.setId(1L);

    when(medicoRepository.findById(medico.getId()))
      .thenReturn(Optional.ofNullable(medico));

    medico = medicoService.findById(medico.getId());

    assertNotNull(medico);
  }

  @Test
  public void deve_deletar_um_medico() {
    medicoService.delete(1L);
    verify(medicoRepository, times(1)).deleteById(anyLong());
  }

}
