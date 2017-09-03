package kevineatsgrapes.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;

public class Meal {
  long id;

  @Min(1)
  private int count;

  long time;

  public Meal() {
  }

  public Meal(long id, int count, long time) {
    this.id = id;
    this.count = count;
    this.time = time;
  }

  @JsonProperty
  public int getCount() {
    return count;
  }

  @JsonProperty
  public long getTime() {
    return time;
  }
}
