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
public class TurretShot extends Enemy{
    int life;
    public TurretShot(int x,int y){
        super(x,y,"TurretShot.png");
        damage = 2;
    }
    
    @Override
    public void onTick(){
        life++;
    }
    
    @Override
    public void collide(Entity e){
        if(life > 3){
            isVisable = false;
        }
    }
}
