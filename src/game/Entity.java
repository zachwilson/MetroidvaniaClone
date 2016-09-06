/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Zach Wilson
 */
public class Entity {
    
    public boolean isVisable;
    public boolean isTransparent;
    public boolean hasCollided;
    public int x; // position in world
    public int y; // position in world
    public int height;
    public int width;
    public int dx;
    public int dy;
    public Image image;
    
    public Entity(int x,int y){
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        isVisable = true;
        isTransparent = false;
    }
    
    public void move(){
        x += dx;
        y += dy;
    }
    
    public void loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public Entity collisions(Entity e){
        int r1Top = y+dy;
        int r1Bottom = y+dy+height;
        int r1Left = x+dx;
        int r1Right = x+dx+width;
        
        int r2Top = e.y;
        int r2Bottom = e.y+e.height;
        int r2Left = e.x;
        int r2Right = e.x+e.width;
        if (!(r2Left > r1Right || r2Right < r1Left || r2Top > r1Bottom || r2Bottom < r1Top)&&(!isTransparent)){
            return e;
        }
        return this;
    }
    
    public boolean isEnemy(){
        return false;
    }
    
    public boolean isJumper(){
        return false;
    }
    
    public boolean isProjectile(){
        return false;
    }
    
    public boolean isDoor(){
        return false;
    }
    
    public boolean isPickup(){
        return false;
    }
    
    public boolean isWall(){
        return false;
    }
    
    public boolean isMovingWall(){
        return false;
    }
    
    public void die(){
        isVisable = false;
    }
    
    public void onTick(){
    }
    
    
    
}
