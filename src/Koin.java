public class Koin extends BendaMati {
  private int nilai;

  /**
   * User Defined constructor.
   * @param x Define absis of Koin.
   * @param y Define ordinate of Koin.
   */
  public Koin(double x, double y) {
    super(x,y);
    nilai = 0;
  }

  /**
   * User Defined constructor.
   * @param x Define absis of Koin.
   * @param y Define ordinate of Koin.
   * @param n Define value(nilai) of Koin.
   */
  public Koin(double x, double y, int n) {
    super(x,y);
    nilai = n;
  }

  /**
   * Getter nilai.
   * @return value of nilai.
   */
  public int getNilai() {
    return nilai;
  }

  /**
   * Setter Y.
   * @param n set Koin nilai = n.
   */
  public void setNilai(int n) {
    nilai = n;
  }

  /**
   * Implementasi Operator==.
   */
  public boolean isSama(Koin k) {
    return (getX() == k.getX() && getY() == k.getY() && getNilai() == k.getNilai());
  }

  /**
   * Implementasi Operator!=.
   */
  public boolean isBeda(Koin k) {
    return (getX() != k.getX() || getY() != k.getY() || getNilai() != k.getNilai());
  }
}
