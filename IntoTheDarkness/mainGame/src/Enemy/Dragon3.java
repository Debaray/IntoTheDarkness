
package Enemy;

import Existence.Animation;
import Existence.Enemy3;

import Map.BlockMap;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Dragon3 extends Enemy3
{
    
    private BufferedImage[] sprites;
   
    private BufferedImage spritesheet ;
    private Animation animation ;
    
    
    public Dragon3(BlockMap tm) {
        super(tm);
        moveSpeed = 3.3;
        maxSpeed = 4.5;
       
       
        
        width = 200;
        height = 200;
        cwidth = 100;
        cheight = 100;
        
        health = maxHealth = 2;
        damage = 1;
        
        try
        {
            spritesheet = ImageIO.read(getClass().getResourceAsStream(
                    "/Resource/Sprites/Enemies/enemy2.gif"));
          
            sprites = new BufferedImage[20];
            for(int i = 0 ; i < sprites.length; i++)
            {
                sprites[i] = spritesheet.getSubimage(i*width, 0, width, height);
            }
   
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(30);
      
        up = true;
        left = true;
        facingRight = true;
    }
    private void getNextPosition()
    {
        if (up)
        {
             dy += moveSpeed;
             if (dy >  maxSpeed)
             {
                dy = maxSpeed;
             }
        }
         if (down)
        {
            dy -= moveSpeed;
            if (dy <  maxSpeed)
            {
                dy =  -maxSpeed;
            }
                
        }
      
           if (left)
        {
             dx += moveSpeed;
             if (dx >  maxSpeed)
             {
                dx = maxSpeed;
             }
        }
         if (right)
        {
            dx -= moveSpeed;
            if (dx <  maxSpeed)
            {
                dx =  -maxSpeed;
            }
                
        }
            
        
    }
    public void update()
    {
        //update Position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp,ytemp);
       
        //if hits a wall , go other direction
       if(up && dy == 0)
        {
            up = false;
            down = true;
            //facingRight = false;
        }
        else if(down && dy == 0)
        {
            up = true;
            down = false;
           // facingRight = false;
            
        }
        if(left && dx == 0)
        {
            left = false;
            right = true;
            facingRight = false;
        }
        else if(right && dx == 0)
        {
            left = true;
            right = false;
            facingRight = true;
            
        }
       
        //update animation
        animation.update();
    }
    
    public void draw(Graphics g)
    {
        
        setMapPosition();
        
        if(facingRight) {
			g.drawImage(animation.getImage()
                                ,(int)(x + xmap - width / 2 -50),(int)(y + ymap - height / 2),null
			);
		}
		else {
			g.drawImage(animation.getImage()
                                ,(int)(x + xmap - width / 2 + width+50),(int)(y + ymap - height / 2),-width,height,null
			);
		}
      
    }
    
    
}
