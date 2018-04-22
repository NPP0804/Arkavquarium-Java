import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller extends JPanel {
    private Akuarium tank;
    private int uang;
    private int levelTelur;
    private Map<String, BufferedImage> images;

    public static final double TIMESTAMP_IKAN = 0.02;
    public static final double TIMESTAMP_MAKANAN = 0.01;
    public static final double TIMESTAMP_SIPUT = 0.02;
    public static final double TIMESTAMP_KOIN = 0.02;
    public static final int PERIODE_KOIN = 2000;
    public static final int NILAI_KOIN_TAHAP1 = 100;
    public static final int NILAI_KOIN_TAHAP2 = 200;
    public static final int NILAI_KOIN_TAHAP3 = 300;
    public static final int HARGA_GUPPY = 100;
    public static final int HARGA_PIRANHA = 150;
    public static final int HARGA_MAKANAN = 50;
    public static final int HARGA_TELUR1 = 800;
    public static final int HARGA_TELUR2 = 1000;
    public static final int HARGA_TELUR3 = 1500;

    private JFrame jFrame;

    public Controller(){
        this.tank = new Akuarium();
        uang = 800;
        levelTelur = 0;
        images = new HashMap<>();

        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(Akuarium.SCREEN_WIDTH, Akuarium.SCREEN_HEIGHT);
        jFrame.setVisible(true);
        jFrame.setContentPane(this);
    }
    public void processAkuarium(){
        tank.updateAkuarium();
        processPiranha();
        processGuppy();
        processMakanan();
        processSiput();
        processKoin();
    }

    public Akuarium getAkuarium(){
        return tank;
    }

    public void processGuppy(){
        if(!tank.getListGuppy().isEmpty()){
            Element<Guppy> temp = tank.getListGuppy().first;
            do{
                Guppy g = temp.info;
                temp = temp.next;
                //mencari makan untuk yang sudah lapar
                if(g.getHungerState() && tank.makananAvailable()){
                    Makanan m = tank.searchMakanan(g.getX(), g.getY());
                    if(Math.abs(m.getX()-g.getX()) < 1 && Math.abs(m.getY()-g.getY()) < 1){
                        tank.getListMakanan().remove(m);
                        g.setFoodCounter(g.getFoodCounter()+1);
                        g.setHunger(Guppy.GUPPY_HUNGER);
                    }
                    else{
                        g.moveTowardsTarget(m.getX(), m.getY(),TIMESTAMP_IKAN);
                    }
                }else{
                    //bergerak random jika tidak lapar
                    g.move(TIMESTAMP_IKAN);
                }

                //jika nabrak tembok
                if(g.getX() <= 0 || g.getY() <= 0 || g.getX() >= Akuarium.SCREEN_WIDTH || g.getY() >= Akuarium.SCREEN_HEIGHT-50){
                    if (g.getDirection()>=Math.PI) {
                        g.setDirection(g.getDirection()-Math.PI);
                    } else {
                        g.setDirection(g.getDirection()+Math.PI);
                    }
                }

                // mengeluarkan koin bagi yang siap mengeluarkan koin
                if(g.getKoinCounter() <= 0){
                    switch(g.getTahap()){
                        case 1: addKoin(g.getX(), g.getY(), g.getTahap()*NILAI_KOIN_TAHAP1); break;
                        case 2: addKoin(g.getX(), g.getY(), g.getTahap()*NILAI_KOIN_TAHAP2); break;
                        case 3: addKoin(g.getX(), g.getY(), g.getTahap()*NILAI_KOIN_TAHAP3); break;
                    }
                    g.setKoinCounter(PERIODE_KOIN);
                }
            } while(temp != null);
        }
    }
    public void processPiranha(){
        if(!tank.getListPiranha().isEmpty()){
            Element<Piranha> temp = tank.getListPiranha().first;
            do{
                Piranha p = temp.info;
                temp = temp.next;
                //mencari makan untuk yang sudah lapar
                if(p.getHungerState() && tank.guppyAvailable()){
                    Guppy g = tank.searchGuppy(p.getX(), p.getY());
                    if(Math.abs(g.getX()-p.getX()) < 2 && Math.abs(g.getY()-p.getY()) < 2){
                        g.setMati(true);
                        p.setFoodCounter(p.getFoodCounter()+1);
                        p.setHunger(Piranha.PIRANHA_HUNGER);
                        addKoin(p.getX(), p.getY(), HARGA_GUPPY*(g.getTahap()+1));
                    }
                    else{
                        p.moveTowardsTarget(g.getX(), g.getY(),TIMESTAMP_IKAN);
                    }
                }else{
                    //bergerak random jika tidak lapar
                    p.move(TIMESTAMP_IKAN);
                }

                //jika nabrak tembok
                if(p.getX() <= 0 || p.getY() <= 0 || p.getX() >= Akuarium.SCREEN_WIDTH || p.getY() >= Akuarium.SCREEN_HEIGHT-50){
                    if (p.getDirection()>=Math.PI) {
                        p.setDirection(p.getDirection()-Math.PI);
                    } else {
                        p.setDirection(p.getDirection()+Math.PI);
                    }
                }
            } while(temp != null);
        }
    }
    public void processMakanan(){
        if (!tank.getListMakanan().isEmpty()) {
            Element<Makanan> temp = tank.getListMakanan().first;
            do {
                temp.info.move(TIMESTAMP_MAKANAN);
                temp = temp.next;
            } while (temp != null);
        }
    }
    public void processSiput(){
        if(tank.koinAvailable()){
            Koin k = tank.searchKoin(tank.getSiput().getX(), tank.getSiput().getY());
            if(Math.abs(k.getX()-tank.getSiput().getX()) < 10){
                if(k.getY()-tank.getSiput().getY() >= -5 && k.getY() < Akuarium.SCREEN_HEIGHT){
                    uang += k.getNilai();
                    tank.getListKoin().remove(k);
                }
                else{
                    tank.getSiput().move(0);
                }
            }
            else{
                tank.getSiput().moveTowardsTarget(k.getX(), k.getY(), TIMESTAMP_SIPUT);
            }
        }
        else {
            tank.getSiput().move(0);
        }
    }
    public void processKoin(){
        if (!tank.getListKoin().isEmpty()) {
            Element<Koin> temp = tank.getListKoin().first;
            do {
                if(temp.info.getY() >= Akuarium.SCREEN_HEIGHT-50){
                    temp.info.move(0);
                }
                else {
                    temp.info.move(TIMESTAMP_KOIN);
                }
                temp = temp.next;
            } while (temp != null);
        }
    }


    public void addKoin(double x, double y, int nilai){
        Koin k = new Koin(x,y,nilai);
        tank.getListKoin().add(k);
    }

    public void addGuppy(){
        Guppy g = new Guppy();
        tank.getListGuppy().add(g);
        uang -= HARGA_GUPPY;
    }

    public void addPiranha(){
        Piranha p = new Piranha();
        tank.getListPiranha().add(p);
        uang -= HARGA_PIRANHA;
    }

    public void addMakanan(double x){
        Makanan m = new Makanan(x,0);
        tank.getListMakanan().add(m);
        uang -= HARGA_MAKANAN;
    }

    public void generateAkuarium() {
        this.addGuppy();
        this.addGuppy();

        boolean running = true;

        while (running) {
            processAkuarium();
            jFrame.invalidate();
            jFrame.validate();
            jFrame.repaint();
        }

    }

    private BufferedImage readImage(String path) {
        BufferedImage choosenImage = images.get(path);
        if (choosenImage == null) {
            try {
                choosenImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                choosenImage = null;
            }
            images.put(path, choosenImage);
        }
        return choosenImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.setColor(Color.WHITE);

        //gambar background
        g.drawImage(readImage("Images/background.png"), 0, 0, null);

        //gambar siput
        g.drawImage(readImage("Images/siputkiri.png"), (int)Math.floor(tank.getSiput().getX()), (int)Math.floor(tank.getSiput().getY()), null);

        //gambar ikan
        if (!tank.getListGuppy().isEmpty()) {
            Element<Guppy> currentGuppy = tank.getListGuppy().getFirst();
            while (currentGuppy != null) {
                g.drawImage(readImage("Images/guppy1kiri.png"), (int)Math.floor(currentGuppy.getInfo().getX()), (int)Math.floor(currentGuppy.getInfo().getY()), null);
                currentGuppy = currentGuppy.getNext();
            }
        }

        //gambar piranha
        if (!tank.getListPiranha().isEmpty()) {
            Element<Piranha> currentPiranha = tank.getListPiranha().getFirst();
            while (currentPiranha != null) {
                g.drawImage(readImage("Images/piranhakiri.png"), (int)Math.floor(currentPiranha.getInfo().getX()), (int)Math.floor(currentPiranha.getInfo().getY()), null);
                currentPiranha = currentPiranha.getNext();
            }
        }

        //gambar makanan
        if (!tank.getListMakanan().isEmpty()) {
            Element<Makanan> currentMakanan = tank.getListMakanan().getFirst();
            while (currentMakanan != null) {
                g.drawImage(readImage("Images/upil.png"), (int)Math.floor(currentMakanan.getInfo().getX()), (int)Math.floor(currentMakanan.getInfo().getY()), null);
                currentMakanan = currentMakanan.getNext();
            }
        }

        //gambar koin
        if (!tank.getListKoin().isEmpty()) {
            System.out.println("ASO");
            Element<Koin> currentKoin = tank.getListKoin().getFirst();
            while (currentKoin != null) {
                g.drawImage(readImage("Images/koin1.png"), (int)Math.floor(currentKoin.getInfo().getX()), (int)Math.floor(currentKoin.getInfo().getY()), null);
                currentKoin = currentKoin.getNext();
            }
        }
    }
}