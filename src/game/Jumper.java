/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 *
 * @author Zach Wilson
 */
public class Jumper extends Entity{
    
    public ArrayList<KeyEvent> pressed;
    private static final String RIGHTIMAGE = "SeventhJumperRight.png";
    private static final String LEFTIMAGE = "SeventhJumperLeft.png";
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public static final int GRAVITY = 1;
    public int antiGravity;
    public boolean leftArrow;
    public boolean rightArrow;
    public boolean upArrow;
    public boolean spaceBar;
    public boolean shift;
    public boolean direction;
    public boolean isJumping;
    public boolean jetPack;
    public boolean speedBoost;
    public boolean jumpBoost;
    public boolean missile;
    public boolean isInvincible;
    public boolean aimUp;
    public boolean longBeam;
    public boolean teslaArmor;
    public boolean stunned;
    public int boostCounter;
    public int jetPackTimer;
    public int gravCount;
    public int invincibleCount;
    private int count;
    private int fallCount;
    public int currHealth;
    public int maxHealth;
    public int currMissiles;
    public int maxMissiles;
    public ArrayList<Projectile> shots;
    
    public Jumper(int x,int y){
        super(x,y);
        shots = new ArrayList();
        pressed = new ArrayList();
        isJumping = false;
        isInvincible = false;
        jumpBoost = false;
        speedBoost = false;
        boostCounter = 0;
        count = 0;
        antiGravity = 0;
        jetPackTimer = 0;
        maxMissiles = 5;
        currMissiles = 5;
        maxHealth = 10;
        currHealth = 10;
        invincibleCount = 0;
        fallCount = 0;
        direction = RIGHT;
        missile = false;
        aimUp = false;
        longBeam = false;
        teslaArmor = false;
        stunned = false;
        loadImage(RIGHTIMAGE);
        getImageDimensions();
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if(!isJumping && !stunned){
                dx = 0;
            }
            boostCounter = 0;
            leftArrow = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(!isJumping&& !stunned){
                dx = 0;
            }
            boostCounter = 0;
            rightArrow = false;
        }
        
        if(key == KeyEvent.VK_S){
            upArrow = false;
            jetPack = false;
            jetPackTimer = 0;
            antiGravity = 0;
        }
        if(key == KeyEvent.VK_A){
            spaceBar = false;
        }
        
