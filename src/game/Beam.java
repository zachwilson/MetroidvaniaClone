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
public class Beam extends Projectile{
    public Beam (int x,int y,boolean direction,int life){
        super(x,y,life);
        if(direction == RIGHT){
            dx = 10;
            loadImage("RightBeam.png");
            getImageDimensions();
        }
        else{
            loadImage("LeftBeam.png");
            getImageDimensions();
            dx = -10;
        }
        damage = 1;
    }
}
