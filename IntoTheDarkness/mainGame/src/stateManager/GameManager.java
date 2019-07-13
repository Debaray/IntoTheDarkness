
package stateManager;

import stateManager.GameState;
import java.awt.Graphics;
import java.util.ArrayList;





public class GameManager {
     public ArrayList <GameState>states;
     
     private int currentState;
     public static final int MENUSTATE = 0;
     public static final int LEVEL1STATE = 1;
     public static final int CONTROLSTATE = 2;
     public static final int ABOUTSTATE = 3;
    public GameManager()
    {
        states = new ArrayList<GameState>();
        states.add(new Menu(this));
        states.add(new Level(this));
        states.add(new Control(this));
        states.add(new About(this));
        
    }
    public  void setState(int state)
    {
      currentState = state;
      states.get(currentState).init();

    }
    public void update()
    {
        states.get(currentState).update();
    }
    public void keyPressed(int k)
    {
        states.get(currentState).keyPressed(k);
    }
    public void keyReleased(int k)
    {
       states.get(currentState).keyReleased(k);
    }
    public void draw(Graphics g)
    {
        states.get(currentState).draw(g);
    }

    public void mouseClicked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
