
package stateManager;

import java.awt.Graphics;

public abstract class GameState {
    
    public GameManager gsm;
    
    
    public GameState(GameManager gsm)
    {
        this.gsm =  gsm;
    }
    public GameState()
    {
        
    }
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void mouseClicked() ;
   
    
}
