import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SiputTest {
  private Siput s1;

  @Test
  public void moveTowardsTarget() {
    s1.moveTowardsTarget(2, 3, 5);
    assertEquals(SCREEN_WIDTH / 2 - 50, s1.getX());
    assertEquals(Math.PI, s1.getDirection());
  }
}