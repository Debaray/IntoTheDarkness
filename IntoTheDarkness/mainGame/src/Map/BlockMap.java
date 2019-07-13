
package Map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import maingame.GamePanel;


public class BlockMap {
    
    //position
    private double x;
    private double y;
    
    //bound
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    
    private double tween;
    
    //map
    private int[][] map;
    private int tileSize;
    private int numRow;
    private int numCol;
    private int width;
    private int height;
    
    //tileset
    private BufferedImage tileImage;
    private int numTileAc;
    private Block[][] tiles;
    
    //draw
    private int rowOffSet;
    private int colOffSet;
    private int numberRowsToDraw;
    private int numberColsToDraw;
    private int numRows;
    private int numCols;
    
     
     public BlockMap(int tileSize)
     {
         this.tileSize = tileSize;
        numberRowsToDraw = (GamePanel.HEIGHT / tileSize)+2;
        numberColsToDraw = (GamePanel.WIDTH / tileSize)+2;
        tween = 0.07;
     }
    
    public void LoadTiles(String s) 
    {
        try
        {
             tileImage = ImageIO.read(getClass().getResourceAsStream(s));
             numTileAc = tileImage.getWidth()/tileSize;
            tiles = new Block[2][numTileAc];
            
            BufferedImage subImage;
            for(int col = 0 ; col < numTileAc ; col++)
            {
                subImage = tileImage.getSubimage(col*tileSize, 0, tileSize, tileSize);
                tiles[0][col] = new Block(subImage,Block.NORMAL);
                subImage = tileImage.getSubimage(col*tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Block(subImage,Block.BLOCKED);
                
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void LoadMap(String s)
    {
        try
        {
            InputStream i;
            i = getClass().getResourceAsStream(s);
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(i));
            numCol = Integer.parseInt(reader.readLine());
            numRow = Integer.parseInt(reader.readLine());
            map = new int[numRow][numCol];
            width = numCol*tileSize;
            height = numRow*tileSize;
            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT -height;
            ymax = 0;
            
            String delimeters = "\\s+";
            for(int row = 0 ; row < numRow ; row++)
            {
                String line = reader.readLine();
                String[] tokens = line.split(delimeters);
                for(int col = 0 ; col < numCol ; col++)
                {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            } 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public int getTileSize()
    {
        return tileSize;
    }
    public double getX()
    {
        return  x;
    }
    public double getY()
    {
        return y;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    
    public int getType(int row,int col)
    {
        int row_col = map[row][col];
        int r = row_col/numTileAc;
        int c = row_col%numTileAc;
        return tiles[r][c].getType();
    }
    public void setPosition(double x , double y)
    {
        this.x += (x -this.x) * tween;
        this.y += (y -this.y) * tween;
        
        fixBounds();
        colOffSet = (int) -this.x/tileSize;
        rowOffSet = (int) -this.y/tileSize;
    }
    private void fixBounds()
    {
        if(x < xmin) x = xmin;
        if(y < ymin) y = ymin;
        if(x > xmax) x = xmax;
        if(y > ymax) y = ymax;
        
    }
    public int getNumRows() { return numRows; }
    public int getNumCols() { return numCols; }
    public void draw(Graphics g)
    {
        for(int row = rowOffSet ; row <(rowOffSet+numberRowsToDraw) ;row++)
        {
            if(row >= numRow)
            {
                break;
            }
            for(int col = colOffSet ; col < (colOffSet + numberColsToDraw) ; col++)
            {
                if(col >= numCol)
                {
                    break;
                }
                if(map[row][col] == 0)
                {
                    continue;
                }
                int rc = map[row][col];
                int r = rc / numTileAc;
                int c = rc % numTileAc;
                g.drawImage(tiles[r][c].getImage(),(int)x + col*tileSize, (int)y + row*tileSize, null);
                
            }
        }
    }

   
}
