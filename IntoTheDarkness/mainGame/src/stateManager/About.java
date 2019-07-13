
package stateManager;

import Map.BackGround;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class About extends GameState{

    private BackGround bg;
    public About(GameManager gsm) {
        super(gsm);
         try
       {
          bg = new BackGround("/Resource/BackGround/about.gif");
          
          
           
       }catch(Exception e)
       {
           e.printStackTrace();
       }
    }

    
    public void init() {
       
    }

   
    public void update() {
        
    }

   
    public void draw(Graphics g) 
    {
       bg.paint(g);
       g.setColor(Color.RED);
        g.setFont(new Font("ALGERIAN",Font.BOLD,50));
        g.drawString("Press BackSpace to Return",180,640);
    }

   
    public void keyPressed(int k)
    {
        if(k == KeyEvent.VK_BACK_SPACE)
        {
                gsm.setState(GameManager.MENUSTATE);  
        }
    }

    public void keyReleased(int k) {
       
    }

   
    public void mouseClicked() {
        
    }
    
}
