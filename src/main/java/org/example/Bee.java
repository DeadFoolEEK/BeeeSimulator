package org.example;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Bee class, responsible for bees
 */
public class Bee {
    /**
     * Bee horizontal coordinate
     */
    private int x;
    /**
     * Bee vertical coordinate
     */
    private int y;
    /**
     * Bee spawn horizontal coordinate
     */
    private int xBeeSpawnLocation;
    /**
     * Bee spawn vertical coordinate
     */
    private int yBeeSpawnLocation;
    /**
     * Bee size (default = 15), changing this does not change bee size (because bee is image)
     */
    private static final int beeSize = 15;
    /**
     * Bee horizontal velocity (default = 1), determines how many pixels bee is moving per timer tick. Is changed after no flowers are left
     */
    private int xVelocity = 1;
    /**
     * Bee vertical velocity (default = 1), determines how many pixels bee is moving per timer tick. Is changed after no flowers are left
     */
    private int yVelocity = 1;
    /**
     * Bee's stored nectar which is grabbed from flower
     */
    private int storedNectar;
    /**
     * Nectar amount that bee is grabbing
     */
    private static final int nectarGrabAmount = 20;
    /**
     * Maximum nectar amount that bee can store
     */
    private final static int maximumNectarStored = 20;
    /**
     * Flower selected by bee, 1 flower can be chosen by 1 one at time
     */
    Flower selectedFlower;
    /**
     * Determines if bee has selected flower
     */
    private boolean hasSelectedFlower;
    /**
     * Bee is ready to deposit nectar when nectarGrabAmount == maximumNectarStored
     */
    private boolean readyToDepositNectar;
    /**
     * If beeVelocityRandomizer() was executed successfully variable is set to true
     */
    private boolean beeVelocityIsRandomized = false;
    /**
     * Randomizer used in beeVelocityRandomizer()
     */
    Random random;
    /**
     * Used when bee is heading right (x coordinate is increasing)
     */
    private static Image rightBeeImage;
    /**
     * Used when bee is heading left (x coordinate is decreasing)
     */
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
    /**
     * Bee class constructor, randomizes bee spawn coordinates, sets hasSelectedFlower to false and sets readyToDepositNectar to false
     */
    public Bee(){
        random = new Random();
        beeSpawnRandomizer();
        hasSelectedFlower = false;
        readyToDepositNectar = false;
    }
    /**
     * randomizes bee spawn coordinates
     */
    private void beeSpawnRandomizer(){
        x = random.nextInt(Hive.x + 2*beeSize, Hive.x + Hive.size - 2*beeSize);
        y = random.nextInt(Hive.y + 2*beeSize, Hive.y + Hive.size - 2*beeSize);
        xBeeSpawnLocation = x;
        yBeeSpawnLocation = y;
    }
    /**
     * Paints bee on Panel screen
     */
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
    /**
     * Bee gets flower then flies to it. Selected flower occupation variable (IsOccupiedByBeeToTrue) is set to false, bee hasSelectedFlower is set to true, bee selectedFlower is set as this flower
     */
    public void getFlower(Flower flower){
        flower.setIsOccupiedByBeeToTrue();
        hasSelectedFlower = true;
        selectedFlower = flower;
    }
    /**
     * If a) hasSelectedFlower is true and readyToDepositNectar is false - bee is flying to flower b) hasSelectedFlower is true and readyToDepositNectar is true - bee comes back to hive c) hasSelectedFlower is false and readyToDepositNectar is false - there are no flowers left so bee is flying around
     */
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
            if(!beeVelocityIsRandomized){
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
    /**
     * Used to randomize x and y velocity after no flowers are left
     */
    private void beeVelocityRandomizer(){
        xVelocity = random.nextInt(-2,2);
        yVelocity = random.nextInt(-2,2);
        while(xVelocity == 0 && yVelocity == 0){
            xVelocity = random.nextInt(-2,2);
            yVelocity = random.nextInt(-2,2);

        }
        beeVelocityIsRandomized = true;
    }
    /**
     * Bee gets nectar from flower
     */
    public void getNectar(Flower flower){
        if(storedNectar + nectarGrabAmount <= maximumNectarStored && flower.getNectarAmount() > 0 && selectedFlower == flower){
            flower.setNectarAmount(flower.getNectarAmount()-nectarGrabAmount);
            storedNectar += nectarGrabAmount;
            readyToDepositNectar = true;
        }
    }
    /**
     * Bee gives nectar to hive
     */
    public void giveNectarToHive(Hive hive){
        if(hasSelectedFlower && readyToDepositNectar){
            hive.todayStoredNectar += storedNectar;
            storedNectar = 0;
            hasSelectedFlower = false;
            readyToDepositNectar = false;
        }
    }
    /**
     * Used to get private variable xBeeSpawnLocation
     */
    public int getxBeeSpawnLocation(){
        return xBeeSpawnLocation;
    }
    /**
     * Used to get private variable yBeeSpawnLocation
     */
    public int getyBeeSpawnLocation(){
        return yBeeSpawnLocation;
    }
    /**
     * Used to get private variable xVelocity
     */
    public int getxVelocity(){
        return xVelocity;
    }
    /**
     * Used to get private variable yVelocity
     */
    public int getyVelocity(){
        return yVelocity;
    }
    /**
     * Used to get private variable beeVelocityIsRandomized
     */
    public boolean getbeeVelocityisRandomized(){
        return beeVelocityIsRandomized;
    }
    /**
     * Used to get private variable hasSelectedFlower
     */
    public boolean getHasSelectedFlower(){
        return hasSelectedFlower;
    }
    /**
     * Used to get private variable x
     */
    public int getX(){
        return x;
    }
    /**
     * Used to get private variable y
     */
    public int getY(){
        return y;
    }
    /**
     * Used to get private static variable beeSize
     */
    public static int getBeeSize(){
        return beeSize;
    }

}