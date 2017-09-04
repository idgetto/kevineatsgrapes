package kevineatsgrapes.servlets;

import com.codahale.metrics.servlets.MetricsServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import kevineatsgrapes.metrics.KevinMetrics;

public class KevinMetricsServlet extends MetricsServlet {

  public KevinMetricsServlet() {
    super(KevinMetrics.METRIC_REGISTRY);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String password = System.getenv("ADMIN_PASSWORD");
    if (password == null) {
      throw new WebApplicationException("unauthorized", 401);
    }

    String credentials = "password=" + password;
    String query = req.getQueryString();
    if (query == null || !query.contains(credentials)) {
      throw new WebApplicationException("unauthorized", 401);
    }

    super.doGet(req, resp);
  }
}
