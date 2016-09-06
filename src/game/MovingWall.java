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
public class MovingWall extends Wall{
    int startX;
    int startY;
    int endX;
    int endY;
    Jumper jumper;
    
    public MovingWall(int x1,int y1,int x2,int y2,int w,int h){
        super(x1,y1,w,h);
        startX = x1;
        startY = y1;
        endX = x2;
        endY = y2;
        jumper = null;
    }
    
    @Override
    public void onTick(){
        move();
        if((x==startX && y == startY)||(x==endX && y == endY)){
            dx = dx * -1;
            dy = dy * -1;
        }
        if (jumper != null){
            if(jumper.isJumping){
                jumper = null;
            }
            else{
                jumper.x += dx;
                jumper.y += dy;
            }
        }
    }
    
    @Override
    public boolean isMovingWall(){
        return true;
    }
}
