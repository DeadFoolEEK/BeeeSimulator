package org.example;

import java.awt.*;
import java.util.Random;

public class Bee {
    public int x;
    public int y;
    public static final int beeSize = 15;
    public int xVelocity;
    public int yVelocity;
    public int storedNectar;
    public static final int nectarGrabAmount = 10;
    public final static int maximumNectarStored = 10;

    Random random;
    Bee(){
        random = new Random();
        beeSpawnRandomizer();
        beeVelocityRandomizer();
    }
    public void beeSpawnRandomizer(){
        x = random.nextInt(Hive.x, Hive.x + Hive.size);
        y = random.nextInt(Hive.y, Hive.y + Hive.size);
    }
    public void paintBee(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,beeSize,beeSize);
    }
    public void beeVelocityRandomizer(){
        while(xVelocity == 0 && yVelocity == 0){
            xVelocity = random.nextInt(-2,2);
            yVelocity = random.nextInt(-2,2);
        }
    }
    public void beeMove(){
        if(x > Panel.PANEL_WIDTH - beeSize || x < 0){
            xVelocity *= -1;
        }
        else if(y > Panel.PANEL_HEIGHT - beeSize || y < 0){
            yVelocity *= -1;
        }
        x += xVelocity;
        y += yVelocity;

    }

    public void getNectar(Flower flower){
        if(storedNectar + nectarGrabAmount <= maximumNectarStored && flower.nectarAmount > 0){
            flower.nectarAmount -= nectarGrabAmount;
            storedNectar += nectarGrabAmount;
        }
    }

    public void giveNectarToHive(Hive hive){
        hive.todayStoredNectar += storedNectar;
        storedNectar = 0;
    }
}
