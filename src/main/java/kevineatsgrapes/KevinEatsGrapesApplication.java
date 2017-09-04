package kevineatsgrapes;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import kevineatsgrapes.auth.SimpleAuthFilter;
import kevineatsgrapes.auth.SimpleDynamicAuthFilter;
import kevineatsgrapes.dao.MealsDao;
import kevineatsgrapes.health.TemplateHealthCheck;
import kevineatsgrapes.resources.HelloWorldResource;
import kevineatsgrapes.resources.MealsResource;
import kevineatsgrapes.resources.ViewsResource;
import org.skife.jdbi.v2.DBI;

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
    bootstrap.addBundle(new ViewBundle<>());

    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
            new EnvironmentVariableSubstitutor()));

    bootstrap.addBundle(new MigrationsBundle<KevinEatsGrapesConfiguration>() {
      @Override
      public PooledDataSourceFactory getDataSourceFactory(
          KevinEatsGrapesConfiguration kevinEatsGrapesConfiguration) {
        return kevinEatsGrapesConfiguration.getDataSourceFactory();
      }
    });

    bootstrap.addBundle(new AssetsBundle("/assets", "/assets"));
  }

  @Override
  public void run(final KevinEatsGrapesConfiguration configuration,
      final Environment environment) {
    final HelloWorldResource helloWorldResource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName());

    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
        configuration.getTemplate());

    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory
        .build(environment, configuration.getDataSourceFactory(), "kevineatsgrapes");

    final MealsDao mealsDao = jdbi.onDemand(MealsDao.class);
    final MealsResource mealsResource = new MealsResource(mealsDao);
    final ViewsResource viewsResource = new ViewsResource(mealsDao);

    environment.healthChecks().register("template", healthCheck);
    environment.jersey().register(helloWorldResource);
    environment.jersey().register(mealsResource);
    environment.jersey().register(viewsResource);
    environment.jersey().register(new SimpleDynamicAuthFilter());
  }
}
