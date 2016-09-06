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
public class Projectile extends Entity{
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public int maxTime;
    public int currTime;
    public int damage;
    public int life;
    
    public Projectile (int x,int y,int life){
        super(x,y);
        this.life = life;
    }
    
    @Override
    public boolean isProjectile(){
        return true;
    }
    
}
