public class Siput extends BendaHidup {
  private static final int speed = 10;

  /**
   * Default constructor.
   */
  public Siput() {
    super(Akuarium.SCREEN_WIDTH / 2, Akuarium.SCREEN_HEIGHT - 50);
  }

  /**
   * Override move.
   */
  @Override
  public void move(double x){

  }

  /**
   * Override moveTowardsTarget (Kecepatan berbeda dari bendaHidup lain).
   */
  @Override
  public void moveTowardsTarget(double x, double y, double t) {
    if (x > this.x) {
      this.x += t * speed;
      this.setDirection(0);
    } else if (x < this.x) {
      this.x -= t * speed;
      this.setDirection(Math.PI);
    }
  }
}