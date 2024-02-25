package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {
    public final static int PANEL_WIDTH = 800;
    public final static int PANEL_HEIGHT = 800;
    Timer timer;
    Hive hive;
    ArrayList<Flower> flowers;
    ArrayList<Bee> bees;
    public static int flowersAmount = 60;
    public static int beessAmount;
    public static boolean isNight;
    long time_start;
    static long timeOfNight;
    static long timeOfDay;
    boolean nightStared = false;
    Flower selectedFlower;

    Panel(Hive hive){
        if(beessAmount==0 || timeOfDay==0 || timeOfNight==0) {
            beessAmount =5;
            timeOfDay= 5000;
            timeOfNight = 5000;
        }
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        isNight = false;
        timer = new Timer(10,this);
        timer.start();
        this.hive = hive;
        flowers = new ArrayList<>();
        bees = new ArrayList<>();
        hive.getAmountOfBees(beessAmount);

        beginDay();
    }

    public void spawnFlowers(){
        for(int i = 0; i < flowersAmount;i++){
            flowers.add(new Flower());
        }
    }

    public void spawnBees(){
        for(int i = 0; i < beessAmount ;i++){
            bees.add(new Bee());
        }
    }

    public void beginDay(){
        this.setBackground(Color.green);
        isNight = false;
        nightStared = false;
        hive.clearTodaysStoreNectar();
        spawnFlowers();
        spawnBees();
        hive.getAmountOfBees(beessAmount);
        hive.addDay();
        time_start = System.currentTimeMillis();
    }
    public void beginNight(){
        if(!nightStared){
            this.setBackground(Color.BLACK);
            flowers.clear();
            bees.clear();
            hive.addTodaysNectarToTotalNectar();
            time_start = System.currentTimeMillis();
            nightStared = true;
        }
    }

    public void drawNightInfo(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        g.drawString("Dzien " + Hive.day + " dobiegl konca",200,200);
        g.drawString("Pszczoly zebraly dzisiaj " + hive.todayStoredNectar + " nektaru",75,300);
    }

    public Flower selectFlower(){
        for(int i = 0; i < flowersAmount ;i++){
            if(flowers.get(i).nectarAmount != 0 && !flowers.get(i).isOccupiedByBee){
                return flowers.get(i);
            }
        }
        return null;
    }

    public void beeFlowerSelector(){
        for (Bee bee : bees) {
            if (!bee.hasSelectedFlower) {
                selectedFlower = selectFlower();
                if (selectedFlower != null) {
                    bee.getFlower(selectFlower());
                }
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        if(!isNight){
            hive.paintHive(g);
            for(int i = 0; i < flowersAmount;i++){
                flowers.get(i).paintFlower(g);
            }
            for(int i = 0; i < beessAmount ;i++){
                bees.get(i).paintBee(g);
            }
        }
        else{
            drawNightInfo(g);
        }
    }
    //DO POPRAWY
    public void bee_flower_collissionDetector(){
        for(int i = 0; i < flowersAmount;i++){
            for(int j = 0; j < beessAmount ;j++){
                double distanceFromFlowerToBee = Math.sqrt((Math.pow(flowers.get(i).x - bees.get(j).x,2)) + Math.pow(flowers.get(i).y - bees.get(j).y,2));
                if(distanceFromFlowerToBee < (double) Flower.flowerSize - Bee.beeSize){
                    bees.get(j).getNectar(flowers.get(i));
                }
            }
        }
    }
    //DO POPRAWY
    public void bee_hive_collisionDetector(){
        for(int i = 0; i < beessAmount;i++){//punkt (400,400) to srodek ula
            double distanceFromHiveToBee = Math.sqrt((Math.pow(400 - bees.get(i).x,2)) + Math.pow(400 - bees.get(i).y,2));
            if(distanceFromHiveToBee <= (double) ((Hive.size / 2)) + Bee.beeSize){
                bees.get(i).giveNectarToHive(hive);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isNight){
            beeFlowerSelector();
            for(int i = 0; i < beessAmount ;i++){
                bees.get(i).beeMove();
            }
            bee_flower_collissionDetector();
            bee_hive_collisionDetector();
            if(System.currentTimeMillis() - time_start >= timeOfDay){
                isNight = true;
                beginNight();
            }
        }
        else{
            if(System.currentTimeMillis() - time_start >= timeOfNight){
                isNight = false;
                beginDay();
            }
        }
        repaint();
    }
}
