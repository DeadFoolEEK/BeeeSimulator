package org.example;

import java.awt.*;
import java.util.Random;

public class Flower {
    public int x;
    public int y;
    public static int flowerSize = 20;
    public int nectarAmount = 10;
    Random random;
    Flower(){
        random = new Random();
        flowerSpawnRandomizer();
    }
    public void flowerSpawnRandomizer(){
        x = random.nextInt(Panel.PANEL_WIDTH);
        y = random.nextInt(Panel.PANEL_HEIGHT);
        while(x >= 300 && x <= 500 && y >= 300 && y <= 500){
            x = random.nextInt(Panel.PANEL_WIDTH);
            y = random.nextInt(Panel.PANEL_HEIGHT);
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
