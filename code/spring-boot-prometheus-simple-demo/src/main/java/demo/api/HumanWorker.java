package demo.api;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class HumanWorker {

  public void work() {

    double idea = dream();

    if (idea < 0.8) {
      think(200, MILLISECONDS);
      return;
    }

    if (idea < 0.9) {
      think(1500, MILLISECONDS);
      return;
    }

    if (idea < 0.95) {
      think(3000, MILLISECONDS);
    }
  }

  private double dream() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private static void think(long amount, TimeUnit timeUnit) {
    try {
      timeUnit.sleep(amount);
    } catch (InterruptedException ignore) {
    }
  }
}
