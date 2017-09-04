package kevineatsgrapes.manager;

import java.util.List;
import kevineatsgrapes.api.Meal;
import kevineatsgrapes.api.MealResults;
import kevineatsgrapes.dao.MealsDao;

public class MealsManager {
  private MealsDao mealsDao;

  public MealsManager(MealsDao mealsDao) {
    this.mealsDao = mealsDao;
  }

  public MealResults getAll() {
    List<Meal> meals = mealsDao.getAll();
    int total = meals.stream()
        .mapToInt(Meal::getCount)
        .sum();

    return new MealResults(total, meals);
  }
}
