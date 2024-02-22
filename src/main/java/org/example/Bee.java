package org.example;

import java.awt.*;
import java.util.Random;

public class Bee {
    public int x;
    public int y;
    public int xBeeSpawnLocation;
    public int yBeeSpawnLocation;
    public static final int beeSize = 15;
    public int xVelocity = 1;
    public int yVelocity = 1;
    public int storedNectar;
    public static final int nectarGrabAmount = 10;
    public final static int maximumNectarStored = 10;
    Flower selectedFlower;
    boolean hasSelectedFlower;
    boolean readyToDepositNectar;

    Random random;
    Bee(){
        random = new Random();
        beeSpawnRandomizer();
        hasSelectedFlower = false;
        readyToDepositNectar = false;
    }
    public void beeSpawnRandomizer(){
        x = random.nextInt(Hive.x, Hive.x + Hive.size);
        y = random.nextInt(Hive.y, Hive.y + Hive.size);
        xBeeSpawnLocation = x;
        yBeeSpawnLocation = y;
    }
    public void paintBee(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,beeSize,beeSize);
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
