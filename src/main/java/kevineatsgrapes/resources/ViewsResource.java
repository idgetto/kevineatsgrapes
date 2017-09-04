package kevineatsgrapes.resources;

import com.codahale.metrics.Meter;
import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kevineatsgrapes.metrics.KevinMetrics;
import kevineatsgrapes.views.IndexView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class ViewsResource {
  private final Meter requests = KevinMetrics.METRIC_REGISTRY.meter("requests");

  @GET
  @Timed
  public IndexView getIndex() {
    requests.mark();
    return new IndexView();
  }
}
