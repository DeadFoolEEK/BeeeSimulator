package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Flower {
    public int x;
    public int y;
    public static int flowerSize = 30;
    public int nectarAmount = 20;
    public boolean isOccupiedByBee;
    Random random;
    int flowerSpawnDistanceFromHive = 20;
    BufferedImage flowerImage;
    BufferedImage blackFlowerImage;
    Flower(){
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
    public void flowerSpawnRandomizer(){
        x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
        y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        while((x > Hive.x - flowerSpawnDistanceFromHive - flowerSize && x < Hive.x + Hive.size + flowerSpawnDistanceFromHive ) && (y > Hive.y - flowerSpawnDistanceFromHive - flowerSize && y < Hive.y + Hive.size + flowerSpawnDistanceFromHive)){
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

}
