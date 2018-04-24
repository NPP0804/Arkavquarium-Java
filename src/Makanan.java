public class Makanan extends BendaMati {
  private boolean edible;

  /**
   * Default constructor.
   */
  public Makanan(){

  }

  /**
   * User Defined constructor.
   * @param x Define absis of Makanan.
   * @param y Define ordinate of Makanan.
   */
  public Makanan(double x, double y) {
    super(x,y);
    edible = true;
  }

  /**
   * Getter edible.
   * Return status apakah bisa dimakan.
   */
  public boolean isEdible() {
    return edible;
  }

  /**
   * Setter edible.
   * Set status apakah bisa dimakan.
   */
  public void setEdible(boolean b) {
    edible = b;
  }

  /**
   * Implementasi Operator==.
   */
  public boolean isSama(Makanan m) {
    return (getX() == m.getX() && getY() == m.getY());
  }

  /**
   * Implementasi Operator!=.
   */
  public boolean isBeda(Makanan m) {
    return (getX() != m.getX() || getY() != m.getY());
  }
}
