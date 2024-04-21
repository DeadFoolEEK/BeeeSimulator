package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Flower class, used in Bee and Panel classes. Responsible for flower management
 */
public class Flower {
    /**
     * Horizontal coordinate of flower
     */
    private int x;
    /**
     * Vertical coordinate of flower
     */
    private int y;
    /**
     * Determines flower size. Bigger flower are easiest to grab by bee. Changing size does not change size of graphic, better keep it 30
     */
    private static final int flowerSize = 30;
    /**
     * Nectar amount of flower. Bee grabs nectar if is close enough to flower
     */
    private int nectarAmount = 20;
    /**
     * if flower is occupied by bee, another bees can't see it as a potential purpose
     */
    private boolean isOccupiedByBee;
    /**
     * randomizer used to randomize flower x and y spawn coordinate
     */
    Random random;
    /**
     * flower image. Used when flower has some nectar left
     */
    BufferedImage flowerImage;
    /**
     * flower image. Used when flower has not got any nectar left
     */
    BufferedImage blackFlowerImage;

    /**
     * Flower constructor. Sets isOccupiedByBee variable to true and run flowerSpawnRandomizer method. Also sets image for flowers
     */
    public Flower(){
        isOccupiedByBee = false;
        random = new Random();
        flowerSpawnRandomizer();
        try {
            flowerImage = ImageIO.read(new File("src/main/resources/flower.png"));
            blackFlowerImage = ImageIO.read(new File("src/main/resources/blackflower.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * randomizes flower spawn coordinates (x and y). Lasts until flower get correct spawn location. Can't be too close to hive and to close or beyond Panel borders
     */
    private void flowerSpawnRandomizer(){
        x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
        y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        int flowerSpawnDistanceFromHive = 20;
        while((x > Hive.x - flowerSpawnDistanceFromHive - flowerSize && x < Hive.x + Hive.size + flowerSpawnDistanceFromHive) && (y > Hive.y - flowerSpawnDistanceFromHive - flowerSize && y < Hive.y + Hive.size + flowerSpawnDistanceFromHive)){
            x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
            y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        }
    }
    /**
     * Paints flower on Panel screen. Used in paint method (Panel). New flowers are colorful (or with some nectar left), those without nectar are black and white
     */
    public void paintFlower(Graphics g){
        if (nectarAmount == 20) {
            g.drawImage(flowerImage, x, y, flowerSize, flowerSize, null);
        } else if (nectarAmount == 0) {
            g.drawImage(blackFlowerImage, x, y, flowerSize, flowerSize, null);
        }
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
     * Used to get private variable IsOccupiedByBee
     */
    public boolean getIsOccupiedByBee(){
        return isOccupiedByBee;
    }
    /**
     * Used to set private variable isOccupiedByBeeToTrue to true
     */
    public void setIsOccupiedByBeeToTrue(){
        isOccupiedByBee = true;
    }
    /**
     * Used to get private variable nectarAmount
     */
    public int getNectarAmount(){
        return nectarAmount;
    }
    /**
     * Used to set private variable nectarAmount to value chosen by user, new value is an argument passed to method
     */
    public void setNectarAmount(int newNectarAmount){
        nectarAmount = newNectarAmount;
    }
    /**
     * Used to get private static variable flowerSize
     */
    public static int getFlowerSize(){
        return flowerSize;
    }

}
