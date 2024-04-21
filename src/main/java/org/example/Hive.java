package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Hive class, responsible for managing flower price, stored nectar and money. It is also bee spawn location
 */
public class Hive {
    /**
     * Hive horizontal coordinate
     */
    public final static int x = 300;
    /**
     * Hive vertical coordinate
     */
    public final static int y = 300;
    /**
     * Hive size
     */
    public final static int size = 200;
    /**
     * Total amount of stored nectar
     */
    public static int storedNectar = 0;
    /**
     * Nectar stored today
     */
    public int todayStoredNectar = 0;
    public int amountOfBess;
    private int day;
    /**
     * Money amount, money is acquired by selling nectar. It can be spent to buy flowers or bees
     */
    public static double money;
    /**
     * Flower price, changes after every purchase of flower
     */
    public static int flowerPrice = 1;

    public void paintHive(Graphics g){
        try {
            BufferedImage hiveImage = ImageIO.read(new File("src/main/resources/hive.png")); 
            g.drawImage(hiveImage, x, y, size, size, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAmountOfBees(int number){
        amountOfBess = number;
    }

    public void addTodaysNectarToTotalNectar(){
        storedNectar += todayStoredNectar;
    }

    public void clearTodaysStoreNectar(){
        todayStoredNectar = 0;
    }

    public void addDay(){
        day++;
    }

    public int getDay(){
        return day;
    }

}
