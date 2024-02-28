package org.example;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bee {
    public int x;
    public int y;
    public int xBeeSpawnLocation;
    public int yBeeSpawnLocation;
    public static final int beeSize = 15;
    public int xVelocity = 1;
    public int yVelocity = 1;
    public int storedNectar;
    public static final int nectarGrabAmount = 20;
    public final static int maximumNectarStored = 20;
    Flower selectedFlower;
    boolean hasSelectedFlower;
    boolean readyToDepositNectar;
    boolean isRandomized = false;
    Random random;
    int height = 20;
    int width = 20;
    private static Image rightBeeImage;
    private static Image leftBeeImage;
    static {
        try {
            rightBeeImage = ImageIO.read(new File("src/main/resources/smallbee.png"));
            leftBeeImage = ImageIO.read(new File("src/main/resources/smallbeeRight.png"));
        } catch (IOException e) {
            e.printStackTrace();
            rightBeeImage = null;
            leftBeeImage = null;
        }
    }
    Bee(){
        random = new Random();
        beeSpawnRandomizer();
        hasSelectedFlower = false;
        readyToDepositNectar = false;
    }
    public void beeSpawnRandomizer(){
        x = random.nextInt(Hive.x + 2*beeSize, Hive.x + Hive.size - 2*beeSize);
        y = random.nextInt(Hive.y + 2*beeSize, Hive.y + Hive.size - 2*beeSize);
        xBeeSpawnLocation = x;
        yBeeSpawnLocation = y;
    }
    public void paintBee(Graphics g){
           
            if (x >= 400 && !readyToDepositNectar) { // pszczółka leci w prawo
                g.drawImage(rightBeeImage, x, y, null);
            } else if (x <= 400 && !readyToDepositNectar) { // pszczółka leci w lewo
                g.drawImage(leftBeeImage, x, y, null);
            } else if (x >= 400 && readyToDepositNectar) {
                g.drawImage(leftBeeImage, x, y, null);
            } else if (x <= 400 && readyToDepositNectar) {
                g.drawImage(rightBeeImage, x, y, null);
            }
    }
    public void getFlower(Flower flower){
        flower.isOccupiedByBee = true;
        hasSelectedFlower = true;
        selectedFlower = flower;
    }
    public void beeMove(){
        //move to selected flower
        if(hasSelectedFlower && !readyToDepositNectar){
            if(selectedFlower.x > x){
                x += xVelocity;
            }
            else if(selectedFlower.x < x){
                x -= xVelocity;
            }
            if(selectedFlower.y > y){
                y += yVelocity;
            }
            else if(selectedFlower.y < y){
                y -= yVelocity;
            }
        }
        //come back to hive
        if(hasSelectedFlower && readyToDepositNectar){
            if(xBeeSpawnLocation > x){
                x += xVelocity;
            }
            else if(xBeeSpawnLocation < x){
                x -= xVelocity;
            }
            if(yBeeSpawnLocation > y){
                y += yVelocity;
            }
            else if(yBeeSpawnLocation < y){
                y -= yVelocity;
            }
        }
        //no flowers left
        if(!hasSelectedFlower && !readyToDepositNectar){
            if(!isRandomized){
                beeVelocityRandomizer();
            }
            if(x > Panel.PANEL_WIDTH - beeSize || x < 0 ){
                xVelocity *= -1;
            }
            if(y > Panel.PANEL_HEIGHT - beeSize || y < 0){
                yVelocity *= -1;
            }
            x += xVelocity;
            y += yVelocity;
        }
    }
    //used to randomize x and y velocity after no flowers are left
    public void beeVelocityRandomizer(){
        xVelocity = random.nextInt(-2,2);
        yVelocity = random.nextInt(-2,2);
        while(xVelocity == 0 && yVelocity == 0){
            xVelocity = random.nextInt(-2,2);
            yVelocity = random.nextInt(-2,2);
        }
        isRandomized = true;
    }
    public void getNectar(Flower flower){
        if(storedNectar + nectarGrabAmount <= maximumNectarStored && flower.nectarAmount > 0 && selectedFlower == flower){
            flower.nectarAmount -= nectarGrabAmount;
            storedNectar += nectarGrabAmount;
            readyToDepositNectar = true;
        }
    }

    public void giveNectarToHive(Hive hive){
        if(hasSelectedFlower && readyToDepositNectar){
            hive.todayStoredNectar += storedNectar;
            storedNectar = 0;
            hasSelectedFlower = false;
            readyToDepositNectar = false;
        }
    }
}
