package com.greenfieldapi.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@ActiveProfiles("test")
public class ControllerUnitTest {
  
  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    RestAssuredMockMvc.mockMvc(this.mockMvc);
  }

}
