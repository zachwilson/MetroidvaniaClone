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
public class Pickup extends Entity{
    public int type;
    
    public Pickup(int x,int y,int z){
        super(x,y);
        isTransparent = true;
        type = z;
        switch(type){
            case 1: {
                loadImage("Heart.png");
                getImageDimensions();
                break;
            }
                
            case 2: {
                loadImage("LeftMissile.png");
                getImageDimensions();
                break;
            }
            
            case 3: {
                loadImage("HealthTank.png");
                getImageDimensions();
                break;
            }
            
            case 4: {
                loadImage("MissileTank.png");
                getImageDimensions();
                break;
            }
            case 5 : {
                loadImage("HighJumpBoot.png");
                getImageDimensions();
                break;
            }
            case 6 : {
                loadImage("DashBoot.png");
                getImageDimensions();
                break;
            }
            case 7 : {
                loadImage("TeslaSuitPickup.png");
                getImageDimensions();
                break;
            }
            
            
        }
    }
    
    @Override
    public boolean isPickup(){
        return true;
    }
    
}
