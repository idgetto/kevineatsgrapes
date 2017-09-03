package kevineatsgrapes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class KevinEatsGrapesConfiguration extends Configuration {

  @NotEmpty
  private String template;

  @NotEmpty
  private String defaultName = "Stranger";

  @Valid
  @NotNull
  @JsonProperty
  private DataSourceFactory database = new DataSourceFactory();

  @JsonProperty
  public String getTemplate() {
    return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
    this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String defaultName) {
    this.defaultName = defaultName;
  }

  public DataSourceFactory getDataSourceFactory() {
    return database;
  }
}