        if(key == KeyEvent.VK_SHIFT){
            shift = false;
        }
        if(key == KeyEvent.VK_UP){
            aimUp = false;
            upArrow = false;
        }
    }
    
    public void changeDirection(boolean d){
        direction = d;
        if(d == RIGHT){
            loadImage(RIGHTIMAGE);
            getImageDimensions();
        }
        else{
            loadImage(LEFTIMAGE);
            getImageDimensions();
        }
    }
    
    public void keyUpdate(){
        if(leftArrow && !stunned){
            if(speedBoost){
                if(boostCounter >= 100){
                    dx = -10;
                }
                else{
                    dx = -6;
                    boostCounter++;
                }
            }
            else{
                dx = -6;
            }
        }
        if(rightArrow && !stunned){
            if(speedBoost){
                if(boostCounter >= 100){
                    dx = 10;
                }
                else{
                    dx = 6;
                    boostCounter++;
                }
            }
            else{
                dx = 6;
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            changeDirection(LEFT);
            leftArrow = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            changeDirection(RIGHT);
            rightArrow = true;
        }

        if (key == KeyEvent.VK_S) {
            if(!isJumping || fallCount < 4){
                if(!upArrow){
                    isJumping = true;
                    dy = -15;
                    jetPack = true;
                    if(jumpBoost){
                        jetPackTimer = 17;
                    }
                    else{
                        jetPackTimer = 10;
                    }
                }
            }
        }
        
        if (key == KeyEvent.VK_A){
            if(missile){
                if(currMissiles > 0){
                    if(aimUp){
                        shots.add (new UpMissile(x + width/2,y - 13,1000));
                    }
                    else{
                        if(direction == RIGHT){
                            shots.add(new Missile(x + width,y + 20,direction,1000));
                        }
                        else{
                            shots.add(new Missile(x - 11,y + 20,direction,1000));  
                        }
                    }
                    currMissiles--;
                }
            }
            else{
                if(longBeam){
                    if(aimUp){
                        shots.add(new UpBeam(x + width/2,y - 20,200));
                    }
                    else{
                        if(direction == RIGHT){
                            shots.add(new Beam(x + width,y + 15,direction,200));
                        }
                        else{
                            shots.add(new Beam(x - 18,y + 15,direction,200));  
                        }
                    }
                }
                else{
                    if(aimUp){
                        shots.add(new UpBeam(x + width/2,y - 20,50));
                    }
                    else{
                        if(direction == RIGHT){
                            shots.add(new Beam(x + width,y + 15,direction,50));
                        }
                        else{
                            shots.add(new Beam(x - 18,y + 15,direction,50));  
                        }
                    }
                    
                }
            }
            spaceBar = true;
        }
        
        if (key == KeyEvent.VK_SHIFT){
            shift = true;
            missile = !missile;
        }
        
        if(key == KeyEvent.VK_UP){
            upArrow = true;
            aimUp = true;
        }
        
    }
    
    public void collide(Entity e){
        int r1Top = y+dy;
        int r1Bottom = y+dy+height;
        int r1Left = x+dx;
        int r1Right = x+dx+width;
        
        int r2Top = e.y;
        int r2Bottom = e.y+e.height;
        int r2Left = e.x;
        int r2Right = e.x+e.width;
        
        int oldr1Top = y;
        int oldr1Bottom = y+height;
        int oldr1Left = x;
        int oldr1Right = x+width;
        
        
        if(r1Top < r2Bottom && !(oldr1Top < r2Bottom)){
            //raising collision
            dy = 0;
            y = e.y + e.height;
            jetPack = false;
            antiGravity = 0;
            stunned = false;
            
        }
        if(r1Bottom > r2Top && !(oldr1Bottom > r2Top)){
            //falling collision
            dy = 0;
            if(isJumping){
                dx = 0;
            }
            y = e.y - height;
            count = 0;
            fallCount = 0;
            isJumping = false;
            stunned = false;
            if(e.isMovingWall()){
                ((MovingWall)e).jumper = this;
//                dx += e.dx;
//                dy += e.dy;
            }
            
        }
        
        if(r1Right > r2Left && !(oldr1Right > r2Left)){
            //right collision
            dx = 0;
            x = e.x - width; 
            stunned = false;
            
        }
        if(r1Left < r2Right && !(oldr1Left < r2Right)){
            //left collision
            dx = 0;
            x = e.x + e.width;
            stunned = false;
            
        }
        
    }
    
    public void heal(int i){
        if(currHealth + i <= maxHealth){
            currHealth += i;
        }
        else{
            currHealth = maxHealth;
        }
    }
    
    public void reload(int i){
        if(currMissiles + i <= maxMissiles){
            currMissiles += i;
        }
        else{
            currMissiles = maxMissiles;
        }
    }
    
    public void fall(){
        System.out.println(isJumping);
        if(isJumping){
            fallCount++;
            if(jetPack){
                jetPackTimer--;
                if(gravCount == 1){
                    antiGravity += 1;
                    gravCount = 0;
                }
                else{
                    gravCount ++;
                }
                if(jetPackTimer <= 0){
                    jetPack = false;
                    antiGravity = 0;
                }
            }
            if(count >= (GRAVITY + antiGravity)){
                if(dy < 30){
                    dy += 2;
                }
                count = 0;
            }
            else{
                count ++;
            }
        }
    }
    
    @Override
    public boolean isJumper(){
        return true;
    }
    
    public void damage(int i){
        if(!isInvincible){
            if(teslaArmor){
                currHealth -= i/2;
                isInvincible = true;
                invincibleCount = 30;
            }
            else{
                currHealth -= i;
                isInvincible = true;
                invincibleCount = 30;
            }
        }
    }
    
    public void onTick(){
        if(invincibleCount > 0){
            invincibleCount--;
        }
        else{
            isInvincible = false;
        }
    }
    
    
}
