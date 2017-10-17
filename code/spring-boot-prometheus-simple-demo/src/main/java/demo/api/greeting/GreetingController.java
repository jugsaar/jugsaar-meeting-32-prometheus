package demo.api.greeting;

import demo.api.HumanWorker;
import io.prometheus.client.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/greetings")
@RequiredArgsConstructor
class GreetingController {

  private final HumanWorker worker;

  /**
   * Prometheus counter
   */
  private static final Counter GREETINGS_TOTAL = Counter.build()
    .name("api_greeting_requests_total")
    .help("Total number of greeting requests.")
    .register();

//  /**
//   * Spring Boot Actuator counter
//   */
//  private final CounterService counterService;
//
//  public GreetingControllerWithMetrics(CounterService counterService) {
//    this.counterService = counterService;
//  }

  @GetMapping
  Object greet(@RequestParam(defaultValue = "World") String name) {

    // shows up in the /prometheus endpoint
    GREETINGS_TOTAL.inc();

    worker.work();

//    // shows up in the /metrics endpoint -> type gauge instead of counter...
//    counterService.increment("api_via_actuator.greeting.requests.total");

    Map<String, Object> data = new HashMap<>();
    data.put("greeting", "Hello " + name);
    data.put("metrics", true);
    data.put("timestamp", System.currentTimeMillis());

    return data;
  }
}
