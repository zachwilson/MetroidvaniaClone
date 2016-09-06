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
public class Turret extends Enemy{
    public int orientation;
    public Entity target;
    public TurretShot latestShot;
    public int wait;
    
    public Turret(int x,int y,Entity t){
        super(x,y,"ProtoTurret.png");
        wait = 0;
        target = t;
    }
    
    public TurretShot fire(){
        TurretShot shot = new TurretShot(x+5,y+25);
        for(int t = 0; t < 200 ;t++){
            for(int deltaX = -7;deltaX < 7;deltaX++){
                int deltaY = (int) Math.floor(Math.sqrt(49-(deltaX^2)));
                int d1x = target.dx/2 * t + target.x;
                int d1y = target.dy/2 * t + target.y;
                int d2x = deltaX * t + shot.x;
                int d2y = deltaY * t + shot.y;
                double e = 30;
                if(Math.abs(d1x-d2x)<e && Math.abs(d1y-d2y)<e){
                    shot.dx = deltaX;
                    shot.dy = deltaY;
                    return shot;
                }
            }
        }
        
//        int deltaY = shot.y - target.y;
//        int deltaX = shot.x - target.x;
//        shot.dy = (int) (-1 * Math.floor(deltaY/20));
//        shot.dx = (int) (-1 * Math.floor(deltaX/20));
        return null;
    }
    
    @Override
    public void onTick(){
        if(wait < 50){
            wait++;
        }
        else{
            latestShot = fire();
            wait = 0;
        }
    }
    
    @Override
    public boolean isTurret(){
        return true;
    }
    
}
