package kevineatsgrapes.dao;

import kevineatsgrapes.api.Meal;
import kevineatsgrapes.api.MealResults;
import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.ResultSetMapperFactory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class MealMapperFactory implements ResultSetMapperFactory {

  @Override
  public boolean accepts(Class aClass, StatementContext statementContext) {
    return aClass.equals(Meal.class) || aClass.equals(MealResults.class);
  }

  @Override
  public ResultSetMapper mapperFor(Class aClass, StatementContext statementContext) {
    if (aClass.equals(Meal.class)) {
      return new MealsMapper();
    }

    if (aClass.equals(MealResults.class)) {
      return new MealResultsMapper();
    }

    return new DefaultMapper();
  }
}
