import java.lang.Math;
import java.util.Random;

public class Guppy extends Ikan {
    public static final int GUPPY_FOOD_COUNTER_NAIK_TAHAP = 5;
    public static final int GUPPY_HUNGER = 4000;
    public static final int PERIODE_KOIN = 2000;
    private static final int speed = 20; //bisa diubah value nya
    protected int koinCounter;


    /**
     * Constructor
     */
    public Guppy() {
        super();
        koinCounter = PERIODE_KOIN;
    }

    /**
     *
     * @param X
     * @param Y
     */
    public Guppy(double X, double Y) {
        super(X,Y);
        koinCounter = PERIODE_KOIN;
    }

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
        x += speed*Math.cos(super.getDirection())*times;
        y += speed*Math.sin(super.getDirection())*times;
        koinCounter--;
    }

    /**
     *
     * @param x
     * @param y
     * @param times
     */
    public void moveTowardsTarget(double x, double y, double times) { //gerak arah x selama time
        super.setDirection(Math.atan2(y-this.y, x-this.x));
        this.x += speed*Math.cos(super.getDirection())*times;
        this.y += speed*Math.sin(super.getDirection())*times;
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
    }
}
