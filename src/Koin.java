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
   * Implementing Operator==.
   * @param k koin that need to be compared.
   * @return true if k equals Koin
   */
  public boolean isSama(Koin k) {
    return (getX() == k.getX() && getY() == k.getY() && getNilai() == k.getNilai());
  }
  
  /**
   * Implementing Operator!=.
   * @param k koin that need to be compared.
   * @return true if k not equals Koin
   */
  public boolean isBeda(Koin k) {
    return (getX() != k.getX() || getY() != k.getY() || getNilai() != k.getNilai());
  }
}
