/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author Zach Wilson
 */
public class Wall extends Entity{
    
    public Wall(int x,int y,int width,int height){
        super(x,y);
        image = makeImage(width,height);
        getImageDimensions();
    }
    
    private Image makeImage(int cols,int rows){  
        int chunks = rows * cols;
        Random rand = new Random();
  
        int chunkWidth, chunkHeight;  
        int type;  
        File[] imgFiles = new File[chunks];  
        for (int i = 0; i < chunks; i++) {
            int r = rand.nextInt();
            if(r%2 == 0){
                imgFiles[i] = new File("GrayBlock1.png");
            }
            else{
                imgFiles[i] = new File("GrayBlock2.png");
            }
        }  
  
        BufferedImage[] buffImages = new BufferedImage[chunks];  
        for (int i = 0; i < chunks; i++) {
            try{
                buffImages[i] = ImageIO.read(imgFiles[i]);
            }
            catch (IOException e) {
                System.err.println("Caught IOException: " + e.getMessage());
            }
        }  
        type = buffImages[0].getType();  
        chunkWidth = buffImages[0].getWidth();  
        chunkHeight = buffImages[0].getHeight();  
   
        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);  
  
        int num = 0;  
        for (int i = 0; i < rows; i++) {  
            for (int j = 0; j < cols; j++) {  
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);  
                num++;  
            }  
        } 
        return finalImg;
    }
    
    @Override
    public boolean isWall(){
        return true;
    }
    
}
