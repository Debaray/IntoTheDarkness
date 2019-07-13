
package Existence;

import Map.BlockMap;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FireBallClass extends MapObjectClass{
    
    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitsprites;
    
    
    public FireBallClass(BlockMap tm,boolean right)
    {
        //super(tm);
       this.blockMap = tm;
        this.tileSize = tm.getTileSize();
        facingRight = right;
        moveSpeed = 10;
        if(right) dx = moveSpeed;
        else dx = -moveSpeed;
        
        width = 30;
        height = 30;
        cwidth = 14;
        cheight = 14;
        
        
        //loadsprites
        try
        {
            BufferedImage spritesheet = ImageIO.read(getClass().
                    getResourceAsStream("/Sprites/Player/fireballplyer.gif"));
            sprites = new BufferedImage[4];
            for(int i = 0 ; i < sprites.length ; i++)
            {
                sprites[i] = spritesheet.getSubimage(i*width, 0, width, height);
            }
            hitsprites = new BufferedImage[3];
            for(int i = 0 ; i < hitsprites.length ; i++)
            {
                hitsprites[i] = spritesheet.getSubimage(i*width, height, width, height);
            }
            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(20);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    public void setHit()
            
    {
        if(hit)return;
        hit = true;
        animation.setFrames(hitsprites);
        animation.setDelay(70);
         dx = 0;
    }
    public boolean shouldRemove()
    {
        return remove;
    }
    public void update()
    {
        checkTileMapCollision();
        setPosition(xtemp,ytemp);
        if(dx == 0 && !hit)
        {
            setHit();
        }
        animation.update();
        if(hit &&  animation.hasPlayedOnce())
        {
            remove = true;
        }
    }
    public void draw(Graphics g)
    {
        setMapPosition();
        if (facingRight)
        {
            g.drawImage(animation.getImage(),(int)( x+xmap-width/2),(int)( y+ymap-height/2), null);
            
        }else
        {
          g.drawImage(animation.getImage(),(int)( x+xmap-width/2+width),(int)( y+ymap-height/2), -width, height, null);
        }
    }
    
}
