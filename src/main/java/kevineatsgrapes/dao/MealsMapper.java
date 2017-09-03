package kevineatsgrapes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import kevineatsgrapes.api.Meal;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class MealsMapper implements ResultSetMapper<Meal> {

  @Override
  public Meal map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    return new Meal(
        resultSet.getInt("id"),
        resultSet.getInt("count"),
        resultSet.getLong("time"));
  }
}
