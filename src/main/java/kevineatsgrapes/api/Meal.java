package kevineatsgrapes.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.Min;

public class Meal {
  long id;

  @Min(1)
  private int count;

  long time;

  public Meal() {
    this.time = System.currentTimeMillis();
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

  @JsonProperty
  public String getDate() {
    ZoneId zoneId = ZoneId.of("America/New_York");
    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(getTime()), zoneId);
    return dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy h:mm a"));
  }
}
