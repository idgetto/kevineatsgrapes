package kevineatsgrapes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
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
  private HttpClientConfiguration httpClient = new HttpClientConfiguration();

  @JsonProperty("httpClient")
  public HttpClientConfiguration getHttpClientConfiguration() {
    return httpClient;
  }

  @JsonProperty("httpClient")
  public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
    this.httpClient = httpClient;
  }

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
