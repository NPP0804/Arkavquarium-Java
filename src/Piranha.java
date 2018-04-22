import java.util.Random;

import static java.lang.Math.*;

public class Piranha extends Ikan {
    public static final int PIRANHA_FOOD_COUNTER_NAIK_TAHAP = 3;
    public static final int PIRANHA_HUNGER = 4000;
    private static final int speed = 20; //bisa diubah value nya

    /**
     *
     */
    public Piranha() {
    }

    /**
     *
     * @param X
     * @param Y
     */
    public Piranha(double X, double Y) {
        super(X, Y);
    }

    /**
     * Mengubah salah satu isi makanan edible pada list menjadi non edible
     * @param times
     */
    public void move(double times) { // gerak arah random selama time
        super.decCounter();
        if(moveCounter == 1){
            Random rand = new Random(System.currentTimeMillis());
            moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
            for (int i = 0; i < x; i++){
                direction = Math.toRadians(rand.nextInt(360));
            }
        }
        x += speed*cos(super.getDirection())*times;
        y += speed*sin(super.getDirection())*times;

    }

    /**
     *
     * @param x
     * @param y
     * @param times
     */
    public void moveTowardsTarget(double x, double y, double times) { //gerak arah x selama time
        super.setDirection(atan2(y-this.y, x-this.x));
        this.x += speed*cos(super.getDirection())*times;
        this.y += speed*sin(super.getDirection())*times;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piranha)) return false;
        Piranha p = (Piranha) o;
        return ((super.getX() == p.getX()) && (super.getY() == p.getY())
                && (super.getDirection() == p.getDirection())
                && (super.getHunger() == p.getHunger())
                && (super.getTahap() == p.getTahap())
                && (super.getFoodCounter() == p.getFoodCounter()));
    }

}
