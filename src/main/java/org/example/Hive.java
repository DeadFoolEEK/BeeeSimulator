package org.example;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Hive {
    public final static int x = 300;
    public final static int y = 300;
    public final static int size = 200;
    public static int storedNectar = 0;
    public int todayStoredNectar = 0;
    public int amountOfBess;
    public static int day;
    public static double money;
    public static int flowerPrice = 1;
    Hive(){

    }

    public void paintHive(Graphics g){
        //g.setColor(Color.yellow);
        //g.fillRect(x,y,size,size);
        try {
            BufferedImage hiveImage = ImageIO.read(new File("src/main/resources/hive.png")); 
            g.drawImage(hiveImage, x, y, size, size, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAmountOfBees(int number){
        amountOfBess = number;
    }

    public void addDay(){
        day++;
    }

    public void addTodaysNectarToTotalNectar(){
        storedNectar += todayStoredNectar;
    }

    public void clearTodaysStoreNectar(){
        todayStoredNectar = 0;
    }
}
