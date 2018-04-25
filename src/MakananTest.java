import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MakananTest {
  Makanan m1 = new Makanan(5, 6);
  Makanan m2 = new Makanan(2, 3);
  Makanan m3 = new Makanan(2, 3);

  @Test
  public void isEdible() {
    assertTrue(m1.isEdible());
  }

  @Test
  public void setEdible() {
    m1.setEdible(true);
    assertTrue(m1.isEdible());
  }

  @Test
  public void isSama() {
    assertTrue(m2.isSama(m3));
  }

  @Test
  public void isBeda() {
    assertTrue(m1.isBeda(m2));
  }
}