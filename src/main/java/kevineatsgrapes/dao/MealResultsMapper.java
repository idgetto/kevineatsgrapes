package kevineatsgrapes.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kevineatsgrapes.api.Meal;
import kevineatsgrapes.api.MealResults;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class MealResultsMapper implements ResultSetMapper<MealResults> {

  @Override
  public MealResults map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    ResultSetMetaData metaData  = resultSet.getMetaData();
    List<Meal> meals = new ArrayList<>();


    try {
      do {
        meals.add(new Meal(
            resultSet.getLong("id"),
            resultSet.getInt("count"),
            resultSet.getLong("time")));
      } while (resultSet.next());
    } catch (SQLException e) {
    }

    int total = meals.stream()
        .map(Meal::getCount)
        .mapToInt(Integer::intValue)
        .sum();

    return new MealResults(total, meals);
  }
}
