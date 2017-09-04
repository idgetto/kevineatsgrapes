package kevineatsgrapes.auth;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class SimpleDynamicAuthFilter implements DynamicFeature {

  @Override
  public void configure(ResourceInfo resourceInfo, FeatureContext featureContext) {
    if (resourceInfo.getResourceMethod().getAnnotation(Authenticated.class) != null) {
      featureContext.register(SimpleAuthFilter.class);
    }
  }
}
