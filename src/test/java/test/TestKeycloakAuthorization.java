package test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.apache.http.impl.client.HttpClients;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@QuarkusTestResource(KeycloakTestResource.class)
class TestKeycloakAuthorization {

  private AuthzClient authzClient;

  @BeforeEach
  public void setup() {
    authzClient = AuthzClient.create(new Configuration(
        "http://localhost:8180/auth",
        "realm-name",
        "client",
        Collections.singletonMap("secret", "secret"),
        HttpClients.createDefault()));
  }

  @Test
  public void test() {
    given()
        .when()
        .auth().oauth2(getToken("user", "pwd"))
        .get("/resource")
        .then()
        .statusCode(200)
        .body(equalTo("Hello"));
  }

  private String getToken(String userName, String password) {
    return authzClient.obtainAccessToken(userName, password).getToken();
  }

}
