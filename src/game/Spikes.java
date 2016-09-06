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
public class Spikes extends Enemy{
    public Spikes(int x,int y,int direction){
        super(x,y);
        // 1 is right, 2 is down, 3 is left, 0 is up 
        if(direction == 1){
            loadImage("RightSpikes.png");
            getImageDimensions();
        }
        if(direction == 2){
            loadImage("DownSpikes.png");
            getImageDimensions();
        }
        if(direction == 3){
            loadImage("LeftSpikes.png");
            getImageDimensions();
        }
        if(direction == 0){
            loadImage("UpSpikes.png");
            getImageDimensions();
        }
        damage = 7;
    }
    
    @Override
    public void onTick(){}
    
}
