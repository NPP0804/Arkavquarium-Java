import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KoinTest {
  Koin k1 = new Koin(3, 7);
  Koin k2 = new Koin(2, 5, 9);
  Koin k3 = new Koin(2, 5, 9);

  @Test
  public void getNilai() {
    assertTrue(k1.getNilai() == 0);
  }

  @Test
  public void setNilai() {
    k1.setNilai(9);
    assertTrue(k1.getNilai() == 9);
  }

  @Test
  public void isSama() {
    assertTrue(k2.isSama(k3));
  }

  @Test
  public void isBeda() {
    assertTrue(k1.isBeda(k2));
  }
}