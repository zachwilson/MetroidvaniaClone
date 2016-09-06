/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import java.util.ArrayList;
/**
 *
 * @author Zach Wilson
 */
public class LockedDoor extends Door{
    public boolean locked;
    public ArrayList<Enemy> targets;
    
    public LockedDoor(int x,int y){
        super(x,y);
        locked = true;
    }
    
    public LockedDoor(int x,int y,Door d){
        super(x,y,d);
        locked = true;
    }
    
    public LockedDoor(int x,int y,int id){
        super(x,y,id);
        locked = true;
    }
    
    public LockedDoor(int x,int y,Door d,int id){
        super(x,y,d,id);
        locked = true;
    }
    
    @Override
    public void onTick(){
        boolean living = false;
        for(Enemy e:targets){
            if(e.isVisable){
                living = true;
            }
        }
        if(!living){
            locked = false;
        }
    }
    
    @Override
    public boolean isLocked(){
        return true;
    }
    
}
