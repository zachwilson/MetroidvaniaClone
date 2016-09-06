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
public class SecretDoor extends Door{
    public static final String closedImage = "SecretDoor.png";
    public static final String openImage = "OpenSecretDoor.png";
    
    public SecretDoor(int x,int y){
        super(x,y);
        loadImage(closedImage);
        getImageDimensions();
    }
    
    public SecretDoor(int x,int y,Door d){
        super(x,y,d);
        loadImage(closedImage);
        getImageDimensions();
    }
    
    public SecretDoor(int x,int y,int r){
        super(x,y,r);
        loadImage(closedImage);
        getImageDimensions();
    }
    
    public SecretDoor(int x,int y,Door d,int r){
        super(x,y,d,r);
        loadImage(closedImage);
        getImageDimensions();
    }
    
    
    @Override
    public void open(){
        open = true;
        loadImage(openImage);
        getImageDimensions();
    }
    
    
    @Override
    public void close(){
        open = false;
        loadImage(closedImage);
        getImageDimensions();
    }
    
}
