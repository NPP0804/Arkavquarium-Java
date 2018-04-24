import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
  
  private List<Integer> l1;
  private List<Double> l2;
  
  @BeforeEach
  void setUp() {
    l1 = new List<>();
    l2 = new List<>();
  }
  
  @Test
  void getFirst() {
    assertNull(l1.getFirst());
    assertNull(l2.getFirst());
    
    l1.add(1);
    assertSame(l1.getFirst(), l1.first);
    assertEquals(1, (int) l1.getFirst().getInfo());
    
    l1.add(2);
    assertSame(l1.getFirst(), l1.first);
    assertEquals(1, (int) l1.getFirst().getInfo());
  }
  
  @Test
  void getLast() {
    assertNull(l1.getLast());
    assertNull(l2.getLast());
    
    l1.add(1);
    assertSame(l1.getLast(), l1.last);
    assertEquals(1, (int) l1.getLast().getInfo());
    
    l1.add(2);
    assertSame(l1.getLast(), l1.last);
    assertEquals(2, (int) l1.getLast().getInfo());
  }
  
  @Test
  void isEmpty() {
    assertTrue(l1.isEmpty());
    
    l1.add(1);
    assertTrue(!l1.isEmpty());
  }
  
  @Test
  void add() {
    l1.add(1);
    assertEquals(1, (int) l1.getLast().getInfo());
    
    l1.add(2);
    assertEquals(2, (int) l1.getLast().getInfo());
  }
  
  @Test
  void remove() {
    l1.add(1);
    assertSame(l1.getLast(), l1.last);
    assertEquals(1, (int) l1.getLast().getInfo());
    
    l1.add(2);
    assertSame(l1.getLast(), l1.last);
    assertEquals(2, (int) l1.getLast().getInfo());
    
    l1.remove(1);
    assertEquals(2, (int) l1.getFirst().getInfo());
    
    l1.remove(2);
    assertNull(l1.getFirst());
  }
  
  @Test
  void get() {
    l1.add(0);
    l1.add(1);
    l1.add(2);
    l1.add(3);
    l1.add(10);
    
    assertEquals(0, (int) l1.get(0));
    assertEquals(10, (int) l1.get(4));
  }
  
  @Test
  void find() {
    l1.add(0);
    l1.add(1);
    l1.add(2);
    l1.add(1);
    l1.add(10);
    
    assertEquals(1, l1.find(1));
    assertEquals(4, l1.find(10));
    assertEquals(l1.find(5), -1);
  }
}