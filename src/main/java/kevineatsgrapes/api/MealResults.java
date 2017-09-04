package kevineatsgrapes.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MealResults {

  private int total;
  private List<Meal> meals;

  public MealResults() {
  }

  public MealResults(int total, List<Meal> meals) {
    this.total = total;
    this.meals = meals;
  }

  @JsonProperty
  public int getTotal() {
    return total;
  }

  @JsonProperty
  public List<Meal> getMeals() {
    return meals;
  }
}
