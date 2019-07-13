
package Map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import maingame.GamePanel;

public class BackGround extends JFrame{
    
    private BufferedImage image;
    
    private Image bgImage;
    
    private double x;
    private double y;
    private double dx;
    private double dy;
    
    private double moveScale;
    
    public BackGround(String s , double ms)
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(s));
                   
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public BackGround(String s)
    {   
           
            ImageIcon i = new ImageIcon(this.getClass().getResource(s));
            bgImage = i.getImage();
        
    }
    public void paint(Graphics g)
    {
       g.drawImage(bgImage, 0, 0, this);
    }
    public void setPositon(double x, double y)
    {
        this.x = (x % moveScale)%GamePanel.WIDTH;
        this.y = (y % moveScale)%GamePanel.HEIGHT;
    }
    public void setVector(double dx, double dy)
    {
        this.dx= dx;
        this.dy = dy;
    }
    public void update()
    {
        x+= dx;
        y += dy;
    }
    public void draw(Graphics g)
    {
            g.drawImage(bgImage, 100, 30, this);
    }

}
