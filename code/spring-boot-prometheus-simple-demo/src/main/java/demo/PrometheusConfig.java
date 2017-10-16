package demo;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.context.annotation.Configuration;

@Configuration
// Registers /prometheus endpoint
@EnablePrometheusEndpoint
// Exposes spring boot metrics via the prometheus endpoint
@EnableSpringBootMetricsCollector
class PrometheusConfig {

  static {
    //HACK Avoids duplicate metrics registration in case of Spring Boot dev-tools restarts
    CollectorRegistry.defaultRegistry.clear();

    // Add JVM Metrics
    DefaultExports.initialize();
  }
}
