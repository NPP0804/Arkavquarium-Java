import java.util.Random;

@SuppressWarnings({"CheckStyle", "WeakerAccess"})
public abstract class Ikan extends BendaHidup {
  private static final int GUPPY_HUNGER = 4000;
  private static final int GUPPY_MIN_HUNGER_STATE = 2500;
  protected static final int MAX_MOVE_COUNTER = 5000;
  protected int hunger; //Nilai Kelaparan ikan
  protected int tahap; //Tahap perkembangan ikan
  protected int foodCounter; //Jumlah makanan yang pernah dimakan
  protected boolean mati; //Indikasi apakah ikan sudah mati atau tidak
  protected boolean hungerState;
  protected int moveCounter;
  
  /**
   * Class Constructor.
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
   * Class Constructor depends on two parameters, absis and ordinate.
   * @param x Absis of Ikan.
   * @param y Ordinate of Ikan.
   */
  public Ikan(double x, double y) {
    super(x,y);
    hunger = GUPPY_HUNGER;
    tahap = 1;
    foodCounter = 0;
    Random rand = new Random(System.currentTimeMillis());
    moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
    mati = false;
    hungerState = false;
  }
  
  /**
   * Hunger Getter.
   * @return hunger of Ikan.
   */
  public int getHunger() {
    return hunger;
  }
  
  /**
   * Tahap Getter.
   * @return tahap of Ikan (1-3).
   */
  public int getTahap() {
    return tahap;
  }
  
  /**
   * FoodCounter Getter.
   * @return foodCounter of Ikan.
   */
  public int getFoodCounter() {
    return foodCounter;
  }
  
  /**
   * Mati Getter.
   * @return mati of Ikan (true or false).
   */
  public boolean isMati() {
    return mati;
  }
  
  /**
   * HungerState Getter.
   * @return hungerState of Ikan.
   */
  public boolean getHungerState() {
    return hungerState;
  }
  
  /**
   * MoveCounter Getter.
   * @return moveCounter of Ikan.
   */
  public int getMoveCounter() {
    return moveCounter;
  }
  
  /**
   * Hunger Setter.
   * @param hunger degree of Ikan.
   */
  public void setHunger(int hunger) {
    this.hunger = hunger;
  }
  
  /**
   * Tahap Setter.
   * @param tahap of Ikan, limited from 1 - 3.
   */
  public void setTahap(int tahap) {
    this.tahap = tahap;
  }
  
  /**
   * FoodCounter Setter.
   * @param foodCounter of Ikan, counting how many food the Ikan had eaten.
   */
  public void setFoodCounter(int foodCounter) {
    this.foodCounter = foodCounter;
  }
  
  /**
   * Mati Setter.
   * @param mati of Ikan, set true if Ikan is already died.
   */
  public void setMati(boolean mati) {
    this.mati = mati;
  }
  
  /**
   * HungerState setter, set true if Ikan's hunger less than minimum.
   */
  public void setHungerState() {
    hunger--;
    hungerState = hunger < GUPPY_MIN_HUNGER_STATE;
  }
  
  /**
   * Controlling how Ikan moves. If new moveCounter < 0, it creates new direction.
   */
  public void decCounter() {
    moveCounter--;
    if (getMoveCounter() <= 0) {
      moveCounter = 0;
      Random rand = new Random(System.currentTimeMillis());
      moveCounter = rand.nextInt(MAX_MOVE_COUNTER);
    }
  }
}
