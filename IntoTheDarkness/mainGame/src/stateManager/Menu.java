
package stateManager;

import Map.BackGround;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Menu extends GameState{

   
    private BackGround bg;
     private BackGround bg1;
   
    
    private String[] options = {"Start","Instruction","About","Quit"}; 
    private int CurrentOperation = 0;

    public Menu(GameManager gsm) {
       super(gsm);
       
       try
       {
          bg = new BackGround("/Resource/BackGround/MenuBackGround1.gif");
           bg1 = new BackGround("/Resource/BackGround/intothedarkness.gif");
          
           
       }catch(Exception e)
       {
           e.printStackTrace();
       }
    }
    
    public void init() {
        
    }
    public void update() {
        bg.update();
    }
    public void draw(Graphics g) {
        //draw Background
        bg.paint(g);
        bg1.draw(g);
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
                //game
                gsm.setState(GameManager.LEVEL1STATE);
            }
            else if(CurrentOperation == 1)
            {
                //controls
                gsm.setState(GameManager.CONTROLSTATE);
            }
            else if(CurrentOperation == 2)
            {
                //help
                gsm.setState(GameManager.ABOUTSTATE);
            }
            else if(CurrentOperation == 3)
            {
                System.exit(0);
            }
            
             
        }
       
    }
    public void keyReleased(int k) {
       
    }
    public void mouseClicked()
    {
        
    }
    
 
}
