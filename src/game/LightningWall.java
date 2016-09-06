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
public class LightningWall extends Enemy{
    public int aniCount;
    public static final int MAXCOUNT = 4;
    
    public LightningWall(int x,int y){
        super(x,y,"LightningWall1");
        health = 10000000;
        isElectric = true;
        aniCount = 0;
    }
    
    
    @Override
    public void onTick(){
        if(health < 10000000){
            health += 500;
        }
        if(aniCount > MAXCOUNT/2){
            loadImage("LightningWall2.png");
            getImageDimensions();
            if(aniCount < MAXCOUNT){
                aniCount++;
            }
            else{
                aniCount = 0;
            }
        }
        else{
            loadImage("LightningWall1.png");
            getImageDimensions();
            aniCount++;
        }
    }
    
}
