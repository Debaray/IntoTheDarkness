package Existence;
import Map.BlockMap;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import stateManager.GameOver;
import stateManager.GameManager;
import stateManager.Level;
public class PlayerClass extends MapObjectClass {
    //player stuff
    private int health;
    private int maxHealth;
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchingTime;
   
    
    //fireball
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBallClass> fireBalls;
        
    // gliding
     private boolean gliding;
     private boolean shooting;
           
    // animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {2,10,1,1,2,8};
 
          
    // animation actions
    private static final int IDLE = 0;
    private static final int RUNNING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int SHOOTING = 5;
    public static int finalScore ;
    
    protected int damage;
    public PlayerClass (BlockMap tm)
    {
        //super(tm);
        this.blockMap = tm;
        this.tileSize = tm.getTileSize();
        width = 50;
        height = 100;
        cwidth = 40;
        cheight = 57;
        
        moveSpeed = 1.5;
        maxSpeed = 3.0;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -6.8;
        stopJumpSpeed = 0.3;
		
        facingRight = true;
            
        health = maxHealth = 1;
        fire =  maxFire = 2500;
        fireCost = 500;
        fireBallDamage = 5;
        fireBalls = new ArrayList<FireBallClass>();
        

        // load sprites
        try {
             BufferedImage spritesheet;
            spritesheet = ImageIO.read(getClass().getResourceAsStream(
                   "/Resource/Sprites/Player/finalplayersprites.gif"));

           //now extracting animations
            sprites = new ArrayList<BufferedImage[]>();
            for(int i=0; i<6; i++)
            {      
                BufferedImage[] bi = new BufferedImage[numFrames[i]]; 
                for(int j=0; j<numFrames[i];j++)
                {                   
                        bi[j] = spritesheet.getSubimage( j*width, i*height, width, height);
                }
                sprites.add(bi);
            }
        }catch(Exception e) 
        {
		e.printStackTrace();
        }
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400); 
        
    }
        
    public int getHealth()
    {
        return health;
    }
    public int getMaxHealth()
    {
        return maxHealth;
    }
   
    public void setShooting(boolean b)
    { 
        shooting = b;
    }
    public void setGliding(boolean b)
    { 
	gliding = b;
    }        
    public void setLeft(boolean b) { left = b; }
    public void setRight(boolean b) { right = b; }
    public void setUp(boolean b) { up = b; }
    public void setDown(boolean b) { down = b; }
    public void setJumping(boolean b) { jumping = b; }
    public void checkAttack(ArrayList<Enemy> enemy) {
		
		// loop through enemies
		for(int i = 0; i < enemy.size(); i++) {
			
			Enemy e = enemy.get(i);
			// shooting
                        
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
                                        
                                        
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			// check enemy collision
			if(intersects(e)) {
				hit(e.getDamage());
                                
			}
			
		}
		
	}
    public void checkAttack1(ArrayList<Enemy1> enemy1) {
		
		// loop through enemies
		for(int i = 0; i < enemy1.size(); i++) {
			
			Enemy1 e1 = enemy1.get(i);
			// shooting
                        
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e1)) {
					e1.hit(fireBallDamage);
                                        
                                        
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			// check enemy collision
			if(intersects(e1)) {
				hit(e1.getDamage());
                                
			}
			
		}
		
	}
    public void checkAttack2(ArrayList<Enemy2> enemy2) {
		
		// loop through enemies
		for(int i = 0; i < enemy2.size(); i++) {
			
			Enemy2 e2 = enemy2.get(i);
			// shooting
                        
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e2)) {
					e2.hit(fireBallDamage);
                                        
                                        
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			// check enemy collision
			if(intersects(e2)) {
				hit(e2.getDamage());
                                
			}
			
		}
		
	}
        public void checkAttack3(ArrayList<Enemy3> enemy3) {
		
		// loop through enemies
		for(int i = 0; i < enemy3.size(); i++) {
			
			Enemy3 e = enemy3.get(i);
			// shooting
                        
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
                                        
                                        
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			// check enemy collision
			if(intersects(e)) {
				hit(e.getDamage());
                                
			}
			
		}
		
	}
    public void hit(int damage)
    {
        if(flinching)return;
        health -= damage;
        if(health < 0) health = 0;
        
            
        
        if(health == 0)
        {
            dead = true;
            finalScore = Level.score;
           // Main.getFrame().dispose();
 
        }
        flinching = true;
        flinchingTime = System.nanoTime();
    }
    public int getDamage()
    {
        return damage;
    }
    public boolean isDead()
    {
        return dead;
    }
    private void getNextPosition()
    {
         //movement
        if (left)
        {
             dx -= moveSpeed;
             if (dx <  -maxSpeed)
             {
                dx = -maxSpeed;
             }
        }
        else if (right)
        {
            dx += moveSpeed;
            if (dx> maxSpeed)
            {
                dx =  maxSpeed;
            }
                
        }
        else 
        {
            if(dx>0)
            {
              dx -= stopSpeed;
                if(dx<0)
                {
                    dx=0;
                }
            }
            else if (dx<0)
            {
                 dx += stopSpeed;
                if(dx>0)
                {
                 dx=0;
                }
            }
        }
        // cannot move while atacking
        if((currentAction == SHOOTING)&&!(jumping||falling))
        {
           dx=0;
        }
        //jumping
        if(jumping && !falling)
        {
            dy=jumpStart;
            falling=true;
        }
        //falling
        if(falling)
        {
            if (dy > 0 && gliding) 
            {
                dy += fallSpeed * 0.1;
            }
            else
            {
                dy += fallSpeed;
            }
            if(dy>0) 
            {
                jumping = false;
            }
            if(dy < 0 && !jumping)
            {
                dy += stopJumpSpeed;
            }
            if(dy > maxFallSpeed)
            {
                dy = maxFallSpeed;
            }
        }
            
    }
    public void update() 
    {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
                
                if(currentAction == SHOOTING)
                {
                    if(animation.hasPlayedOnce())
                    {
                   shooting = false;
                    } 
                }
                //shooting
                fire += 15;
                if(fire > maxFire)
                {
                    fire = maxFire;
                }
                if(shooting /*&& currentAction != SHOOTING*/)
                {
                    if(fire > fireCost)
                    {
                        fire -= fireCost;
                        FireBallClass fb = new FireBallClass(blockMap,facingRight);
                        fb.setPosition(x, y);
                        fireBalls.add(fb);
                        
                    }
  
                }
                //update fireBall
                for(int i = 0 ; i < fireBalls.size(); i++)
                {
                    fireBalls.get(i).update();
                    if(fireBalls.get(i).shouldRemove())
                    {
                        fireBalls.remove(i);
                        i--;
                    }
                }
                if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchingTime) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
			}
		}
                
		// set animation
                
                 if(dy > 0) 
                 {
                    if(gliding)
                    {
			if(currentAction != GLIDING)
                        {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(400);
					width = 50;
			}
                    }
                    else if(currentAction != FALLING)
                    {
			currentAction = FALLING;
			animation.setFrames(sprites.get(FALLING));
			animation.setDelay(200);
			width = 50;
                    }
                }
		else if(dy < 0) 
                {
			if(currentAction != JUMPING) 
                        {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 50;
			}
		}
		else if(left || right) {
			if(currentAction != RUNNING) {
				currentAction = RUNNING;
				animation.setFrames(sprites.get(RUNNING));
				animation.setDelay(200);
				width = 50;
			}
		}
                else if(shooting)
                {
                    if(currentAction != SHOOTING)
                    {
                        currentAction = SHOOTING;
                        animation.setFrames(sprites.get(SHOOTING));
                        animation.setDelay(30);
                        width = 50;
                    }
                }
		else {
			if(currentAction != IDLE) 
                        {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(80);
				width = 50;
			}
		}
		
		animation.update();
		
		// set direction
		if(currentAction != SHOOTING) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
        
        public void draw(Graphics g)
        {
            //
                setMapPosition();
                //draw player
                if(flinching)
                {
                    long elapsed = (System.nanoTime()- flinchingTime)/1000000;
                
                    if(elapsed / 100 % 2 == 0)
                     {
                    return;
                    }
                }
                if (facingRight)
                {
                    
                     g.drawImage(animation.getImage(),(int)( x+xmap-width/2),(int)( y+ymap-height/2), null);
                }
                else
                {
                     g.drawImage(animation.getImage(),(int)( x+xmap-width/2+width),(int)( y+ymap-height/2), -width, height, null);
                }
                //draw FireBall
                for(int i = 0 ; i < fireBalls.size();i++)
                {
                    fireBalls.get(i).draw(g);
                }
        }

    

    
        
}
