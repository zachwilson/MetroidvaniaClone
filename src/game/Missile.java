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
public class Missile extends Projectile{
    
    public Missile (int x,int y,boolean direction,int life){
        super(x,y,life);
        if(direction == RIGHT){
            dx = 8;
            loadImage("RightMissile.png");
            getImageDimensions();
        }
        else{
            loadImage("LeftMissile.png");
            getImageDimensions();
            dx = -8;
        }
        damage = 5;
    }
    
}
