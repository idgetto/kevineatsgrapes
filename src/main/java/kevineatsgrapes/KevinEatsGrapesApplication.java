package kevineatsgrapes;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import kevineatsgrapes.health.TemplateHealthCheck;
import kevineatsgrapes.resources.KevinEatsGrapesResource;

public class KevinEatsGrapesApplication extends Application<KevinEatsGrapesConfiguration> {

  public static void main(final String[] args) throws Exception {
    new KevinEatsGrapesApplication().run(args);
  }

  @Override
  public String getName() {
    return "kevineatsgrapes";
  }

  @Override
  public void initialize(final Bootstrap<KevinEatsGrapesConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(final KevinEatsGrapesConfiguration configuration,
      final Environment environment) {
    final KevinEatsGrapesResource resource = new KevinEatsGrapesResource(
        configuration.getTemplate(),
        configuration.getDefaultName());
    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
        configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);
    environment.jersey().register(resource);
  }
}
