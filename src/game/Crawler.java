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
public class Crawler extends Enemy{
    public int picCount;
    private ArrayList<Entity> entities;
    private Entity currWall;
    // 1 is right, 2 is down, 3 is left, 0 is up 
    public int direc;
    public int orientation;
    // used to tell if the state of the crawler has changed
    private boolean changed;
    // true is clockwise,false is counter clockwise
    public Crawler(int x,int y,int direc,ArrayList array){
        super(x,y);
        this.direc = direc;
        orientation = 0;
        changed = false;
        entities = array;
        picCount = 0;
        loadImage("NewCrawlerUp1.png");
        getImageDimensions();
        health = 5;
        isElectric = true;
    }
    
    
    @Override
    public void onTick(){
        if(!hasCollided){
            outsideTurn();
        }
        animate();
        switch(direc){
            case 0 : {
                dx = 0;
                dy = -2;
                break;
            }
            case 1 : {
                dy = 0;
                dx = 2;
                break;
            }
            case 2 : {
                dx = 0;
                dy = 2;
                break;
            }
            case 3 : {
                dx = -2;
                dy = 0;
                break;
            }
            
        }
        
        
        if(isStunned){
            stunCount += 1;
            if(stunCount == 15){
                stunCount = 0;
                isStunned = false;
            }
        }
        changed = false;
    }
    
    public void animate(){
        switch(orientation){
            case 0: {
                if(picCount > 3){
                    loadImage("NewCrawlerUp2.png");
                    getImageDimensions();
                    if(picCount == 6){
                        picCount = 0;
                    }
                    else{
                        picCount ++;
                    }
                }
                else{
                    loadImage("NewCrawlerUp1.png");
                    getImageDimensions();
                    picCount++;
                }
                break;
            }
            case 1: {
                if(picCount > 3){
                    loadImage("NewCrawlerRight2.png");
                    getImageDimensions();
                    if(picCount == 6){
                        picCount = 0;
                    }
                    else{
                        picCount ++;
                    }
                }
                else{
                    loadImage("NewCrawlerRight1.png");
                    getImageDimensions();
                    picCount++;
                }
                break;
            }
            case 2: {
                if(picCount > 3){
                    loadImage("NewCrawlerDown2.png");
                    getImageDimensions();
                    if(picCount == 6){
                        picCount = 0;
                    }
                    else{
                        picCount ++;
                    }
                }
                else{
                    loadImage("NewCrawlerDown1.png");
                    getImageDimensions();
                    picCount++;
                }
                break;
            }
            case 3: {
                if(picCount > 3){
                    loadImage("NewCrawlerLeft2.png");
                    getImageDimensions();
                    if(picCount == 6){
                        picCount = 0;
                    }
                    else{
                        picCount ++;
                    }
                }
                else{
                    loadImage("NewCrawlerLeft1.png");
                    getImageDimensions();
                    picCount++;
                }
                break;
            }
        }
    }
    
    @Override
    public void collide(Entity e){
        if(e == this){
            return;
        }
        if(e.isJumper()){
            return;
        }
        if(e.isEnemy()){
            return;
        }
        if(e.isWall() || e.isDoor()){
            if(currWall == null){
                currWall = e;
            }
            hasCollided = true;
            int r1Top = y+dy;
            int r1Bottom = y+dy+height;
            int r1Left = x+dx;
            int r1Right = x+dx+width;

            int r2Top = e.y;
            int r2Bottom = e.y+e.height;
            int r2Left = e.x;
            int r2Right = e.x+e.width;

            int oldr1Top = y;
            int oldr1Bottom = y+height;
            int oldr1Left = x;
            int oldr1Right = x+width;

            if(r1Top < r2Bottom && !(oldr1Top < r2Bottom)){
                //raising collision
                if(! e.equals(currWall)){
                    if(direc != 1 && direc != 3 && !changed){
                        if(orientation == 1){
                            clockWiseTurn();
                        }
                        else{
                            counterClockWiseTurn();
                        }
                    }
                }
                dy = 0;
                y = e.y + e.height;

            }
            if(r1Bottom > r2Top && !(oldr1Bottom > r2Top)){
                //falling collision
                if(! e.equals(currWall)){
                    if(direc != 1 && direc != 3 && !changed){
                        if(orientation == 3){
                            clockWiseTurn();
                        }
                        else{
                            counterClockWiseTurn();
                        }
                    }
                }
                dy = 0;
                y = e.y - height;

            }

            if(r1Right > r2Left && !(oldr1Right > r2Left)){
                //right collision
                if(! e.equals(currWall)){
                    if(direc != 2 && direc != 0 && !changed){
                        if(orientation == 2){
                            clockWiseTurn();
                        }
                        else{
                            counterClockWiseTurn();
                        }
                    }
                }
                x = e.x - width;
                direction = LEFT;

            }
            if(r1Left < r2Right && !(oldr1Left < r2Right)){
                //left collision
                if(! e.equals(currWall)){
                    if(direc != 2 && direc != 0 && !changed){
                        if(orientation == 0){
                            clockWiseTurn();
                        }
                        else{
                            counterClockWiseTurn();
                        }
                    }
                }
                x = e.x + e.width;
                direction = RIGHT;

            }
            if(currWall != e && !changed){
                currWall = e;
            }

        }
        
    }
    
    public void clockWiseTurn(){
        direc = (direc + 1) % 4;
        orientation = (orientation + 1) % 4;
        changed = true;
    }
    
    public void counterClockWiseTurn(){
//        direc = (direc - 1) % 4;
//        orientation = (orientation - 1) % 4;
        direc = (((direc - 1) % 4) + 4) % 4;
        orientation = (((orientation - 1) % 4) + 4) % 4;
        changed = true;
    }
    
    public void outsideTurn(){
        if(direc == 0){
            if(this.y < currWall.y - this.height){
                if(orientation == 3){
                    clockWiseTurn();
                }
                else{
                    counterClockWiseTurn();
                }
                this.y = currWall.y - this.height;
            }
        }
        else if(direc == 1){
            if(this.x > currWall.x + currWall.width){
                if(orientation == 0){
                    clockWiseTurn();
                }
                else{
                    counterClockWiseTurn();
                }
                this.x = currWall.x + currWall.width;
            }
        }
        else if(direc == 2){
            if(this.y > currWall.y + currWall.height){
                if(orientation == 1){
                    clockWiseTurn();
                }
                else{
                    counterClockWiseTurn();
                }
                this.y = currWall.y + currWall.height;
            }
        }
        else if(direc == 3){
            if(this.x < currWall.x - this.width){
                if(orientation == 2){
                    clockWiseTurn();
                }
                else{
                    counterClockWiseTurn();
                }
                this.x = currWall.x - this.width;
            }
        }
    }
    
}
