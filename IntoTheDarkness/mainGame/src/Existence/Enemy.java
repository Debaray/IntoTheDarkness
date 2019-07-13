
package Existence;

import Map.BlockMap;

public abstract class Enemy extends MapObjectClass
{
 protected int health;
 protected int maxHealth;
 protected boolean dead;
 protected int damage;
 
 protected boolean flinching;
 protected long flinchTimer;
 
    public Enemy(BlockMap tm)
    {
        this.blockMap = tm;
        this.tileSize = tm.getTileSize();
        
    }
    public boolean isDead()
    {
        return dead;
    }
    public int getDamage()
    {
        return damage;
    }
    public void hit(int damage)
    {
        if(dead || flinching)
        {
            return ;
        }
        health -= damage;
        if(health < 0)
        {
            health = 0;
        }
        if(health == 0)
        {
            dead = true;
        }
        flinching = true;
        flinchTimer = System.nanoTime();
    }

    public void update()
    {
      
    }
}
