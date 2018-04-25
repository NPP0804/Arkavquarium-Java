public class Makanan extends BendaMati {
  private boolean edible;

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
   * @return true if Makanan is edible.
   */
  public boolean isEdible() {
    return edible;
  }

  /**
   * Setter edible.
   * @param b condition of edible.
   */
  public void setEdible(boolean b) {
    edible = b;
  }

  /**
   * Implementing Operator==.
   * @param m object that need to compared.
   * @return true if Makanan == m.
   */
  public boolean isSama(Makanan m) {
    return (getX() == m.getX() && getY() == m.getY());
  }
  
  /**
   * Implementing Operator!=.
   * @param m object that need to compared.
   * @return true if Makanan != m.
   */
  public boolean isBeda(Makanan m) {
    return (getX() != m.getX() || getY() != m.getY());
  }
}
