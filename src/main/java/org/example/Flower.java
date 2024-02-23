package org.example;

import java.awt.*;
import java.util.Random;

public class Flower {
    public int x;
    public int y;
    public static int flowerSize = 20;
    public int nectarAmount = 10;
    public boolean isOccupiedByBee;
    Random random;
    int flowerSpawnDistanceFromHive = 20;
    Flower(){
        isOccupiedByBee = false;
        random = new Random();
        flowerSpawnRandomizer();
    }
    public void flowerSpawnRandomizer(){
        x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
        y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        while((x > Hive.x -  flowerSpawnDistanceFromHive && x < Hive.x + Hive.size + flowerSpawnDistanceFromHive) && (y > Hive.y - flowerSpawnDistanceFromHive && y < Hive.y + Hive.size + flowerSpawnDistanceFromHive)){
            x = (int)Math.floor(Math.random() * (Panel.PANEL_WIDTH - flowerSize + 1));
            y = (int)Math.floor(Math.random() * (Panel.PANEL_HEIGHT - flowerSize + 1));
        }
    }

    public void paintFlower(Graphics g){
        if(nectarAmount == 10){
            g.setColor(Color.red);
            g.fillRect(x,y,flowerSize,flowerSize);
        }
        else if(nectarAmount == 0){
            g.setColor(Color.black);
            g.fillRect(x,y,flowerSize,flowerSize);
        }
    }

}
