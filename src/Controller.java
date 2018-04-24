import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.floor;

public class Controller extends JPanel {
  private Akuarium tank;
  private int uang;
  private int levelTelur;
  private int winningState;
  private Map<String, BufferedImage> images;
  private final long fps;

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

  public Controller() {
    this.tank = new Akuarium();
      uang = 800;
      levelTelur = 0;
      images = new HashMap<>();
      winningState = -2;
      fps = 1000000000L/(256+128);

      initiateJFrame();
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
          }else{
            g.moveTowardsTarget(m.getX(), m.getY(),TIMESTAMP_IKAN);
          }
        }else{
          //bergerak random jika tidak lapar
          g.move(TIMESTAMP_IKAN);
        }

        //jika nabrak tembok
        if(g.getX() <= 0 || g.getY() <= 0 || g.getX() >= Akuarium.SCREEN_WIDTH || g.getY() >= Akuarium.SCREEN_HEIGHT-50){
          if (g.getDirection()>=PI) {
            g.setDirection(g.getDirection()-PI);
          } else {
            g.setDirection(g.getDirection()+PI);
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
          }else{
            p.moveTowardsTarget(g.getX(), g.getY(),TIMESTAMP_IKAN);
          }
        }else{
          //bergerak random jika tidak lapar
          p.move(TIMESTAMP_IKAN);
        }

        //jika nabrak tembok
        if(p.getX() <= 0 || p.getY() <= 0 || p.getX() >= Akuarium.SCREEN_WIDTH || p.getY() >= Akuarium.SCREEN_HEIGHT-50){
          if (p.getDirection()>=PI) {
            p.setDirection(p.getDirection()-PI);
          } else {
            p.setDirection(p.getDirection()+PI);
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
        }else{
          tank.getSiput().move(0);
        }
      }else{
        tank.getSiput().moveTowardsTarget(k.getX(), k.getY(), TIMESTAMP_SIPUT);
      }
    }else{
      tank.getSiput().move(0);
    }
  }

  public void processKoin(){
    if (!tank.getListKoin().isEmpty()) {
      Element<Koin> temp = tank.getListKoin().first;
      do {
        if(temp.info.getY() >= Akuarium.SCREEN_HEIGHT-50){
          temp.info.move(0);
        }else{
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

        long lastFrameStart = System.nanoTime();
        long now;

        boolean running = true;

        while (winningState == -2) {
          jFrame.invalidate();
          jFrame.validate();
          jFrame.repaint();
        }

        while (winningState>=-1 && winningState<=1) {
            now = System.nanoTime();
            if ((now - lastFrameStart) >= fps) {
                processAkuarium();
                jFrame.invalidate();
                jFrame.validate();
                jFrame.repaint();
                lastFrameStart = now;

                if (getAkuarium().getListGuppy().isEmpty() &&
                        getAkuarium().getListPiranha().isEmpty() && uang<HARGA_GUPPY && winningState==0) {
                    winningState = -1;
                } else if (levelTelur == 3 && winningState==0) {
                    winningState = 1;
                }
            }
        }

    }

    private BufferedImage readImage(String path) {
        BufferedImage choosenImage = images.get(path);
        if (choosenImage == null) {
            try {
                choosenImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                System.out.println("Image Not Found!");
            }
            images.put(path, choosenImage);
        }
        return choosenImage;
    }

    public void initiateJFrame() {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(Akuarium.SCREEN_WIDTH, Akuarium.SCREEN_HEIGHT);
        jFrame.setVisible(true);
        jFrame.setContentPane(this);

        jFrame.getContentPane().setFocusable(true);
        jFrame.getContentPane().requestFocusInWindow();

        jFrame.getContentPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_P) {
                    if (uang >= HARGA_PIRANHA) {
                        addPiranha();
                        }
                } else if (keyCode == KeyEvent.VK_N) {
                    if (uang >= HARGA_GUPPY) {
                        addGuppy();
                    }
                } else if (keyCode == KeyEvent.VK_M) {
                    if (uang >= HARGA_MAKANAN) {
                        addMakanan(Akuarium.SCREEN_WIDTH/2);
                    }
                } else if (keyCode == KeyEvent.VK_T) {
                    if (levelTelur == 0) {
                        if (uang >= HARGA_TELUR1) {
                            levelTelur++;
                            uang -= HARGA_TELUR1;
                        }
                    } else if (levelTelur == 1) {
                        if (uang >= HARGA_TELUR2) {
                            levelTelur++;
                            uang -= HARGA_TELUR2;
                        }
                    } else if (levelTelur == 2) {
                        if (uang >= HARGA_TELUR3) {
                            levelTelur++;
                            uang -= HARGA_TELUR3;
                        }
                    }
                } else if (keyCode == KeyEvent.VK_ENTER) {
                  if (winningState == -2) {
                    winningState = 0;
                  }
                }
            }
        });

        jFrame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double mouseX = e.getX();
                if (uang >= HARGA_MAKANAN) {
                    addMakanan(mouseX);
                }

            }
        });
    }

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.WHITE);
      g.setColor(Color.WHITE);

      //gambar background
      g.drawImage(readImage("Images/background.png"), 0, 0, null);

      //Gambar Tulisan

      String amountKoin = "Koin : " + uang;
      String amountTelur = "Level Telur : " + levelTelur;
      String priceInfo = "Food : " + HARGA_MAKANAN + " - M | Guppy : " + HARGA_GUPPY + " - N" + " | Piranha : " + HARGA_PIRANHA + " - P";
      priceInfo = priceInfo + " | Telur : ";
      if (levelTelur == 0) {
        priceInfo += HARGA_TELUR1;
      } else if (levelTelur == 1) {
        priceInfo += HARGA_TELUR2;
      } else if (levelTelur == 2) {
        priceInfo += HARGA_TELUR3;
      }
      priceInfo += " - T";

      g.drawString(amountKoin, 0, 10);
      g.drawString(amountTelur, Akuarium.SCREEN_WIDTH - 95, 10);
      g.drawString(priceInfo, 130, 10);

      //gambar siput
      if ((tank.getSiput().getDirection() >= 0 && tank.getSiput().getDirection() < 0.5 * PI) ||
        (tank.getSiput().getDirection() >= 1.5 * PI && tank.getSiput().getDirection() < 2 * PI)) {
        g.drawImage(readImage("Images/siputkanan.png"), (int) floor(tank.getSiput().getX()), (int) floor(tank.getSiput().getY()) - 55, null);
      } else {
        g.drawImage(readImage("Images/siputkiri.png"), (int) floor(tank.getSiput().getX()), (int) floor(tank.getSiput().getY()) - 55, null);
      }

      //gambar ikan
      if (!tank.getListGuppy().isEmpty()) {
        Element<Guppy> currentGuppy = tank.getListGuppy().first;
        while (currentGuppy != null) {
          if ((currentGuppy.getInfo().getDirection() >= 0 && currentGuppy.getInfo().getDirection() < 0.5 * PI) ||
            (currentGuppy.getInfo().getDirection() >= 1.5 * PI && currentGuppy.getInfo().getDirection() < 2 * PI)) { //Hadap kanan
            if (currentGuppy.getInfo().getTahap() == 1) {
              if (currentGuppy.getInfo().getHungerState()) { //guppy lapar
                g.drawImage(readImage("Images/guppy1laparkanan.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              } else {
                g.drawImage(readImage("Images/guppy1kanan.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              }
            } else if (currentGuppy.getInfo().getTahap() == 2) {
              if (currentGuppy.getInfo().getHungerState()) { //guppy lapar
                g.drawImage(readImage("Images/guppy2laparkanan.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              } else {
                g.drawImage(readImage("Images/guppy2kanan.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              }
            } else if (currentGuppy.getInfo().getTahap() == 3) {
              if (currentGuppy.getInfo().getHungerState()) { //guppy lapar
                g.drawImage(readImage("Images/guppy3laparkanan.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              } else {
                g.drawImage(readImage("Images/guppy3kanan.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              }
            }
          } else { //Hadap kiri
            if (currentGuppy.getInfo().getTahap() == 1) {
              if (currentGuppy.getInfo().getHungerState()) { //guppy lapar
                g.drawImage(readImage("Images/guppy1laparkiri.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              } else {
                g.drawImage(readImage("Images/guppy1kiri.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              }
            } else if (currentGuppy.getInfo().getTahap() == 2) {
              if (currentGuppy.getInfo().getHungerState()) { //guppy lapar
                g.drawImage(readImage("Images/guppy2laparkiri.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              } else {
                g.drawImage(readImage("Images/guppy2kiri.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              }
            } else if (currentGuppy.getInfo().getTahap() == 3) {
              if (currentGuppy.getInfo().getHungerState()) { //guppy lapar
                g.drawImage(readImage("Images/guppy3laparkiri.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              } else {
                g.drawImage(readImage("Images/guppy3kiri.png"), (int) floor(currentGuppy.getInfo().getX()), (int) floor(currentGuppy.getInfo().getY()) - 55, null);
              }
            }
          }
          currentGuppy = currentGuppy.getNext();
        }
      }

      //gambar piranha
      if (!tank.getListPiranha().isEmpty()) {
        Element<Piranha> currentPiranha = tank.getListPiranha().first;
        while (currentPiranha != null) {
          if ((currentPiranha.getInfo().getDirection() >= 0 && currentPiranha.getInfo().getDirection() < 0.5 * PI) ||
            (currentPiranha.getInfo().getDirection() >= 1.5 * PI && currentPiranha.getInfo().getDirection() < 2 * PI)) { //Hadap kanan
            if (currentPiranha.getInfo().getHungerState()) { //Piranha lapar
              g.drawImage(readImage("Images/Piranhalaparkanan.png"), (int) floor(currentPiranha.getInfo().getX()), (int) floor(currentPiranha.getInfo().getY()) - 55, null);
            } else {
              g.drawImage(readImage("Images/Piranhakanan.png"), (int) floor(currentPiranha.getInfo().getX()), (int) floor(currentPiranha.getInfo().getY()) - 55, null);
            }
          } else {
            if (currentPiranha.getInfo().getHungerState()) { //Piranha lapar
              g.drawImage(readImage("Images/Piranhalaparkiri.png"), (int) floor(currentPiranha.getInfo().getX()), (int) floor(currentPiranha.getInfo().getY()) - 55, null);
            } else {
              g.drawImage(readImage("Images/Piranhakiri.png"), (int) floor(currentPiranha.getInfo().getX()), (int) floor(currentPiranha.getInfo().getY()) - 55, null);
            }
          }
          currentPiranha = currentPiranha.getNext();
        }
      }

      if (!tank.getListMakanan().isEmpty()) {
        Element<Makanan> currentFood = tank.getListMakanan().first;
        while (currentFood != null) {
          g.drawImage(readImage("Images/food.png"), (int) floor(currentFood.getInfo().getX()), (int) floor(currentFood.getInfo().getY()) - 25, null);
          currentFood = currentFood.getNext();
        }
      }

      if (!tank.getListKoin().isEmpty()) {
        Element<Koin> temp = tank.getListKoin().first;
        while (temp != null) {
          if (temp.getInfo().getNilai() == NILAI_KOIN_TAHAP1) {
            g.drawImage(readImage("Images/Koin1.png"), (int) floor(temp.getInfo().getX()), (int) floor(temp.getInfo().getY()) - 30, null);
          } else if (temp.getInfo().getNilai() == NILAI_KOIN_TAHAP2) {
            g.drawImage(readImage("Images/Koin2.png"), (int) floor(temp.getInfo().getX()), (int) floor(temp.getInfo().getY()) - 30, null);
          } else if (temp.getInfo().getNilai() == NILAI_KOIN_TAHAP3) {
            g.drawImage(readImage("Images/Diamond.png"), (int) floor(temp.getInfo().getX()), (int) floor(temp.getInfo().getY()) - 30, null);
          }
          temp = temp.getNext();
        }
      }

      if (winningState == 1) {
        g.drawImage(readImage("Images/win.png"), Akuarium.SCREEN_WIDTH / 2 - 200, Akuarium.SCREEN_HEIGHT / 2 - 50, null);
      } else if (winningState == -1) {
        g.drawImage(readImage("Images/lose.png"), Akuarium.SCREEN_WIDTH / 2 - 200, Akuarium.SCREEN_HEIGHT / 2 - 50, null);
      } else if (winningState == -2) {
        g.drawImage(readImage("Images/menu.png"), 0, 0, null);
        g.drawImage(readImage("Images/enter.png"), Akuarium.SCREEN_WIDTH / 2 - 265, Akuarium.SCREEN_HEIGHT / 2 + 100, null);
      }
  }
}