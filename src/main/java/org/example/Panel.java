package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {
    public final static int PANEL_WIDTH = 800;
    public final static int PANEL_HEIGHT = 800;
    Bot bot;
    static public boolean botPlay;
    static public String botName;
    static public boolean botDidAction;
    Timer timer;
    Hive hive;
    ArrayList<Flower> flowers;
    ArrayList<Bee> bees;
    public static int flowersAmount = 60;
    public static int beessAmount = 5;
    public static boolean isNight;
    long time_start;
    static long timeOfNight = 5000;
    static long timeOfDay = 7000;
    boolean nightStared = false;
    Flower selectedFlower;
    Random random = new Random();
    public static int babyBee = 0;
    public static int eating;
    public static int howManyDie;
    public Image backgroundImage;


    Panel(Hive hive){
        /*if(beessAmount==0 || timeOfDay==0 || timeOfNight==0) {
            beessAmount =5;
            timeOfDay= 7000;
            timeOfNight = 5000;
        }*/
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        isNight = false;
        timer = new Timer(10,this);
        timer.start();
        this.hive = hive;
        flowers = new ArrayList<>();
        bees = new ArrayList<>();
        hive.getAmountOfBees(beessAmount);
        generateBot();
        beginDay();
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/field2.jpg")); // Ścieżka do pliku z obrazkiem tła
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateBot(){
        if(botPlay){
            if(botName.equals("Peter")){
                bot = new BotPeter(hive);
            }
            else{
                bot = new BotBruno(hive);
            }
            botDidAction = false;
        }
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
        // this.setBackground(Color.green);
        isNight = false;
        nightStared = false;
        hive.clearTodaysStoreNectar();
        spawnFlowers();
        spawnBees();
        hive.getAmountOfBees(beessAmount);
        hive.addDay();
        botDidAction = false;
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
        if(beessAmount > 0) {
        g.drawString("Dzien " + Hive.day + " dobiegl konca",200,200);
        g.drawString("Pszczoly zebraly dzisiaj " + hive.todayStoredNectar + " nektaru",75,300);
        g.drawString("W nocy urodzilo sie " + babyBee + " pszczol", 5, 400);
        if(Hive.storedNectar > 0) {
            g.drawString("Wszystkie pszczoly przezyly", 100, 500);
        } else if (Hive.storedNectar <=0){
            g.drawString("Z glodu umarlo " + howManyDie + " pszczol", 100, 500 );
        }
        if(botPlay){
            g.drawString("Bot kupil " + bot.getAmountOfBoughtBees() + " pszczol",100,600);
            g.drawString("Bot kupil " + bot.getAmountOfBoughtFlowers() + " kwiatkow",100 ,700);
        }
    } else if(beessAmount <= 0) {
        g.drawString("Zadna pszczola nie przezyla, koniec symulacji",5,200);
        timeOfNight = 100000;
    } 
        
            
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

    public void bee_doing_sex() {
        if(beessAmount<20) {
            babyBee = random.nextInt(2);
            beessAmount += babyBee;
        } else if (beessAmount<40) {
            babyBee = random.nextInt(7);
            beessAmount += babyBee;
        } else if(beessAmount<70) {
            babyBee = random.nextInt(10);
            beessAmount += babyBee;
        } else {
            babyBee = random.nextInt(13);
            beessAmount += babyBee;
        }
    }

    public void bee_eat_or_die() {
        eating = beessAmount * 2;
        Hive.storedNectar -= eating;
        if(Hive.storedNectar < 0) {
            System.out.println(Hive.storedNectar);
            howManyDie = Hive.storedNectar * (-1) / 2;
            System.out.println(howManyDie);
            beessAmount -= howManyDie;
            Hive.storedNectar = 0;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isNight) {
            setBackground(Color.BLACK);
            drawNightInfo(g);
        } else if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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
                beginNight(); // to ma znaczenie ze jest tutaj bo inaczej jest problem z bee eat or die 
                bee_doing_sex();
                bee_eat_or_die();
                if(botPlay && !botDidAction){
                    bot.action();
                }
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
