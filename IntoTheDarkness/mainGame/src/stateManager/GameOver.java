
package stateManager;

import Map.BackGround;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class GameOver extends GameState{

    private BackGround bg;
   
    private String[] options = {"ReStart","Main Menu","Quit"}; 
    private int CurrentOperation = 0;
   public GameOver(GameManager gsm)
   {
       super(gsm);
       try
       {
          bg = new BackGround("/Resource/BackGround/lose.gif");
           
       }catch(Exception e)
       {
           e.printStackTrace();
       }
   }

    public GameOver(int DEAD) {
       
    }
   
    public void init() {
       
    }
    public void update() {
        bg.update();
    }

   
    public void draw(Graphics g) {
       //draw Background
        bg.paint(g);
        //draw menu 
        for(int i = 0 ; i < options.length ; i++)
        {
            
            if(i == CurrentOperation)
            {
                g.setColor(Color.GREEN);
            }
            else
            {
                g.setColor(Color.BLUE);
            }
            
            g.setFont(new Font("Algerian",Font.BOLD,35));
            g.drawString(options[i],500,250 + i*70); 
        }
    }

   
    public void keyPressed(int k) {
        
        if(k == KeyEvent.VK_DOWN)
        {
            CurrentOperation++;
            if(CurrentOperation >= options.length)
            {
                CurrentOperation = 0;
            }
        }
        else if(k == KeyEvent.VK_UP)
        {
            CurrentOperation--;
            if(CurrentOperation < 0)
            {
                CurrentOperation = options.length -1;
            }
        }
        else if(k == KeyEvent.VK_ENTER)
        {
            if(CurrentOperation == 0)
            {
                gsm.setState(GameManager.LEVEL1STATE);
            }
            else if(CurrentOperation == 1)
            {
                gsm.setState(GameManager.MENUSTATE);
            }
            else if(CurrentOperation == 2)
            {
               // System.exit(0);
                System.out.println("fsjfsf");
            }
            
            
             
        }
       
    }

    
    public void keyReleased(int k) {
        
    }

   
    public void mouseClicked() {
       
    }
    
}
