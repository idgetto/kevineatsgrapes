package kevineatsgrapes.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.TimeUnit;

public class KevinMetrics {
  public static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();
  public static final ConsoleReporter REPORTER = ConsoleReporter.forRegistry(METRIC_REGISTRY)
      .convertRatesTo(TimeUnit.SECONDS)
      .convertDurationsTo(TimeUnit.MILLISECONDS)
      .build();

  static {
    REPORTER.start(10, TimeUnit.SECONDS);
  }
}
