package stateManager;

import Map.BlockMap;
import Map.BackGround;
import Enemy.Dragon;
import Enemy.Dragon1;
import Enemy.Dragon2;
import Enemy.Dragon3;
import Existence.DisplayTag;
import Existence.Enemy;
import Existence.Enemy1;
import Existence.Enemy2;
import Existence.Enemy3;
import Existence.Explosions;
import Existence.PlayerClass;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import maingame.GamePanel;
import maingame.Main;

public class Level extends GameState {

    private BlockMap tileMap;
    private BackGround bg;
    private PlayerClass player;

    private DisplayTag tag;
    public static int score = 0;
    private ArrayList<Enemy> enemy;
    private ArrayList<Enemy1> enemy1;
    private ArrayList<Enemy2> enemy2;
    private ArrayList<Enemy3> enemy3;
    public ArrayList<Explosions> explosion;
    public Enemy e;
    public Enemy1 e1;
    public Enemy2 e2;
    public Enemy3 e3;
    int count = 0;

    public Level(GameManager gsm) {
        super(gsm);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void init() {

        bg = new BackGround("/Resource/BackGround/Level1.gif");

        tileMap = new BlockMap(30);
        tileMap.LoadTiles("/Resource/Tiles/testtileset.gif");
        tileMap.LoadMap("/Resource/Map/finalmap.map");
        tileMap.setPosition(0, 0);

        player = new PlayerClass(tileMap);
        player.setPosition(150, 530);

        populateEnemies();

        explosion = new ArrayList<Explosions>();
        tag = new DisplayTag(player);

    }

    public void createDragon() {
        enemy = new ArrayList<Enemy>();
        Dragon d;
        Point points = new Point(getRandomNumberInRangeX(100, 500), getRandomNumberInRangeY(200, 250));
        d = new Dragon(tileMap);
        d.setPosition(points.x, points.y);
        enemy.add(d);
    }

    public void createDragon1() {
        enemy1 = new ArrayList<Enemy1>();
        Dragon1 d1;
        Point points1 = new Point(getRandomNumberInRangeX(600, 1000), getRandomNumberInRangeY(200, 250));
        d1 = new Dragon1(tileMap);
        d1.setPosition(points1.x, points1.y);
        enemy1.add(d1);
    }

    public void createDragon2() {
        enemy2 = new ArrayList<Enemy2>();
        Dragon2 d2;
        Point points2 = new Point(getRandomNumberInRangeX(1100, 1500), getRandomNumberInRangeY(200, 250));
        d2 = new Dragon2(tileMap);
        d2.setPosition(points2.x, points2.y);
        enemy2.add(d2);
    }
    public void createDragon3() {
        enemy3 = new ArrayList<Enemy3>();
        Dragon3 d3;
        Point points3 = new Point(getRandomNumberInRangeX(1100, 1500), getRandomNumberInRangeY(200, 250));
        d3 = new Dragon3(tileMap);
        d3.setPosition(points3.x, points3.y);
        enemy3.add(d3);
    }

    private void populateEnemies() {
        createDragon();
        createDragon1();
        createDragon2();
        createDragon3();

    }

    private static int getRandomNumberInRangeX(int minX, int maxX) {

        if (minX >= maxX) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((maxX - minX) + 1) + minX;
    }

    private static int getRandomNumberInRangeY(int minY, int maxY) {

        if (minY >= maxY) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((maxY - minY) + 1) + minY;
    }

    public void update() {
        // update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety());

        //set Background
        bg.setPositon(tileMap.getX(), tileMap.getY());
        //attack enemies
        player.checkAttack(enemy);
        //update all enemies
        for (int i = 0; i < enemy.size(); i++) {
            e = enemy.get(i);
            enemy.get(i).update();
            if (enemy.get(i).isDead()) {
                enemy.remove(i);
                i--;
                count++;
                explosion.add(new Explosions(e.getx(), e.gety()));
                //createDragon2();
                score = score+10;
                if(count == 4)
                {
                    createDragon();
                    createDragon1();
                    createDragon2();
                    createDragon3();
                    count = 0;
                }
            }
        }
        player.checkAttack1(enemy1);
        //update all enemies
        for (int i = 0; i < enemy1.size(); i++) {
            e1 = enemy1.get(i);
            enemy1.get(i).update();
            if (enemy1.get(i).isDead()) {
                enemy1.remove(i);
                i--;
                count++;
                score = score+10;
                explosion.add(new Explosions(e1.getx(), e1.gety()));
               // createDragon();
                
                if(count == 4)
                {
                    createDragon();
                    createDragon1();
                    createDragon2();
                    createDragon3();
                    count = 0;
                }
            }
        }
        player.checkAttack2(enemy2);
        //update all enemies
        for (int i = 0; i < enemy2.size(); i++) {
            e2 = enemy2.get(i);
            enemy2.get(i).update();
            if (enemy2.get(i).isDead()) {
                enemy2.remove(i);
                i--;
                count++;
                score = score+10;
                explosion.add(new Explosions(e2.getx(), e2.gety()));
             
                if(count == 4)
                {
                    createDragon();
                    createDragon1();
                    createDragon2();
                    createDragon3();
                    count = 0;
                }
            }
        }
            player.checkAttack3(enemy3);
        //update all enemies
        for (int j = 0; j < enemy3.size(); j++)
        {
            e3 = enemy3.get(j);
            enemy3.get(j).update();
            if (enemy3.get(j).isDead()) {
                enemy3.remove(j);
                j--;
                count++;
                score = score+10;
                explosion.add(new Explosions(e3.getx(), e3.gety()));
                
                
                if(count == 4)
                {
                    createDragon();
                    createDragon1();
                    createDragon2();
                    createDragon3();
                    count = 0;
                }
            }
        }
        //update explosions
        for (int i = 0; i < explosion.size(); i++) {
            explosion.get(i).update();
            if (explosion.get(i).shouldRemove()) {
                explosion.remove(i);
                i--;
            }
        }
        
        if(score >= 100)
        {
                Main.getFrame().dispose();
                Winner win = new Winner();
                win.setVisible(true);
                score = 0;
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            win.dispose();
            Main.getFrame().setVisible(true);
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics g) {
        //draw BackGround
        g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        bg.paint(g);

        //tileMap
        tileMap.draw(g);

        //draw display Tag
        tag.draw(g);
        //dtaw player
        player.draw(g);

        //draw enemy
        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).draw(g);
        }
        for (int i = 0; i < enemy1.size(); i++) {
            enemy1.get(i).draw(g);
        }
        for (int i = 0; i < enemy2.size(); i++) {
            enemy2.get(i).draw(g);
        }
        for (int i = 0; i < enemy3.size(); i++) {
            enemy3.get(i).draw(g);
        }
        //draw explosions
        for (int i = 0; i < explosion.size(); i++) {
            explosion.get(i).setMapPosition((int) tileMap.getX(), (int) tileMap.getY());
            explosion.get(i).draw(g);
        }

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(true);
        }
        if (k == KeyEvent.VK_SHIFT) {
            player.setGliding(true);
        }
        if (k == KeyEvent.VK_SPACE) {
            player.setShooting(true);
        }

    }

    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(false);
        }
        if (k == KeyEvent.VK_SHIFT) {
            player.setGliding(false);
        }

    }

    public void mouseClicked() {
        player.setShooting(true);

    }

    public void mousePressed() {

    }

}
