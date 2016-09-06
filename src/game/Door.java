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
public class Door extends Entity{
    public Door exit;
    // room the door is going to
    public int room;
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public static final String closedImage = "Door.png";
    public static final String openImage = "OpenDoor.png";
    // side of the room the door is on
    public boolean direction;
    public boolean open;
    
    public Door(int x,int y){
        super(x,y);
        open = false;
        loadImage(closedImage);
        getImageDimensions();
    }
    
    public Door(int x,int y,Door d){
        super(x,y);
        open = false;
        loadImage(closedImage);
        getImageDimensions();
        direction = !d.direction;
        exit = d;
        d.exit = this;
    }
    
    public Door(int x,int y,int r){
        super(x,y);
        open = false;
        loadImage(closedImage);
        getImageDimensions();
        room = r;
    }
    
    public Door(int x,int y,Door d,int r){
        super(x,y);
        open = false;
        loadImage(closedImage);
        getImageDimensions();
        direction = !d.direction;
        exit = d;
        room = r;
        d.exit = this;
    }
    
    public void open(){
        open = true;
        loadImage(openImage);
        getImageDimensions();
    }
    
    public void close(){
        open = false;
        loadImage(closedImage);
        getImageDimensions();
    }
    
    @Override
    public boolean isDoor(){
        return true;
    }
    
    public boolean isLocked(){
        return false;
    }
    
}
