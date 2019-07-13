
package Map;

import java.awt.image.BufferedImage;


public class Block {
    private BufferedImage image;
    private int type;
    //tile types
    
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    
    public Block(BufferedImage image,int type)
    {
        this.image = image;
        this.type = type;
    }
    public BufferedImage getImage()
    {
        return image;
    }
    public int getType()
    {
        return type;
    }
}
