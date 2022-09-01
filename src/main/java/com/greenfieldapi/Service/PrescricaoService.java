package com.greenfieldapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greenfieldapi.Model.Medicamento;
import com.greenfieldapi.Model.Medico;
import com.greenfieldapi.Model.Paciente;
import com.greenfieldapi.Model.Prescricao;
import com.greenfieldapi.Repository.PrescricaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescricaoService {

  private final PrescricaoRepository prescricaoRepository;
  private final MedicoService medicoService;
  private final PacienteService pacienteService;
  private final MedicamentoService medicamentoService;

  public List<Prescricao> findByMedicoId(Long medicoId) {
    return prescricaoRepository.findByMedicoId(medicoId);
  }

  public List<Prescricao> findByPacienteId(Long pacienteId) {
    return prescricaoRepository.findByPacienteId(pacienteId);
  }

  public Prescricao findById(Long id) {
    return prescricaoRepository.findById(id).get();
  }

  public Prescricao save(Prescricao prescricao) {
    Medico medico = medicoService.findById(prescricao.getMedicoId());
    prescricao.setMedico(medico);

    Paciente paciente = pacienteService.findById(prescricao.getPacienteId());
    prescricao.setPaciente(paciente);

    Prescricao prescricaoSalva = prescricaoRepository.save(prescricao);

    List<Medicamento> medicamentosSalvos = prescricao.getMedicamentos().stream()
        .map(medicamento -> {
          medicamento.setPrescricao(prescricaoSalva);
          medicamento.setPrescricaoId(prescricaoSalva.getId());
          return medicamentoService.save(medicamento);
        })
        .toList();

    prescricaoSalva.setMedicamentos(medicamentosSalvos);

    return prescricaoSalva;
  }
}
