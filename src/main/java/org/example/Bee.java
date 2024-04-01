package org.example;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Bee {

    private int x;
    private int y;
    private int xBeeSpawnLocation;
    private int yBeeSpawnLocation;
    private static final int beeSize = 15;
    private int xVelocity = 1;
    private int yVelocity = 1;
    private int storedNectar;
    private static final int nectarGrabAmount = 20;
    private final static int maximumNectarStored = 20;
    Flower selectedFlower;
    private boolean hasSelectedFlower;
    private boolean readyToDepositNectar;
    private boolean beeVelocityisRandomized = false;
    Random random;
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

    public Bee(){
        random = new Random();
        beeSpawnRandomizer();
        hasSelectedFlower = false;
        readyToDepositNectar = false;
    }

    private void beeSpawnRandomizer(){
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
        flower.setIsOccupiedByBeeToTrue();
        hasSelectedFlower = true;
        selectedFlower = flower;
    }

    public void beeMove(){
        //move to selected flower
        if(hasSelectedFlower && !readyToDepositNectar){
            if(selectedFlower.getX() > x){
                x += xVelocity;
            }
            else if(selectedFlower.getX() < x){
                x -= xVelocity;
            }
            if(selectedFlower.getY() > y){
                y += yVelocity;
            }
            else if(selectedFlower.getY() < y){
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
            if(!beeVelocityisRandomized){
                beeVelocityRandomizer();
            }
            if(x > Panel.PANEL_WIDTH - beeSize - 25 || x < -25 ){
                xVelocity *= -1;
            }
            if(y > Panel.PANEL_HEIGHT - beeSize  - 35 || y < -25){
                yVelocity *= -1;
            }
            x += xVelocity;
            y += yVelocity;
        }
    }

    //used to randomize x and y velocity after no flowers are left
    private void beeVelocityRandomizer(){
        xVelocity = random.nextInt(-2,2);
        yVelocity = random.nextInt(-2,2);
        while(xVelocity == 0 && yVelocity == 0){
            xVelocity = random.nextInt(-2,2);
            yVelocity = random.nextInt(-2,2);

        }
        beeVelocityisRandomized = true;
    }

    public void getNectar(Flower flower){
        if(storedNectar + nectarGrabAmount <= maximumNectarStored && flower.getNectarAmount() > 0 && selectedFlower == flower){
            //flower.nectarAmount -= nectarGrabAmount;
            flower.setNectarAmount(flower.getNectarAmount()-nectarGrabAmount);
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

    public int getxBeeSpawnLocation(){
        return xBeeSpawnLocation;
    }

    public int getyBeeSpawnLocation(){
        return yBeeSpawnLocation;
    }

    public int getxVelocity(){
        return xVelocity;
    }

    public int getyVelocity(){
        return yVelocity;
    }

    public boolean getbeeVelocityisRandomized(){
        return beeVelocityisRandomized;
    }

    public boolean getHasSelectedFlower(){
        return hasSelectedFlower;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public static int getBeeSize(){
        return beeSize;
    }

}