package com.greenfieldapi.api;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import io.restassured.RestAssured;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase(refresh = RefreshMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ApiTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;
  }

  public String criarJson(Object obj) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
    
    Gson gson = gsonBuilder.create();
    return gson.toJson(obj);
  }

}

class LocalDateSerializer implements JsonSerializer<LocalDate> {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
      return new JsonPrimitive(formatter.format(localDate));
  }
}

class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
  @Override
  public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
  throws JsonParseException {
      return LocalDate.parse(json.getAsString(),
          DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
