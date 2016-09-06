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
import java.util.ArrayList;

public class Room {
    public ArrayList<Entity> entities;
    public int id;
    public int top;
    public int bottom;
    public int right;
    public int left;
    public Room(){
        entities = new ArrayList();
    }
    
    public Room(int w,int h,int id){
        this.id = id;
        entities = new ArrayList();
        Wall ceiling = new Wall(0,0,42 * w,1);
        Wall floor = new Wall(0,(800 * h) - 20,42 * w,1);
        Wall leftW = new Wall(0,0,1,40 * h);
        Wall rightW = new Wall((800 * w)-20,0,1,40 * h);
        
        top = 0;
        bottom = 800 * h;
        left = 0;
        right = 800 * w;
        
        entities.add(ceiling);
        entities.add(floor);
        entities.add(leftW);
        entities.add(rightW);
    }
    
    public void add(Entity e){
        entities.add(e);
    }
    
}
