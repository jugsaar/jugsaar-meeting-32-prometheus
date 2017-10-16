package demo.api.monitoring;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/*")
class ApiMonitorFilter extends OncePerRequestFilter {

  /**
   * Prometheus counter
   */
  private static final Counter API_REQUESTS_TOTAL = Counter.build()
    .name("api_requests_total")
    .labelNames("handler")
    .help("Total number of API requests.")
    .register();

  /**
   * Prometheus Gauge
   */
  private static final Gauge API_REQUESTS_IN_PROGRESS = Gauge.build()
    .name("api_requests_inprogress")
    .help("Number of API requests in progress")
    .register();

  /**
   * Prometheus histogram
   */
  private static final Histogram API_REQUESTS_LATENCY_SECONDS = Histogram.build()
    .name("api_requests_latency_seconds")
    .help("API Request latency in seconds.")
    .register();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    API_REQUESTS_TOTAL.labels(extractApiHandler(request)).inc();

    API_REQUESTS_IN_PROGRESS.inc();

    Histogram.Timer latencyTimer = API_REQUESTS_LATENCY_SECONDS.startTimer();

    try {
      filterChain.doFilter(request, response);
    } finally {

      latencyTimer.observeDuration();
      API_REQUESTS_IN_PROGRESS.dec();
    }
  }

  private String extractApiHandler(HttpServletRequest request) {
    // TODO proper handler extraction
    return request.getRequestURI();
  }
}
