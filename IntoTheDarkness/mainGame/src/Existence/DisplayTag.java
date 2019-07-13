
package Existence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import stateManager.Level;

public class DisplayTag 
{
    private PlayerClass player;
    public DisplayTag(PlayerClass player)
    {
       this.player = player;
    }
    public void draw(Graphics g)
    {
           
           g.setColor(Color.GRAY);
           g.fillRect(0, 15, 150, 30);
           g.setColor(Color.BLUE);
           g.setFont(new Font("Algerian",Font.PLAIN,20));
           g.drawString("Health : ", 5, 35);
            g.setFont(new Font("Arial",Font.PLAIN,20));
           g.drawString(player.getHealth() + "/" + player.getMaxHealth(),100,35);
           g.setColor(Color.GRAY);
           g.fillRect(0, 60, 150, 30);
            g.setColor(Color.BLUE);
           g.setFont(new Font("Algerian",Font.PLAIN,20));
           g.drawString("Score : ", 5, 80);
            g.setFont(new Font("Arial",Font.PLAIN,20));
           g.drawString(Integer.toString(Level.score),100,80);
    }
}
