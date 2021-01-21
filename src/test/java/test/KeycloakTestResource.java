package test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class KeycloakTestResource implements
    QuarkusTestResourceLifecycleManager {
  private GenericContainer<?> container;

  @Override
  public Map<String, String> start() {

    container = new KeycloakContainer();
    container.waitingFor(Wait.forHttp("/auth").withStartupTimeout(Duration.ofMinutes(10)));
    container.start();
    // System.out.println(container.getLogs());

    return Collections.emptyMap();
  }

  @Override
  public void stop() {
    Optional.ofNullable(container).ifPresent(GenericContainer::stop);
  }

}
