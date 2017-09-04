package kevineatsgrapes.resources;


import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kevineatsgrapes.api.Meal;
import kevineatsgrapes.api.MealResults;
import kevineatsgrapes.auth.Authenticated;
import kevineatsgrapes.dao.MealsDao;
import kevineatsgrapes.manager.MealsManager;

@Path("/meals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MealsResource {

  private final MealsDao mealsDao;
  private final MealsManager mealsManager;

  public MealsResource(MealsDao mealsDao) {
    this.mealsDao = mealsDao;
    this.mealsManager = new MealsManager(mealsDao);
  }

  @GET
  @Timed
  public MealResults get() {
    return mealsManager.getAll();
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
