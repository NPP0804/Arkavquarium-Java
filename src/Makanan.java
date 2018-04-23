public class Makanan extends BendaMati {
  private boolean edible;

  public Makanan(){

  }

  public Makanan(double x, double y) {
    super(x,y);
    edible = true;
  }

  public boolean isEdible() {
    return edible;
  }

  public void setEdible(boolean b) {
    edible = b;
  }

  //Implementasi Operator==
  public boolean isSama(Makanan m) {
    return (getX() == m.getX() && getY() == m.getY());
  }

  //Implementasi Operator!=
  public boolean isBeda(Makanan m) {
    return (getX() != m.getX() || getY() != m.getY());
  }
}
