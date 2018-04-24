import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.Random;

public class Guppy extends Ikan {
  public static final int GUPPY_FOOD_COUNTER_NAIK_TAHAP = 5;
  public static final int GUPPY_HUNGER = 4000;
  @SuppressWarnings("WeakerAccess")
  public static final int PERIODE_KOIN = 2000;
  private static final int speed = 20; //bisa diubah value nya
  protected int koinCounter;
  
  /**
   * Class Constructor.
   */
  public Guppy() {
    super();
    koinCounter = PERIODE_KOIN;
  }
  
  /**
   * Class Constructor depends on two parameters, absis and ordinate.
   * @param x Absis of Guppy.
   * @param y Ordinate of Guppy.
   */
  public Guppy(double x, double y) {
    super(x,y);
    koinCounter = PERIODE_KOIN;
  }
  
  /**
   * Move randomly as long as times.
   * @param times number of move allowed.
   */
  public void move(double times) {
    super.decCounter();
    if (moveCounter == 1) {
      Random rand = new Random(System.currentTimeMillis());
      direction = Math.toRadians(rand.nextInt(360));
    }
    x += speed * cos(super.getDirection()) * times;
    y += speed * sin(super.getDirection()) * times;
    koinCounter--;
  }
  
  /**
   * Move Guppy towarda target position.
   * @param x absis of target's position.
   * @param y ordinate o target's position.
   * @param times number of move allowed.
   */
  public void moveTowardsTarget(double x, double y, double times) {
    double dir =  Math.atan2(y - this.y, x - this.x);
    if (dir < 0) {
      dir = dir + 2 * PI;
    }
<<<<<<< HEAD

    /**
     * Mengubah salah satu isi makanan edible pada list menjadi non edible
     * @param times
     */
    public void move(double times) { // gerak arah random selama time
        super.decCounter();
        if(moveCounter == 1){
            Random rand = new Random(System.currentTimeMillis());
            direction = Math.toRadians(rand.nextInt(360));
        }
        setX(getX() + speed*Math.cos(super.getDirection())*times);
        setY(getY() + speed*Math.sin(super.getDirection())*times);
        koinCounter--;
    }

    /**
     *
     * @param x
     * @param y
     * @param times
     */
    public void moveTowardsTarget(double x, double y, double times) { //gerak arah x selama time
        double dir =  Math.atan2(y-this.y, x-this.x);
        if (dir<0) {
            dir = dir + 2*Math.PI;
        }
        super.setDirection(dir);
        setX(getX()+speed*Math.cos(super.getDirection())*times);
        setY(getY()+speed*Math.sin(super.getDirection())*times);
        koinCounter--;
    }

    /**
     *
     * @return
     */
    public int getKoinCounter() {
        return koinCounter;
    }

    /**
     *
     * @param koinCounter
     */
    public void setKoinCounter(int koinCounter) {
        this.koinCounter = koinCounter;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guppy)) return false;
        Guppy g = (Guppy) o;
        return (super.getX() == g.getX() && super.getY() == g.getY()
                && super.getDirection() == g.getDirection()
                && super.getHunger() == g.getHunger()
                && super.getTahap() == g.getTahap()
                && super.getFoodCounter() == g.getFoodCounter())
                && getKoinCounter() == g.getKoinCounter();
=======
    super.setDirection(dir);
    this.x += speed * cos(super.getDirection()) * times;
    this.y += speed * sin(super.getDirection()) * times;
    koinCounter--;
  }
  
  /**
   * KoinCounter Getter.
   * @return koinCounter, number of Koin produced by Guppy.
   */
  public int getKoinCounter() {
    return koinCounter;
  }
  
  /**
   * KoinCounter Setter.
   * @param koinCounter new number of Koin produced by Guppy.
   */
  public void setKoinCounter(int koinCounter) {
    this.koinCounter = koinCounter;
  }
  
  /**
   * Check if Guppy equals Object O.
   * @param o Object that will be compared to Guppy.
   * @return true of O == Guppy.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Guppy)) {
      return false;
    } else {
      Guppy g = (Guppy) o;
      return super.getX() == g.getX() && super.getY() == g.getY()
        && super.getDirection() == g.getDirection()
        && super.getHunger() == g.getHunger()
        && super.getTahap() == g.getTahap()
        && super.getFoodCounter() == g.getFoodCounter()
        && getKoinCounter() == g.getKoinCounter();
>>>>>>> f6ea87621341fc31667a95523a91b66cb2658177
    }
  }
}