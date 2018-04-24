import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.Random;


public class Piranha extends Ikan {
  public static final int PIRANHA_FOOD_COUNTER_NAIK_TAHAP = 3;
  public static final int PIRANHA_HUNGER = 4000;
  private static final int speed = 20; //bisa diubah value nya

  /**
   * Class Constructor.
   */
  public Piranha() {
    super();
  }

  /**
   * Class Constructor depends on two parameters, absis and ordinate.
   * @param x Absis of Piranha.
   * @param y Ordinate of Piranha.
   */
  public Piranha(double x, double y) {
    super(x, y);
  }
  
  /**
   * Move randomly as long as times.
   * @param times number of move allowed.
   */
  public void move(double times) {
    super.decCounter();
    if (moveCounter == 1) {
      Random rand = new Random(System.currentTimeMillis());
      moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
      for (int i = 0; i < x; i++) {
        direction = Math.toRadians(rand.nextInt(360));
      }
    }
    x += speed * cos(super.getDirection()) * times;
    y += speed * sin(super.getDirection()) * times;
  }
  
  /**
   * Move Piranha towarda target position.
   * @param x absis of target's position.
   * @param y ordinate o target's position.
   * @param times number of move allowed.
   */
  public void moveTowardsTarget(double x, double y, double times) {
    double dir = atan2(y - this.y, x - this.x);
    if (dir < 0) {
      dir += 2 * PI;
    }
    super.setDirection(dir);
    this.x += speed * cos(super.getDirection()) * times;
    this.y += speed * sin(super.getDirection()) * times;
  }
  
  /**
   * Check if Piranha equals Object O.
   * @param o Object that will be compared to Piranha.
   * @return true of O == Piranha.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Piranha)) {
      return false;
    } else {
      Piranha p = (Piranha) o;
      return (super.getX() == p.getX() && super.getY() == p.getY()
        && super.getDirection() == p.getDirection()
        && super.getHunger() == p.getHunger()
        && super.getTahap() == p.getTahap()
        && super.getFoodCounter() == p.getFoodCounter());
    }
  }
}
