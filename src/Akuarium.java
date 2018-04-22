public class Akuarium {
    private int ukuranX; //Menyimpan ukuran panjang akuarium
    private int ukuranY; //Menyimpan ukuran lebar akuarium
    private List<Piranha> listPiranha;
    private List<Guppy> listGuppy;
    private List<Koin> listKoin;
    private List<Makanan> listMakanan;
    private Siput s;

    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 480;


    public Akuarium(){
        ukuranX = SCREEN_WIDTH;
        ukuranY = SCREEN_HEIGHT;
        listPiranha = new List<>();
        listGuppy = new List<>();
        listKoin = new List<>();
        listMakanan = new List<>();
        s = new Siput();

    }

    public Akuarium(int x, int y){
        ukuranX = x;
        ukuranY = y;
        listPiranha = new List<>();
        listGuppy = new List<>();
        listKoin = new List<>();
        listMakanan = new List<>();
        s = new Siput();
    }

    //Getter
    public int getX(){
        return ukuranX;
    }
    public int getY(){
        return ukuranY;
    }
    //Mengambil suatu list dari suatu petak dari matriks[x,y]
    public List<Makanan> getListMakanan(){
        return listMakanan;
    }
    public List<Guppy> getListGuppy(){
        return listGuppy;
    }
    public List<Piranha> getListPiranha(){
        return listPiranha;
    }
    public List<Koin> getListKoin(){
        return listKoin;
    }
    public Siput getSiput(){
        return s;
    }

    //Searcher
    public boolean koinAvailable(){
        return !listKoin.isEmpty();
    }
    public boolean guppyAvailable(){
        return !listGuppy.isEmpty();
    }
    public boolean makananAvailable(){
        return !listMakanan.isEmpty();
    }

    public Makanan searchMakanan(double x, double y){
        Element<Makanan> temp = listMakanan.first;
        Makanan m = temp.info;
        double jarak = Math.sqrt(Math.pow(x-m.getX(), 2) + Math.pow(y-m.getY(), 2));

        while (temp.next != null) {
            temp = temp.next;
            if(jarak > Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2))){
                jarak = Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2));
                m = temp.info;
            }
        }
        return m;
    } //Mengembalikan makanan terdekat dari x y yang terdapat makanan

    public Guppy searchGuppy(double x, double y){
        Element<Guppy> temp = listGuppy.first;
        double jarak = Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2));
        Guppy g = temp.info;
        while(temp.next != null){
            temp = temp.next;
            if(jarak > Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2))){
                jarak = Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2));
                g = temp.info;
            }
        }
        return g;
    }  //Mengembalikan guppy terdekat dari x y yang terdapat Guppy

    public Koin searchKoin(double x, double y){
        Element<Koin> temp = listKoin.first;
        double jarak = Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2));
        Koin k = temp.info;
        while(temp.next != null) {
            temp = temp.next;
            if(jarak > Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2))){
                jarak = Math.sqrt(Math.pow(x-temp.info.getX(), 2) + Math.pow(y-temp.info.getY(), 2));
                k = temp.info;
            }
        }
        return k;
    } //Mengembalikan koin terdekat dari x y yang terdapat Koin

    public void updateAkuarium(){
        updateMakanan();
        updatePiranha();
        updateGuppy();
    }

    public void updateMakanan(){
        if(!listMakanan.isEmpty()){
            Element<Makanan> temp = listMakanan.first;
            do{
                Makanan m = temp.info;
                temp = temp.next;
                if(m.getY() >= (SCREEN_HEIGHT-50) && !listMakanan.isEmpty()){
                    listMakanan.remove(m);
                }

            } while(temp != null);
        }
    }
    public void updatePiranha(){
        if(!listGuppy.isEmpty()){
            Element<Guppy> temp = listGuppy.first;
            do{
                //update status guppy
                Guppy g = temp.info;
                temp = temp.next;
                // cout << g.getHungerState();
                g.setHungerState();
                g.decCounter();
                if(g.getFoodCounter() == Guppy.GUPPY_FOOD_COUNTER_NAIK_TAHAP){ //bisa diatur
                    if(g.getTahap() < 3){
                        g.setTahap(g.getTahap()+1);
                        g.setFoodCounter(0);
                    }
                }
                if(g.getHunger() <= 0){
                    g.setMati(true);
                }

                //update eksistensi guppy
                if(g.isMati()){
                    listGuppy.remove(g);
                }
            } while(temp != null);
        }
    }
    public void updateGuppy(){
        if(!listPiranha.isEmpty()){
            Element<Piranha> temp = listPiranha.first;
            do{
                Piranha p = temp.info;
                temp = temp.next;
                p.setHungerState();
                p.decCounter();
                if(p.getFoodCounter() == Piranha.PIRANHA_FOOD_COUNTER_NAIK_TAHAP){ //bisa diatur
                    if(p.getTahap() < 3){
                        p.setFoodCounter(p.getFoodCounter()+1);
                    }
                }
                if(p.getHunger() <= 0){
                    p.setMati(true);
                }

                if(p.isMati()){
                    listPiranha.remove(p);
                }
            } while(temp != null);
        }
    }
}