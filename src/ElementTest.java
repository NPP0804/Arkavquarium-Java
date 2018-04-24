import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementTest {
  private Element<Integer> e1;
  private Element<Integer> e2;
  
  @BeforeEach
  void setUp() {
    e1 = new Element<>(null, 0);
    e2 = new Element<>(null, 8);
  }
  
  @Test
  void getNext() {
    assertNull(e1.getNext());
    e1.setNext(e2);
    assertSame(e1.getNext(),e2);
  }
  
  @Test
  void setNext() {
    e2.setNext(e1);
    assertSame(e2.next, e1);
  }
  
  @Test
  void getInfo() {
    assertEquals(0, (int) e1.getInfo());
    assertEquals(8, (int) e2.getInfo());
  }
  
  @Test
  void setInfo() {
    e1.setInfo(-78);
    assertEquals(-78, (int) e1.info);
  }
}