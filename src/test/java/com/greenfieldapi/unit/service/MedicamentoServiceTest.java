package com.greenfieldapi.unit.service;

import static com.greenfieldapi.TestUtils.criarMedicamento;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.repository.MedicamentoRepository;
import com.greenfieldapi.domain.service.MedicamentoService;

@ExtendWith(MockitoExtension.class)
public class MedicamentoServiceTest {
  
  @InjectMocks
  private MedicamentoService medicamentoService;

  @Mock
  private MedicamentoRepository medicamentoRepository;

  @Test
  public void deve_criar_um_medicamento() {
    Medicamento medicamento = criarMedicamento();

    when(medicamentoRepository.save(medicamento)).thenReturn(medicamento);

    medicamento = medicamentoService.save(medicamento);

    assertNotNull(medicamento);
  }

  @Test
  public void deve_obter_um_medicamento() {
    Medicamento medicamento = criarMedicamento();
    medicamento.setId(1L);

    when(medicamentoRepository.findById(medicamento.getId()))
      .thenReturn(Optional.ofNullable(medicamento));

      medicamento = medicamentoService.findById(medicamento.getId());

    assertNotNull(medicamento);
  }

  @Test
  public void deve_deletar_um_medicamento() {
    medicamentoService.delete(1L);
    verify(medicamentoRepository, times(1)).deleteById(anyLong());
  }

}
