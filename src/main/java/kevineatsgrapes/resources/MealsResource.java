package kevineatsgrapes.resources;


import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kevineatsgrapes.api.Meal;
import kevineatsgrapes.auth.Authenticated;
import kevineatsgrapes.dao.MealsDao;

@Path("/meals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealsResource {

  private final MealsDao mealsDao;

  public MealsResource(MealsDao mealsDao) {
    this.mealsDao = mealsDao;
  }

  @GET
  @Timed
  public List<Meal> get() {
    return mealsDao.getAll();
  }

  @GET
  @Timed
  @Path("/total")
  public int getTotal() {
    return mealsDao.getTotal();
  }

  @POST
  @Authenticated
  public Meal create(Meal meal) {
    int id = mealsDao.insert(meal);
    return new Meal(id, meal.getCount(), meal.getTime());
  }
}
