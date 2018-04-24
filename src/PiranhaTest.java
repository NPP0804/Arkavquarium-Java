import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PiranhaTest {
  
  private Piranha p1;
  private Piranha p2;
  private Piranha p3;
  
  @BeforeEach
  void setUp() {
    p1 = new Piranha();
    p2 = new Piranha(10,-10);
    p3 = new Piranha(10,-10);
  }
  
  @Test
  void equals() {
    assertTrue(!p1.equals(p2));
    assertEquals(p3, p2);
    Guppy g = new Guppy();
    //noinspection EqualsBetweenInconvertibleTypes
    assertTrue(!p1.equals(g));
  }
  
  @Test
  void getHunger() {
    assertEquals(4000, p1.getHunger());
  }
  
  @Test
  void getTahap() {
    assertEquals(1, p1.getTahap());
  }
  
  @Test
  void getFoodCounter() {
    assertEquals(0, p1.getFoodCounter());
  }
  
  @Test
  void isMati() {
    assertTrue(!p1.isMati());
  }
  
  @Test
  void getHungerState() {
    assertTrue(!p1.getHungerState());
  }
  
  @Test
  void setHunger() {
    p1.setHunger(3000);
    assertEquals(3000, p1.hunger);
  }
  
  @Test
  void setTahap() {
    p1.setTahap(2);
    assertEquals(2, p1.tahap);
  }
  
  @Test
  void setFoodCounter() {
    p1.setFoodCounter(1);
    assertEquals(1, p1.foodCounter);
  }
  
  @Test
  void setMati() {
    p1.setMati(true);
    assertTrue(p1.mati);
  }
  
  @Test
  void setHungerState() {
    p1.setHunger(100);
    p1.setHungerState();
    assertTrue(p1.hungerState);
    p1.setHunger(5000);
    p1.setHungerState();
    assertTrue(!p1.hungerState);
  }
  
  @Test
  void getX() {
    assertEquals(0, p1.getX(), 0.0);
    assertEquals(10, p2.getX(), 0.0);
  }
  
  @Test
  void setX() {
    p3.setX(-5.3);
    assertEquals(p3.x, -5.3, 0.0);
  }
  
  @Test
  void getY() {
    assertEquals(0, p1.getY(), 0.0);
    assertEquals(p2.getY(), -10, 0.0);
  }
  
  @Test
  void setY() {
    p3.setY(2.54);
    assertEquals(2.54, p3.y, 0.0);
  }
}