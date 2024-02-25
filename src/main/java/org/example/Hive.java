package org.example;

import java.awt.*;

public class Hive{
    public final static int x = 300;
    public final static int y = 300;
    public final static int size = 200;
    public int storedNectar = 0;
    public int todayStoredNectar = 0;
    public int amountOfBess;
    public static int day;
    public static double money;
    Hive(){

    }
    public void paintHive(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x,y,size,size);
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
