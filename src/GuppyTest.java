import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GuppyTest {
  private Guppy g1;
  private Guppy g2;
  private Guppy g3;
  
  @BeforeEach
  void setUp() {
    g1 = new Guppy();
    g2 = new Guppy(10,-10);
    g3 = new Guppy(10,-10);
  }
  
  @Test
  void getKoinCounter() {
    assertEquals(2000, g1.getKoinCounter());
  }
  
  @Test
  void setKoinCounter() {
    g1.setKoinCounter(1000);
    assertEquals(1000, g1.koinCounter);
  }
  
  @Test
  void equals() {
    Piranha p = new Piranha();
    //noinspection EqualsBetweenInconvertibleTypes
    assertTrue(!g1.equals(p));
    assertEquals(g2, g3);
    assertTrue(!g2.equals(g1));
  }
  
  @Test
  void getHunger() {
    assertEquals(4000, g1.getHunger());
  }
  
  @Test
  void getTahap() {
    assertEquals(1, g1.getTahap());
  }
  
  @Test
  void getFoodCounter() {
    assertEquals(0, g1.getFoodCounter());
  }
  
  @Test
  void isMati() {
    assertTrue(!g1.isMati());
  }
  
  @Test
  void getHungerState() {
    assertTrue(!g1.getHungerState());
  }
  
  @Test
  void setHunger() {
    g1.setHunger(3000);
    assertEquals(3000, g1.hunger);
  }
  
  @Test
  void setTahap() {
    g1.setTahap(2);
    assertEquals(2, g1.tahap);
  }
  
  @Test
  void setFoodCounter() {
    g1.setFoodCounter(1);
    assertEquals(1, g1.foodCounter);
  }
  
  @Test
  void setMati() {
    g1.setMati(true);
    assertTrue(g1.mati);
  }
  
  @Test
  void setHungerState() {
    g1.setHunger(100);
    g1.setHungerState();
    assertTrue(g1.hungerState);
    
    g1.setHunger(5000);
    g1.setHungerState();
    assertTrue(!g1.hungerState);
  }
  
  @Test
  void getX() {
    assertEquals(0, g1.getX(), 0.0);
    assertEquals(10, g2.getX(), 0.0);
  }
  
  @Test
  void setX() {
    g3.setX(-5.3);
    assertEquals(g3.x, -5.3, 0.0);
  }
  
  @Test
  void getY() {
    assertEquals(0, g1.getY(), 0.0);
    assertEquals(g2.getY(), -10, 0.0);
  }
  
  @Test
  void setY() {
    g3.setY(2.54);
    assertEquals(2.54, g3.y, 0.0);
  }
}