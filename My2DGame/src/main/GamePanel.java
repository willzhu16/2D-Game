package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
  
  //SCREEN SETTINGS
  final int originalTileSize = 16; //16 x 16 tile
  final int scale = 3;
  
  public final int tileSize = originalTileSize * scale; //48 x 48 tile
  public final int maxScreenCol = 24;
  public final int maxScreenRow = 18;
  
  public final int screenWidth = tileSize * maxScreenCol; //768 pixels
  public final int screenHeight = tileSize * maxScreenRow; //576 pixels
  
  
  //WORLD SETTINGS
  public final int maxWorldCol = 100;
  public final int maxWorldRow = 100;
  public final int worldWidth = tileSize * maxWorldCol;
  public final int worldHeight = tileSize * maxWorldRow;
  
  //FPS
  int FPS = 30;
  
  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  public CollisionChecker cChecker = new CollisionChecker(this);
  public AssetSetter aSetter = new AssetSetter(this);
  public Player player = new Player(this, keyH);
  public SuperObject obj[] = new SuperObject[10];
  
  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }
  
  
  public void setUpGame() {
    aSetter.setObject();
  }
  
  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    
    double drawInterval = 100000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    
    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;
      
      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
      
    }
  }
//  @Override
//  public void run() {
//    
//    double drawInterval = 100000000/FPS;
//    double nextDrawTime = System.nanoTime() + drawInterval;
//    
//    while (gameThread != null) {
//      update();
//      repaint();
//      
//      try {
//        double remainingTime = nextDrawTime - System.nanoTime();
//        remainingTime = remainingTime/1000000;
//        
//        if (remainingTime < 0) {
//          remainingTime = 0;
//        }
//        Thread.sleep((long) remainingTime);
//        
//        nextDrawTime += drawInterval;
//      } catch (InterruptedException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//      
//    }
//  }
  
  public void update() {

    player.update();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    
    //TILE
    tileM.draw(g2);
    
    //OBJECT
    for (int i = 0; i < obj.length; i++) {
      if (obj[i] != null) {
        obj[i].draw(g2, this);
      }
    }
    
    //PLAYER
    player.draw(g2);
    
    g2.dispose();
  }
}
