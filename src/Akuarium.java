public class Akuarium {
  private int ukuranX; //Menyimpan ukuran panjang Akuarium
  private int ukuranY; //Menyimpan ukuran lebar Akuarium
  private List<Piranha> listPiranha;
  private List<Guppy> listGuppy;
  private List<Koin> listKoin;
  private List<Makanan> listMakanan;
  private Siput siput;

  public static final int SCREEN_WIDTH = 640;
  public static final int SCREEN_HEIGHT = 480;

    /**
     * Class default constructor.
     */
  public Akuarium() {
    ukuranX = SCREEN_WIDTH;
    ukuranY = SCREEN_HEIGHT;
    listPiranha = new List<>();
    listGuppy = new List<>();
    listKoin = new List<>();
    listMakanan = new List<>();
    siput = new Siput();
  }

    /**
     * Class constructor with two parameters.
     * @param x absis screen size
     * @param y ordinate screen size
     */
  public Akuarium(int x, int y) {
    ukuranX = x;
    ukuranY = y;
    listPiranha = new List<>();
    listGuppy = new List<>();
    listKoin = new List<>();
    listMakanan = new List<>();
    siput = new Siput();
  }

    /**
     * Getter for absis screen size.
     * @return ukuranX instance of integer
     */
  public int getX() {
    return ukuranX;
  }

    /**
     * Getter for ordinate screen size.
     * @return ukuranY instance of integer.
     */
  public int getY() {
    return ukuranY;
  }

    /**
     * Getter for list of makanan.
     * @return listMakanan instance of List of Makanan.
     */
  public List<Makanan> getListMakanan() {
    return listMakanan;
  }

    /**
     * Getter for list of guppy.
     * @return listGuppy instance of List of Guppy.
     */
  public List<Guppy> getListGuppy() {
    return listGuppy;
  }

    /**
     * Getter for list of piranha.
     * @return listPiranha instance of List of Piranha.
     */
  public List<Piranha> getListPiranha() {
    return listPiranha;
  }

    /**
     * Getter for list of koin.
     * @return listKoin instance of List of Koin.
     */
  public List<Koin> getListKoin() {
    return listKoin;
  }

    /**
     * Getter for siput.
     * @return s instance of Siput
     */
  public Siput getSiput() {
    return siput;
  }

    /**
     * Search whether koin is available or not.
     * @return availability koin in Akuarium, instance of boolean
     */
  public boolean koinAvailable() {
    return !listKoin.isEmpty();
  }

    /**
     * Search whether guppy is available or not.
     * @return availability guppy in Akuarium, instance of boolean
     */
  public boolean guppyAvailable() {
    return !listGuppy.isEmpty();
  }

    /**
     * Search whether makanan is available or not.
     * @return availability makanan in Akuarium, instance of boolean
     */
  public boolean makananAvailable() {
    return !listMakanan.isEmpty();
  }

    /**
     * Search nearest Makanan instance from certain coordinate.
     * @param x absis of a coordinate
     * @param y ordinate of a coordinate
     * @return object instance of Makanan which has nearest position to coordinate x,y
     */
  public Makanan searchMakanan(double x, double y) {
    Element<Makanan> temp = listMakanan.first;
    Makanan m = temp.info;
    double jarak = Math.sqrt(Math.pow(x - m.getX(), 2) + Math.pow(y - m.getY(), 2));

    while (temp.next != null) {
      temp = temp.next;
      if (jarak > Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2))) {
        jarak = Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2));
        m = temp.info;
      }
    }
    return m;
  }

    /**
     * Search nearest Guppy instance from certain coordinate.
     * @param x absis of a coordinate
     * @param y ordinate of a coordinate
     * @return object instance of Guppy which has nearest position to coordinate x,y
     */
  public Guppy searchGuppy(double x, double y) {
    Element<Guppy> temp = listGuppy.first;
    double jarak = Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2));
    Guppy g = temp.info;
    while (temp.next != null) {
      temp = temp.next;
      if (jarak > Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2))){
        jarak = Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2));
        g = temp.info;
      }
    }
    return g;
  }

    /**
     * Search nearest Koin instance from certain coordinate.
     * @param x absis of a coordinate
     * @param y ordinate of a coordinate
     * @return object instance of Koin which has nearest position to coordinate x,y
     */
  public Koin searchKoin(double x, double y) {
    Element<Koin> temp = listKoin.first;
    double jarak = Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2));
    Koin k = temp.info;
    while (temp.next != null) {
      temp = temp.next;
      if (jarak > Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2))) {
        jarak = Math.sqrt(Math.pow(x - temp.info.getX(), 2) + Math.pow(y - temp.info.getY(), 2));
        k = temp.info;
      }
    }
    return k;
  }

    /**
     * Method for updating every object state in Akuarium.
     */
  public void updateAkuarium() {
    updateMakanan();
    updatePiranha();
    updateGuppy();
  }

    /**
     * Method for updating every instance of makanan state in Akuarium.
     */
  public void updateMakanan() {
    if (!listMakanan.isEmpty()) {
      Element<Makanan> temp = listMakanan.first;
      do {
        Makanan m = temp.info;
        temp = temp.next;
        if (m.getY() >= (SCREEN_HEIGHT - 50) && !listMakanan.isEmpty()) {
          listMakanan.remove(m);
        }

      } while (temp != null);
    }
  }

    /**
     * Method for updating every instance of guppy state in Akuarium.
     */

  public void updateGuppy() {
    if (!listGuppy.isEmpty()) {
      Element<Guppy> temp = listGuppy.first;
      do {
        //update status guppy
        Guppy g = temp.info;
        temp = temp.next;
        // cout << g.getHungerState();
        g.setHungerState();
        g.decCounter();
        if (g.getFoodCounter() == Guppy.GUPPY_FOOD_COUNTER_NAIK_TAHAP) { //bisa diatur
          if (g.getTahap() < 3) {
            g.setTahap(g.getTahap()+1);
            g.setFoodCounter(0);
          }
        }
        if (g.getHunger() <= 0) {
          g.setMati(true);
        }

                //update eksistensi guppy
        if (g.isMati()) {
          listGuppy.remove(g);
        }
      } while (temp != null);
    }
  }

    /**
     * Method for updating every instance of piranha state in Akuarium.
     */
  public void updatePiranha() {
    if (!listPiranha.isEmpty()) {
      Element<Piranha> temp = listPiranha.first;
      do {
        Piranha p = temp.info;
        temp = temp.next;
        p.setHungerState();
        p.decCounter();
        if (p.getFoodCounter() == Piranha.PIRANHA_FOOD_COUNTER_NAIK_TAHAP) { //bisa diatur
          if (p.getTahap() < 3) {
            p.setFoodCounter(p.getFoodCounter() + 1);
          }
        }
        if (p.getHunger() <= 0) {
          p.setMati(true);
        }

        if (p.isMati()) {
          listPiranha.remove(p);
        }
      } while (temp != null);
    }
  }
}