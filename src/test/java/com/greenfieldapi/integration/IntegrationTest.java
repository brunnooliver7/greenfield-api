package com.greenfieldapi.integration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public abstract class IntegrationTest {

  @Autowired
  private DataSource dataSource;

  @LocalServerPort
  private int port;

  @Container
  private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
      .withDatabaseName("testdb")
      .withUsername("root")
      .withPassword("root");

  @BeforeClass
  public static void beforeAll() {
    postgresContainer.start();
  }

  @Before
  public void setUp() throws Exception {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;

    if (!postgresContainer.isRunning()) {
      postgresContainer.start();
    }

    System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
    System.setProperty("spring.datasource.username", postgresContainer.getUsername());
    System.setProperty("spring.datasource.password", postgresContainer.getPassword());

    clearDatabase();
  }

  private void clearDatabase() throws Exception {
    try {
      String sql = Files.readString(Path.of("src/test/resources/cleanup.sql"), StandardCharsets.UTF_8);
      try (Statement statement = dataSource.getConnection().createStatement()) {
        statement.execute(sql);
      }
    } catch (IOException | SQLException e) {
      throw new Exception("Erro ao executar cleanup.sql");
    }
  }

}
