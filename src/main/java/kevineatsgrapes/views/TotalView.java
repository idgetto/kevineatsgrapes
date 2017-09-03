package kevineatsgrapes.views;

import io.dropwizard.views.View;

public class TotalView extends View {
  private final int total;

  public TotalView(int total) {
    super("total.ftl");
    this.total = total;
  }

  public int getTotal() {
    return total;
  }
}
