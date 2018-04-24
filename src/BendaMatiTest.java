import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BendaMatiTest {

  BendaMati b1 = new BendaMati();
  BendaMati b2 = new BendaMati(3,5);

  @Test
  void getX() {
    assertTrue(b1.getX()==0);
    assertTrue(b2.getX()==3);
  }

  @Test
  void getY() {
    assertTrue(b1.getY()==0);
    assertTrue(b2.getY()==5);
  }

  @Test
  void setY() {
    b2.setY(9);
    assertTrue(b2.getY()==9);
  }

  @Test
  void move() {
    b1.move(1);
    assertTrue(b1.getY()==10);
  }
}