package com.greenfieldapi.api.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.MedicoRepository;
import com.greenfieldapi.domain.repository.PacienteRepository;
import com.greenfieldapi.domain.repository.PrescricaoRepository;

@Component
@Profile("dev")
public class DadosIniciais implements ApplicationRunner {

  @Autowired
  private Flyway flyway;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Autowired
  private PrescricaoRepository prescricaoRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    flyway.clean();
    flyway.migrate();

    Medico medico1 = medicoRepository.save(criarMedico1());
    Medico medico2 = medicoRepository.save(criarMedico2());
    Medico medico3 = medicoRepository.save(criarMedico3());

    Paciente paciente1 = pacienteRepository.save(criaPaciente1());
    Paciente paciente2 = pacienteRepository.save(criaPaciente2());
    Paciente paciente3 = pacienteRepository.save(criaPaciente3());

    Medicamento medicamento1 = criaMedicamento1();
    Medicamento medicamento2 = criaMedicamento2();
    Medicamento medicamento3 = criaMedicamento3();
    Medicamento medicamento4 = criaMedicamento4();
    Medicamento medicamento5 = criaMedicamento5();

    prescricaoRepository.save(criaPrescricao(medico1.getId(), paciente1.getId(), Arrays.asList(medicamento1, medicamento2)));
    prescricaoRepository.save(criaPrescricao(medico2.getId(), paciente2.getId(), Arrays.asList(medicamento3)));
    prescricaoRepository.save(criaPrescricao(medico3.getId(), paciente2.getId(), Arrays.asList(medicamento4)));
    prescricaoRepository.save(criaPrescricao(medico3.getId(), paciente3.getId(), Arrays.asList(medicamento5)));
  }

  private Medico criarMedico1() {
    return Medico.builder()
      .cpf("07978344002")
      .email("andre@email")
      .nome("André da Silva")
      .dtNascimento(LocalDate.of(2000, 1, 1))
      .crm("001")
      .estadoRegistroCrm("ativo")
      .estado("DF")
      .sexo("M")
      .senha("secreta1")
      .build();
  }
  
  private Medico criarMedico2() {
    return Medico.builder()
      .cpf("89243903098")
      .email("bernardo@email")
      .nome("Bernardo Gonçalvez")
      .dtNascimento(LocalDate.of(2000, 2, 2))
      .crm("002")
      .estadoRegistroCrm("suspenso")
      .estado("SP")
      .sexo("M")
      .senha("secreta2")
      .build();    
  }

  private Medico criarMedico3() {
    return Medico.builder()
      .cpf("13652543050")
      .email("camila@email")
      .nome("Camila Vasconcelos")
      .dtNascimento(LocalDate.of(2000, 3, 3))
      .crm("003")
      .estadoRegistroCrm("ativo")
      .estado("MG")
      .sexo("F")
      .senha("secreta3")
      .build();    
  }

  private Paciente criaPaciente1() {
    return Paciente.builder()
      .cpf("99511685074")
      .nome("Augusto Borges")
      .dtNascimento(LocalDate.of(2001, 1, 1))
      .sexo("M")
      .build();
  }

  private Paciente criaPaciente2() {
    return Paciente.builder()
      .cpf("38143136078")
      .nome("Bruna Matos")
      .dtNascimento(LocalDate.of(2001, 2, 2))
      .sexo("F")
      .build();
  }

  private Paciente criaPaciente3() {
    return Paciente.builder()
      .cpf("45244172069")
      .nome("Carol Albuquerque")
      .dtNascimento(LocalDate.of(2001, 3, 3))
      .sexo("F")
      .build();
  }

  private Medicamento criaMedicamento1() {
    return Medicamento.builder()
      .descricao("ibuprofeno")
      .quantidade(2)
      .dosagem("400mg")
      .frequencia("3 vezes ao dia")
      .build();
  }
  
  private Medicamento criaMedicamento2() {
    return Medicamento.builder()
      .descricao("dipirona")
      .quantidade(1)
      .dosagem("40 gotas")
      .frequencia("2 vezes ao dia")
      .build();
  }
  
  private Medicamento criaMedicamento3() {
    return Medicamento.builder()
      .descricao("resfenol")
      .quantidade(2)
      .dosagem("100mg")
      .frequencia("3 vezes ao dia")
      .build();
  }
  
  private Medicamento criaMedicamento4() {
    return Medicamento.builder()
      .descricao("isoniazida")
      .quantidade(6)
      .dosagem("400mg")
      .frequencia("3 vezes ao dia")
      .build();
  }
  
  private Medicamento criaMedicamento5() {
    return Medicamento.builder()
      .descricao("pantoprazol")
      .quantidade(10)
      .dosagem("20mg")
      .frequencia("1 vez ao dia")
      .build();
  }
  
  private Prescricao criaPrescricao(Long medicoId, Long pacienteId, List<Medicamento> medicamentos) {
    return Prescricao.builder()
      .medicoId(medicoId)
      .pacienteId(pacienteId)
      .medicamentos(medicamentos)
      .build();
  }
  
}
