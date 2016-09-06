/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Zach Wilson
 */
public class Enemy extends Entity{
    
    public boolean direction;
    public boolean isFalling;
    public boolean isStunned;
    public boolean isElectric;
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public int health;
    public int damage;
    public int jumpCount;
    public int stunCount;
    
    public Enemy(int x,int y){
        super(x,y);
        isFalling = false;
        isStunned = false;
        jumpCount = 0;
        health = 3;
        damage = 1;
        loadImage("BlueRec.png");
        getImageDimensions();
    }
    
    public Enemy(int x,int y,String s){
        super(x,y);
        isFalling = false;
        jumpCount = 0;
        health = 5;
        loadImage(s);
        getImageDimensions();
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
            
        }
        if(r1Bottom > r2Top && !(oldr1Bottom > r2Top)){
            //falling collision
            dy = 0;
            y = e.y - height;
            isFalling = false;
            
        }
        
        if(r1Right > r2Left && !(oldr1Right > r2Left)){
            //right collision
            dx = 0;
            x = e.x - width - 1;
            direction = LEFT;
            
        }
        if(r1Left < r2Right && !(oldr1Left < r2Right)){
            //left collision
            dx = 0;
            x = e.x + e.width + 1;
            direction = RIGHT;
            
        }
        
    }
    
    public void fall(){
        if (isFalling){
            if(jumpCount == 0){
                dy += 1;
                jumpCount = 1;
            }
            else{
                jumpCount--;
            }
        }
    }
    
    @Override
    public void move(){
        if(!isStunned){
            super.move();
        }
    }
    
    public void onTick(){
        if(direction){
            dx = 2;
        }
        else{
            dx = -2;
        }
        if(isStunned){
            stunCount -= 1;
            if(stunCount == 0){
                isStunned = false;
            }
        }
        fall();
    }
    
    @Override
    public boolean isEnemy(){
        return true;
    }
    
    public boolean isTurret(){
        return false;
    }
    
}
