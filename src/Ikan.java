import java.util.Random;

public abstract class Ikan extends BendaHidup {
    protected static final int GUPPY_HUNGER = 4000;
    protected static final int GUPPY_MIN_HUNGER_STATE = 2500;
    protected static final int MAX_MOVE_COUNTER = 5000;
    protected int hunger; //Nilai Kelaparan ikan
    protected int tahap; //Tahap perkembangan ikan
    protected int foodCounter; //Jumlah makanan yang pernah dimakan
    protected boolean mati; //Indikasi apakah ikan sudah mati atau tidak
    protected boolean hungerState;
    protected int moveCounter;

    protected Random rand = new Random();

    /**
     *
     */
    public Ikan() {
        super();
        hunger = GUPPY_HUNGER;
        tahap = 1;
        foodCounter = 0;
        Random rand = new Random(System.currentTimeMillis());
        moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
        mati = false;
        hungerState = false;
    }

    /**
     *
     * @param X
     * @param Y
     */
    public Ikan(double X, double Y) {
        super(X,Y);
        hunger = GUPPY_HUNGER;
        tahap = 1;
        foodCounter = 0;

        Random rand = new Random(System.currentTimeMillis());
        moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
        mati = false;
        hungerState = false;
    }

    /**
     *
     * @return
     */
    public int getHunger() {
        return hunger;
    }

    /**
     *
     * @return
     */
    public int getTahap() {
        return tahap;
    }

    /**
     *
     * @return
     */
    public int getFoodCounter() {
        return foodCounter;
    }

    /**
     *
     * @return
     */
    public boolean isMati() {
        return mati;
    }

    /**
     *
     * @return
     */
    public boolean getHungerState() {
        return hungerState;
    }

    /**
     *
     * @return
     */
    public int getMoveCounter() {
        return moveCounter;
    }

    /**
     *
     * @param hunger
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     *
     * @param tahap
     */
    public void setTahap(int tahap) {
        this.tahap = tahap;
    }

    /**
     *
     * @param foodCounter
     */
    public void setFoodCounter(int foodCounter) {
        this.foodCounter = foodCounter;
    }

    /**
     *
     * @param mati
     */
    public void setMati(boolean mati) {
        this.mati = mati;
    }

    /**
     *
     */
    public void setHungerState() {
        hunger--;
        if(hunger < GUPPY_MIN_HUNGER_STATE){ //bisa disesuaikan angka nya
            hungerState = true;
        }
        else{
            hungerState = false;
        }
    }

    /**
     *
     */
    public void decCounter() {
        moveCounter--;
        if(moveCounter <= 0){
            moveCounter = 0;
            Random rand = new Random(System.currentTimeMillis());
            moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
        }
    }
}
