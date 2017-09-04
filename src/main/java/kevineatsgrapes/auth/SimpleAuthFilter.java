package kevineatsgrapes.auth;

import io.dropwizard.auth.AuthFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;

public class SimpleAuthFilter implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    MultivaluedMap<String, String> map = containerRequestContext.getUriInfo().getQueryParameters();
    List<String> values = map.getOrDefault("password", Collections.emptyList());

    if (values.size() != 1) {
      throw new WebApplicationException("unauthorized", Status.UNAUTHORIZED);
    }

    String password = System.getenv("ADMIN_PASSWORD");
    if (password == null || !password.equals(values.get(0))) {
      throw new WebApplicationException("unauthorized", Status.UNAUTHORIZED);
    }
  }
}
