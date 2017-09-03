package kevineatsgrapes.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kevineatsgrapes.dao.MealsDao;
import kevineatsgrapes.views.TotalView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class ViewsResource {
  private final MealsDao mealsDao;

  public ViewsResource(MealsDao mealsDao) {
    this.mealsDao = mealsDao;
  }

  @GET
  public TotalView get() {
    return new TotalView(mealsDao.getTotal());
  }
}
