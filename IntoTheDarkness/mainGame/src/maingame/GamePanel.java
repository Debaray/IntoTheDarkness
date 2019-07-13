package maingame;

import stateManager.GameManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable ,KeyListener ,MouseListener,MouseMotionListener{
    public static final long serialVersionUID = 1L; 
    //dimention       
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 650;
    //Thread
    private Thread thread;
    private boolean IsRunning = false;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    //image
    private BufferedImage image;
    private Graphics g;
    
    public static GameManager gsm;
    
    public GamePanel()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        
        start();
       
    }
    
    private void start()
    {
        thread = new Thread(this);
        thread.start();
        
    }
    private void init()
    {
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics) image.getGraphics();
        IsRunning = true;
        gsm = new GameManager();
    }
    
    public void run()
    {
        init();
         long start,elapsed,wait;
        
        //game loop
       while(IsRunning)
       {
         start =System.nanoTime();
         update();
         draw(g);
         drawScreen();
         elapsed = System.nanoTime() - start;
         wait = targetTime - elapsed/1000000;
         if(wait <= 0)
         {
             wait = 15;
         }
         try
         {
             
             Thread.sleep(wait);
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         
       }
    }
    public void update()
    {
        gsm.update();
    }
    public void draw(Graphics g)
    {
        gsm.draw(g);
    }
    public void drawScreen()
    {
       Graphics g1 = getGraphics();
       g1.drawImage(image,0,0, null);
       g1.dispose();
    }
    public void keyTyped(KeyEvent e) {}  
    public void keyPressed(KeyEvent e)
    {
       gsm.keyPressed(e.getKeyCode());
    }
     public void keyReleased(KeyEvent e)
    {
        gsm.keyReleased(e.getKeyCode());
    }
    public void mouseClicked(MouseEvent e)
    {
      
      //gsm.mouseClicked();
    }
    public void mousePressed(MouseEvent e)
    {
       
    }
    public void mouseReleased(MouseEvent e) 
    {
        
    }
    public void mouseEntered(MouseEvent e)
    {
       
    }
    public void mouseExited(MouseEvent e) 
    {
   
    }  
    public void mouseDragged(MouseEvent e) 
    {
       
    }
    public void mouseMoved(MouseEvent e)
    {
       
    }
}
