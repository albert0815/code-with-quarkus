package test;

import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;

public class KeycloakContainer extends GenericContainer<KeycloakContainer> {
  private static final String KEYCLOAK_IMAGE = "quay.io/keycloak/keycloak:11.0.3";
  private static final int FIXED_PORT = 8180;
  private static final String USER = "admin";
  private static final String PASSWORD = "admin";

  private static final String REALM_FILE = "/tmp/realm.json";

  public KeycloakContainer() {
    super(KEYCLOAK_IMAGE);
    addFixedExposedPort(FIXED_PORT, 8080);
    withEnv("KEYCLOAK_USER", USER);
    withEnv("KEYCLOAK_PASSWORD", PASSWORD);
    withEnv("KEYCLOAK_IMPORT", REALM_FILE);
    withClasspathResourceMapping("keycloak-realm.json", REALM_FILE, BindMode.READ_ONLY);
  }
}
