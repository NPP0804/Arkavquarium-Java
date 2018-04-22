import java.util.Random;

public abstract class BendaHidup implements Entitas {
//Child class dari kelas entitas
//Berisi arah hadapan dan method untuk bergerak benda hidup

    protected double x; //Absis entitas
    protected double y; //Ordinat entitas
    protected double direction; //atribut yang menyimpan kemana BendaHidup menghadap

    /**
     * Default constructor of BendaHidup
     */
    public BendaHidup() {
        Random rand = new Random(System.currentTimeMillis());
        x = rand.nextInt(Akuarium.SCREEN_WIDTH);
        y = rand.nextInt(Akuarium.SCREEN_HEIGHT);
        direction = 0;
    }

    /**
     * Constructor of BendaHidup
     * @param x double, absis of BendaHidup
     * @param y double, ordinate of BendaHidup
     */
    public BendaHidup(double x, double y) {
        this.x = x;
        this.y = y;
        this.direction = 0;
    }

    /**
     * Getter X
     * @return absis x of BendaHidup
     */
    public double getX() {
        return x;
    }

    /**
     * Setter X
     * @param x set absis of BendaHidup with x (double)
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter Y
     * @return ordinate y of BendaHidup
     */
    public double getY() {
        return y;
    }

    /**
     * Setter Y
     * @param y set ordinate of BendaHidup with y (double)
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Getter direction
     * @return direction of BendaHidup
     */
    public double getDirection() {
        return direction;
    }

    /**
     * Setter direction
     * @param direction set direction of BendaHidup with direction (double)
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Procedure for BendaHidup to move randomly
     * @param time (double)
     */
    public abstract void move(double time);

    /**
     * Procedure for BendaHidup to move towards (x,y) as far as it can
     * @param x absis of BendaHidup's target
     * @param y ordinate of BendaHidup's target
     * @param time
     */
    public abstract void moveTowardsTarget(double x, double y, double time);

}
