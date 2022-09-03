package com.greenfieldapi.IntegrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.greenfieldapi.domain.model.Medico;

@SpringBootTest
@AutoConfigureMockMvc
// @TestPropertySource(properties = {
//   "spring.datasource.url=jdbc:tc:postgresql:13.2-alpine://"
// })
public class MedicoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  // @Test
  // void deveCriarMedico() throws Exception {
  //   char sexo = 'F';

  //   Medico medico = Medico.builder()
  //       .cpf("cpf")
  //       .email("email")
  //       .nome("nome")
  //       // .dtNascimento(LocalDate.now())
  //       .crm("crm")
  //       .estadoRegistroCrm("estadoRegistroCrm")
  //       .estado("ES")
  //       .sexo(sexo)
  //       .senha("senha")
  //       .build();

  //   Gson gson = new Gson();
  //   String json = gson.toJson(medico);

  //   System.out.println(json);

  //   mockMvc.perform(post("/medico")
  //       .contentType(MediaType.APPLICATION_JSON)
  //       .content(json)).andExpect(status().isCreated());
  // }
}
