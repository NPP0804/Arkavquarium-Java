@SuppressWarnings({"WeakerAccess", "CheckStyle"})
public class BendaMati implements Entitas {
  protected final double x;
  protected double y;
  protected static final double speed = 10;

  /**
   * Default constructor.
   */
  public BendaMati() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Default constructor.
   * @param x Define absis of BendaMati.
   * @param y Define ordinate of BendaMati.
   */
  public BendaMati(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter X.
   * @return value of absis x.
   */
  public double getX() {
    return x;
  }

  /**
   * Getter Y.
   * @return value of ordinate y.
   */
  public double getY() {
    return y;
  }

  /**
   * Setter Y.
   * @param y set BendaMati ordinate = y.
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Move bendaMati to random direction.
   * @param time amount of distance to move.
   */
  public void move(double time) {
    setY(getY() + speed * time);
  }

}
