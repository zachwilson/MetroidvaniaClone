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
public class UpMissile extends Projectile{
    public UpMissile (int x,int y,int life){
        super(x,y,life);
        dy = -8;
        loadImage("UpMissile.png");
        getImageDimensions();
        damage = 5;
    }
    
}
