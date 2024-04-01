package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Flower {

    private int x;
    private int y;
    private static final int flowerSize = 30;
    private int nectarAmount = 20;
    private boolean isOccupiedByBee;
    Random random;
    BufferedImage flowerImage;
    BufferedImage blackFlowerImage;

    public Flower(){
        isOccupiedByBee = false;
        random = new Random();
        flowerSpawnRandomizer();
        try {
            flowerImage = ImageIO.read(new File("src/main/resources/flower.png")); // Ścieżka do pliku z obrazkiem kwiatka
            blackFlowerImage = ImageIO.read(new File("src/main/resources/blackflower.png")); // Ścieżka do pliku z czarnym kwiatkiem
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void flowerSpawnRandomizer(){
        x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
        y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        int flowerSpawnDistanceFromHive = 20;
        while((x > Hive.x - flowerSpawnDistanceFromHive - flowerSize && x < Hive.x + Hive.size + flowerSpawnDistanceFromHive) && (y > Hive.y - flowerSpawnDistanceFromHive - flowerSize && y < Hive.y + Hive.size + flowerSpawnDistanceFromHive)){
            x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
            y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        }
    }

    public void paintFlower(Graphics g){
        if (nectarAmount == 20) {
            g.drawImage(flowerImage, x, y, flowerSize, flowerSize, null);
        } else if (nectarAmount == 0) {
            g.drawImage(blackFlowerImage, x, y, flowerSize, flowerSize, null);
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getIsOccupiedByBee(){
        return isOccupiedByBee;
    }

    public void setIsOccupiedByBeeToTrue(){
        isOccupiedByBee = true;
    }

    public int getNectarAmount(){
        return nectarAmount;
    }

    public void setNectarAmount(int newNectarAmount){
        nectarAmount = newNectarAmount;
    }

    public static int getFlowerSize(){
        return flowerSize;
    }

}
