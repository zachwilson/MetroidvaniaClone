/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author Zach Wilson
 */
import java.util.ArrayList;

public class World extends JPanel implements ActionListener{
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public ArrayList<Projectile> shots;
    public ArrayList<Entity> entities;
    public ArrayList<Entity> toAdd;
    public ArrayList<Enemy> enemies;
    public ArrayList<Room> rooms;
    public Room currRoom;
    public Jumper jumper;
    public boolean inGame;
    public boolean won;
    public boolean keyPressed;
    public boolean keyReleased;
    public KeyEvent pressed;
    public KeyEvent released;
    // information for the window
    public int winX;
    public int winY;
    public final int winH = 800;
    public final int winW = 800;
    
    public Timer timer;
    // Delay = 400 to see individual ticks;
    public final int DELAY = 15;
    
    
    public World(){
        entities = new ArrayList();
        enemies = new ArrayList();
        rooms = new ArrayList();
        toAdd = new ArrayList();
        inGame = true;
        won = false;
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        Room room0 = new Room(2,1,0);
        rooms.add(room0);
        Door door1 = new Door(1580,640,1);
        door1.direction = RIGHT;
        room0.add(door1);
       
        jumper = new Jumper(40,660);
        room0.add(jumper);
        shots = jumper.shots;
        
        currRoom = room0;
        entities = room0.entities;
        
//        Room room1 = new Room(1,4,1);
//        rooms.add(room1);
//        Door door2 = new Door(0,1440,door1,0);
//        room1.add(door2);
//        Wall platform = new Wall(1,640,12,1);
//        room1.add(platform);
//        platform = new Wall(360,680,8,1);
//        room1.add(platform);
//        platform = new Wall(520,1140,8,1);
//        room1.add(platform);
//        platform = new Wall(360,1260,4,1);
//        room1.add(platform);
//        platform = new Wall(60,1320,4,1);
//        room1.add(platform);
//        platform = new Wall(560,1360,8,1);
//        room1.add(platform);
//        platform = new Wall(320,1500,8,1);
//        room1.add(platform);
//        platform = new Wall(1,1580,7,1);
//        room1.add(platform);
//        platform = new MovingWall(240,820,240,1040,8,1);
//        platform.dy = 1;
//        room1.add(platform);
//        platform = new Wall(200,1680,6,1);
//        room1.add(platform);
//        platform = new Wall(320,1760,6,1);
//        room1.add(platform);
//        platform = new Wall(480,1920,8,1);
//        room1.add(platform);
//        platform = new Wall(240,2020,8,1);
//        room1.add(platform);
//        platform = new Wall(1,2160,5,1);
//        room1.add(platform);
//        platform = new MovingWall(140,2280,600,2280,9,1);
//        platform.dx = 1;
//        room1.add(platform);
//        platform = new Wall(440,2440,8,1);
//        room1.add(platform);
//        platform = new MovingWall(160,2600,160,2820,8,1);
//        platform.dy = 1;
//        room1.add(platform);
//        platform = new Wall(400,2920,10,1);
//        room1.add(platform);
//        platform = new Wall(160,3040,6,1);
//        room1.add(platform);

        Wall ceiling0 = new Wall(60,-400,82,1);
        Wall floor0 = new Wall(60,380,82,15);
        Wall left0 = new Wall(40,-400,2,40);
        Wall right0 = new Wall(1660,-400,1,32);
        addWall(ceiling0);
        addWall(floor0);
        addWall(left0);
        addWall(right0);
        Door entrance = new Door(1660,240,1);
        entrance.direction = RIGHT;
        entities.add(entrance);
        Room room0 = new Room();
        room0.id = 0;
        room0.entities = entities;
        currRoom = room0;
        room0.top = -400;
        room0.bottom = 400;
        room0.left = 40;
        room0.right = 1680;
        rooms.add(room0);
        
        
        Wall ceiling1 = new Wall(0,0,42,1);
        Wall floor1 = new Wall(0,780,42,1);
        Wall left1 = new Wall(0,0,1,32);
        Wall right1 = new Wall(780,0,1,39);
        Wall mid1 = new Wall(160,300,25,2);
        entities = new ArrayList();
        
        addWall(ceiling1);
        addWall(floor1);
        addWall(left1);
        addWall(right1);
        addWall(mid1);
        
        Crawler crawler1 = new Crawler(160,260,1,entities);
        entities.add(crawler1);
        Turret turret = new Turret(200,340,jumper);
        entities.add(turret);
        
        Door test1 = new Door(0,640,entrance,0);
        entrance.exit = test1;
        entities.add(test1);
        Room room1 = new Room();
        room1.id = 1;
        room1.entities = entities;
        room1.top = 0;
        room1.bottom = 800;
        room1.left = 0;
        room1.right = 800;
        rooms.add(room1);
        
        Door trans2 = new Door(780,640,2);
        trans2.direction = RIGHT;
        
        Room room2 = new Room(1,1,2);
        Crawler crawler2 = new Crawler(140,740,3,room2.entities);
        room2.entities.add(crawler2);
        room1.entities.add(trans2);
        
        Door trans3 = new Door(0,640,trans2,1);
        trans2.exit = trans3;
        room2.entities.add(trans3);
        rooms.add(room2);
        
        Room room3 = new Room(1,2,3);
        Door trans4 = new Door(780,640,3);
        room2.entities.add(trans4);
        trans4.direction = RIGHT;
        
        Door trans5 = new Door(0,1440,trans4,2);
        trans4.exit = trans5;
        room3.entities.add(trans5);
        Wall plat1 = new Wall(660,1400,6,1);
        Wall plat2 = new Wall(500,1220,5,1);
        Wall plat3 = new Wall(240,1040,7,1);
        Wall plat4 = new Wall(0,860,5,1);
        Wall plat5 = new Wall(260,680,7,1);
        Wall plat6 = new Wall(0,500,5,1);
        Wall plat7 = new Wall(140,320,6,1);
        Wall plat8 = new Wall(240,160,28,1);
        room3.entities.add(plat1);
        room3.entities.add(plat2);
        room3.entities.add(plat3);
        room3.entities.add(plat4);
        room3.entities.add(plat5);
        room3.entities.add(plat6);
        room3.entities.add(plat7);
        room3.entities.add(plat8);
        Door trans6 = new Door(780,20,4);
        rooms.add(room3);
        
        Room room4 = new Room(1,2,4);
        Door trans7 = new Door(0,820,trans6,3);
        trans6.exit = trans7;
        trans6.direction = RIGHT;
        room4.entities.add(trans7);
        room3.entities.add(trans6);
        
        Pickup highJump = new Pickup(400,1480,5);
        room4.entities.add(highJump);
        rooms.add(room4);
        
        plat1 = new Wall(580,1340,10,1);
        room4.add(plat1);
        Wall platform = new Wall(260,1080,7,1);
        room4.add(platform);
        platform = new Wall(580,820,10,1);
        room4.add(platform);
        platform = new Wall(260,560,7,1);
        room4.add(platform);
        platform = new Wall(580,300,10,1);
        room4.add(platform);
        platform = new Wall(0,200,20,1);
        room4.add(platform);
        
        Door door1 = new Door(0,60,5);
        door1.direction = LEFT;
        Door door2 = new Door(3980,60,door1,4);
        door1.exit = door2;
        room4.add(door1);
        
        Room room5 = new Room(5,1,5);
        rooms.add(room5);
        room5.add(door2);
        platform = new Wall(3880,200,5,1);
        room5.add(platform);
        Pickup dashBoots = new Pickup(100,760,6);
        room5.add(dashBoots);
        LightningWall zap = new LightningWall(3700,580);
        room5.add(zap);
        platform = new Wall(3700,560,15,1);
        room5.add(platform);
        platform = new MovingWall(50,40,1000,40,6,1);
        platform.dx = 1;
        room5.add(platform);
       
        
        Room room6 = new Room(2,1,6);
        rooms.add(room6);
        door1 = new Door(0,640,6);
        room5.add(door1);
        door1.direction = LEFT;
        door2 = new Door(1580,640,door1,5);
        door1.exit = door2;
        room6.add(door2);
        platform = new Wall(0,220,15,1);
        room6.add(platform);
        platform = new Wall(400,440,10,1);
        room6.add(platform);
        platform = new Wall(700,660,10,1);
        room6.add(platform);
        Pickup teslaArmor = new Pickup(60,60,7);
        room6.add(teslaArmor);
        
        door1 = new SecretDoor(780,1440,7);
        room3.add(door1);
        door1.direction = RIGHT;
        door2 = new Door(0,640,door1,3);
        door1.exit = door2;
        Room room7 = new Room(1,1,7);
        rooms.add(room7);
        room7.add(door2);
        Pickup tempPickup = new Pickup(100,760,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(100,740,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(100,720,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(100,700,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(120,740,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(120,720,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(120,700,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(140,740,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(140,720,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(140,700,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(160,740,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(160,720,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(160,700,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(180,740,4);
        room7.add(tempPickup);
        tempPickup = new Pickup(180,720,3);
        room7.add(tempPickup);
        tempPickup = new Pickup(180,700,4);
        room7.add(tempPickup);
        
        Room room8 = new Room(1,1,8);
        rooms.add(room8);
        
        door1 = new Door(3980,640,8);
        door1.direction = RIGHT;
        room5.add(door1);
        door2 = new Door(0,640,door1,5);
        door1.exit = door2;
        room8.add(door2);
        

        setPreferredSize(new Dimension(winW,winH));
        timer = new Timer(DELAY, this);
        timer.start();
        
        entities = room0.entities;
        
        winX = 0;
        winY = 0;
    }
    
    public void addWall(Wall w){
        entities.add(w);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        checkJumpColl();
        updateJumper();
        updateShots();
        for(Entity en:entities){
            if(en.isEnemy()){
                updateEnemy((Enemy)en);
            }
            en.onTick();
        }
        entities.addAll(toAdd);
        toAdd = new ArrayList();
        keyPressed = false;
        keyReleased = false;
        cleanUp();
        repaint();
    
    }
    
    public void updateJumper(){
        if(keyPressed){
            jumper.keyPressed(pressed);
        }
        if(keyReleased){
            jumper.keyReleased(released);
        }
        jumper.move();
        updateWindow();
        jumper.fall();
        jumper.keyUpdate();
        if(jumper.currHealth <= 0){
            inGame = false;
        }
        if(currRoom.id == 8){
            won = true;
            inGame = false;
        }
    }
    
    public void updateWindow(){
        winX = jumper.x - winW / 2;
        winY = jumper.y - winH / 2;
        // check horizontal coordinates
        if(((winX + winW) > currRoom.right)||(winX < currRoom.left)){
            if((winX + winW) > currRoom.right){
                winX = currRoom.right - winW;
            }
            else{
                winX = currRoom.left;
            }
        }
        // check vertical coordinates
        if(((winY + winH) > currRoom.bottom)||((winY) < currRoom.top)){
            if((winY + winH) > currRoom.bottom){
                winY = currRoom.bottom - winH;
            }
            else{
                winY = currRoom.top;
            }
        }
    }
    
    public void updateEnemy(Enemy enemy){
        if(enemy.health <= 0){
            enemy.die();
        }
        checkEnemyColl(enemy);
        enemy.move();
        if(enemy.isTurret()){
            if(!entities.contains(((Turret)enemy).latestShot)){
                if(((Turret)enemy).latestShot != null){
                    toAdd.add(((Turret)enemy).latestShot);
                }
            }
        }
    }
    
    public void updateShots(){
        for(Projectile p:shots){
            checkProjColl();
            p.move();
            p.life--;
            if(p.life == 0){
                p.die();
            }
        }
    }
    
    private void checkJumpColl(){
        boolean collided = false;
        ArrayList<Entity> en = new ArrayList(entities);
        for(Entity e:en){
            Entity n = jumper.collisions(e);
            if (!(n.equals(jumper))){
                if(n.isEnemy()){
                    Enemy em = (Enemy) n;
                    if(!jumper.isInvincible){
                        if(!em.isElectric || !jumper.teslaArmor){
                            if(em.direction == RIGHT){
                                jumper.dy = -3;
                                jumper.dx = 10;
                                jumper.stunned = true;
                            }
                            else{
                                jumper.dy = -3;
                                jumper.dx = -10;
                                jumper.stunned = true;
                            }
                        }
                    }
                    jumper.damage(em.damage);
                }
                if(n.isDoor()){
                    Door d = (Door) n;
                    if(d.open){
                        entities.remove(jumper);
                        entities = rooms.get(d.room).entities;
                        entities.add(jumper);
                        currRoom = rooms.get(d.room);
                        jumper.changeDirection(d.direction);
                        jumper.shots = new ArrayList();
                        shots = jumper.shots;
                        if(d.direction == RIGHT){
                            jumper.x = d.exit.x + d.exit.width;
                            jumper.y = d.exit.y + 20;
                            jumper.dy = 0;
                            jumper.dx = 0;
                        }
                        else{
                            jumper.x = d.exit.x - jumper.width;
                            jumper.y = d.exit.y + 20;
                            jumper.dy = 0;
                            jumper.dx = 0;
                            
                        }
                        d.close();
                    }
                }
                if(n.isPickup()){
                    pickup(((Pickup)n).type);
                    n.isVisable = false;
                }
                if(e.isEnemy()){
                    Enemy em = (Enemy) e;
                    if(!em.isElectric || !jumper.teslaArmor){
                        jumper.collide(e);
                    }
                }
                else{
                    jumper.collide(n);
                }
                collided = true;
            }
        }
        if(!collided){
            jumper.isJumping = true;
        }
    }
    
    private void pickup(int n){
        switch(n){
            case 1:{
                jumper.heal(5);
                break;
            }
            
            case 2: {
                jumper.reload(5);
                break;
            }
            
            case 3: {
                jumper.maxHealth += 5;
                jumper.currHealth += 5;
                break;
            }
            
            case 4: {
                jumper.maxMissiles += 5;
                jumper.currMissiles += 5;
                break;
            }
            case 5: {
                jumper.jumpBoost = true;
                break;
            }
            case 6: {
                jumper.speedBoost = true;
                break;
            }
            case 7 : {
                jumper.teslaArmor = true;
                break;
            }
        }
    }
    
    private void checkEnemyColl(Enemy enemy){
        enemy.hasCollided = false;
        for(Entity e:entities){
            Entity n = enemy.collisions(e);
            if (!(n.equals(enemy))){
                if(n.equals(jumper)){
                    jumper.damage(enemy.damage);
                }
                enemy.collide(n);
            }
        }
    }
    
    private void checkProjColl(){
        for(Projectile p: shots){
            if(p.isVisable){
                for(Entity e :entities){
                    Entity n = p.collisions(e);
                    if(!(n.equals(p))){
                        if(n.isEnemy()){
                            Enemy en = (Enemy) n;
                            en.health -= p.damage;
                            en.stunCount = 5;
                            en.isStunned = true;
                            p.die();
                          }
                        if(n.isDoor()){
                            p.die();
                            if(((Door)n).isLocked()){
                                if(!(((LockedDoor)n).locked)){
                                    ((Door) n).open();
                                }
                            }
                            else{
                                ((Door)n).open();
                            }
                        }
                        else{
                            p.die();
                        }
                    }
                }
            }
        }
    }
    
    private void cleanUp(){
        ArrayList<Entity> copy = new ArrayList(entities);
        for(Entity e:copy){
            if(!e.isVisable){
                entities.remove(e);
            }
        }
        copy = new ArrayList(shots);
        for(Entity e:copy){
            if(!e.isVisable){
                shots.remove(e);
            }
        }
    }
    
    private void inGame() {
        
        if (!inGame) {
            timer.stop();
        }
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            doDrawing(g);
        }
        else{
            if(won){
                drawWon(g);
            }
            else{
                drawGameOver(g);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(Entity e: entities){
            if(e.isVisable){
                g2d.drawImage(e.image, e.x - winX,
                e.y - winY, this);
            }
        }
        for(Projectile p:shots){
            if(p.isVisable){
                g2d.drawImage(p.image, p.x - winX,
                p.y - winY, this);
            }
        }
        
        g.setColor(Color.white);
        Font small = new Font("Helvetica",Font.PLAIN,12);
        g.setFont(small);
        g.drawString("Health:" + jumper.currHealth + "/" + jumper.maxHealth, 10,10);
        g.drawString("Missiles:" + jumper.currMissiles + "/" + jumper.maxMissiles, 10,20);
        String s;
        if(jumper.missile){
            s = "Missile";
        }
        else{
            s = "Beam";
        }
        g.drawString(s,10,30);
    }
    
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (winW - fm.stringWidth(msg)) / 2,
                winH / 2);
    }
    
    private void drawWon(Graphics g){
        String msg = "You won";
        Font small = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.RED);
        g.setFont(small);
        g.drawString(msg, (winW - fm.stringWidth(msg)) / 2,
                winH / 2);
    }
    
    
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        //    jumper.keyReleased(e);
            keyReleased = true;
            released = e;
        }

        @Override
        public void keyPressed(KeyEvent e) {
        //    jumper.keyPressed(e);
            keyPressed = true;
            pressed = e;
        }
    }
    
    
}
