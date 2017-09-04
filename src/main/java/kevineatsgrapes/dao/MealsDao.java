package kevineatsgrapes.dao;

import java.util.List;
import kevineatsgrapes.api.Meal;
import kevineatsgrapes.api.MealResults;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

@RegisterMapper(MealsMapper.class)
public interface MealsDao {

  @SqlQuery("SELECT SUM(count) FROM meals")
  int getTotal();

  @SqlQuery("SELECT * FROM meals ORDER BY time DESC")
  List<Meal> getAll();

  @SqlQuery("SELECT * FROM meals WHERE id = :id")
  Meal get(@Bind("id") long id);

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO meals (count, time) VALUES (:count, :time)")
  int insert(@BindBean Meal meal);
}
