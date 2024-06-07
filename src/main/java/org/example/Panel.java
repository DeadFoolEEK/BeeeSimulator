package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
/**
 * Panel class, main simulation class
 */
public class Panel extends JPanel implements ActionListener {


    /**
     * Sets panel width.
     */
    public final static int PANEL_WIDTH = 800;
    /**
     * Sets panel height.
     */
    public final static int PANEL_HEIGHT = 800;
    /**
     * Declare bot
     */
    Bot bot;
    /**
     * If true bot will take over simulation
     */
    static public boolean botPlay;
    /**
     * Gets bot name. It can be Peter or Bruno
     */
    static public String botName;
    /**
     * Checks if bot did something
     */
    static public boolean botDidAction;
    /**
     * Declare timer
     */
    Timer timer;
    /**
     * Declare hive
     */
    Hive hive;
    /**
     * Creates arraylist of flowers
     */
    ArrayList<Flower> flowers;
    /**
     * Creates arraylist of bees
     */
    ArrayList<Bee> bees;
    /**
     * Sets defauly flowersamount
     */
    public static int flowersAmount = 60;
    /**
     * Sets defauly bees amount
     */
    public static int beessAmount = 5;
    /**
     * Declare starting bees amount
     */
    private int startingBeesAmount;
    /**
     * Checks if there is night.
     */
    public static boolean isNight;
    /**
     * Declare time start
     */
    long time_start;
    /**
     * Sets default time of night
     */
    static long timeOfNight = 5000;
    /**
     * Sets default time of day
     */
    static long timeOfDay = 7000;
    /**
     * Sets nightstared to false
     */
    boolean nightStared = false;
    /**
     * Declare selected flowe
     */
    Flower selectedFlower;
    /**
     * Creates random
     */
    Random random = new Random();
    /**
     * Initalize baby bees
     */
    public static int babyBee = 0;
    /**
     * Declare eating
     */
    public static int eating;
    /**
     * Declare how many bees died
     */
    public static int howManyDie;

    /**
     * Background image of simulation window (forest)
     */
    public Image backgroundImage;

    /**
     * Counts how many days simulation lasts
     */
    private int daysAmount = 0;

    /**
     * Determines how many days simulation lasts. User can change it in settings. Default is 15
     */
    public static int maximumDaysAmount = 15;

    /**
     * Determines how many days left to end simulation.
     */
    public static int daysLeftToSimulationEnd = 0; // static, poniewaz jest uzywane przez Infopanel

    /**
     * Determines how many days passed already through simulation.
     */
    private int daysPassed; // kopia daysAmount, uzywana do przekazania do zapisu, gdyz daysAmount jest uzywane gdy beesAmount <= 0 w drawNightInfo()
    Frame frame;
    RandomEventGenerator randomEventGenerator;
    public static boolean randomEventsActived = false;

    /**
     * Panel constructor. Creates array lists for flowers and bees. Sets size for panel and starting parametres of simulation like bees amount.
     */
    Panel(Hive hive,Frame frame){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.frame = frame;
        isNight = false;
        timer = new Timer(10,this);
        timer.start();
        daysLeftToSimulationEnd = maximumDaysAmount;
        randomEventGenerator = new RandomEventGenerator(randomEventsActived);
        this.hive = hive;
        flowers = new ArrayList<>();
        bees = new ArrayList<>();
        startingBeesAmount = beessAmount;
        hive.setAmountOfBees(beessAmount);
        generateBot();
        beginDay();
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/field2.jpg")); // Ścieżka do pliku z obrazkiem tła
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the appropriate bot to work in simulation.
     */
    private void generateBot(){
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

    /**
     * Spawn flowers in panel.
     */
    private void spawnFlowers(){
        for(int i = 0; i < flowersAmount;i++){
            flowers.add(new Flower());
        }
    }

    /**
     * Spawn bees in panel.
     */
    private void spawnBees(){
        for(int i = 0; i < beessAmount ;i++){
            bees.add(new Bee());
        }
    }

    /**
     * Starts the day. Activates function of spawning flowers and bees. Also increase days passed and days amount.
     */
    private void beginDay(){
        // this.setBackground(Color.green);
        daysAmount++;
        daysPassed++;
        isNight = false;
        nightStared = false;
        hive.clearTodaysStoreNectar();
        spawnFlowers();
        spawnBees();
        hive.setAmountOfBees(beessAmount);
        hive.addDay();
        botDidAction = false;
        randomEventGenerator.setRandomEventHappenedToFalse();
        time_start = System.currentTimeMillis();
    }
    /**
     * Starts the night.
     */
    private void beginNight(){
        if(!nightStared){
            this.setBackground(Color.BLACK);
            updateDaysAmountToSimulationEnd();
            flowers.clear();
            bees.clear();
            hive.addTodaysNectarToTotalNectar();
            randomEventGenerator.generateRandomEvent();
            time_start = System.currentTimeMillis();
            nightStared = true;
        }
    }

    /**
     * Shows the total summary of the day. How many bees were born, what day it is, how much nectar the bees collected, did all the bees survive, how many bees died of hunger, information about bots tactical movements.
     */
    public void drawNightInfo(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        if(beessAmount > 0) {

            if(randomEventGenerator.getRandomEventHappened()){
                randomEventGenerator.drawRandomEventResult(g);
                g.setColor(Color.green);
            }

            g.drawString("Dzien " + hive.getDay() + " dobiegl konca",220,230);
            g.drawString("Pszczoly zebraly dzisiaj " + hive.todayStoredNectar + " nektaru",75,330);
            g.drawString("W nocy urodzilo sie " + babyBee + " pszczol", 150, 430);

            if(Hive.storedNectar > 0) {
                g.drawString("Wszystkie pszczoly przezyly", 150, 530);
            }

            else if (Hive.storedNectar <=0){
                g.drawString("Z glodu umarlo " + howManyDie + " pszczol", 170, 530 );
            }

            if(botPlay){
                g.drawString("Bot kupil " + bot.getAmountOfBoughtBees() + " pszczol",225,630);
                g.drawString("Bot kupil " + bot.getAmountOfBoughtFlowers() + " kwiatkow",210 ,700);
            }

        }
        else{
            g.drawString("Koniec symulacji",220,230);
            g.drawString("Zadna pszczola nie przezyla!",75,330);
            daysAmount = maximumDaysAmount;
        }

    }
    /**
     * Gives the coordinates to the flower
     */
    private Flower selectFlower(){
        for(int i = 0; i < flowersAmount ;i++){
            if(flowers.get(i).getNectarAmount() != 0 && !flowers.get(i).getIsOccupiedByBee()){
                return flowers.get(i);
            }
        }
        return null;
    }

    /**
     * He gives the flower to the bee. Thanks to this, the bee knows where to fly and to which flower
     */
    private void beeFlowerSelector(){
        for (Bee bee : bees) {
            if (!bee.getHasSelectedFlower()) {
                selectedFlower = selectFlower();
                if (selectedFlower != null) {
                    bee.getFlower(Objects.requireNonNull(selectFlower()));
                }
            }
        }
    }

    /**
     * It draws graphics of bees, a hive, flowers and night information
     */
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

    /**
     * It checks whether a bee - flower or flower - bee collision occurred
     */
    private void bee_flower_collissionDetector(){
        for(int i = 0; i < flowersAmount;i++){
            for(int j = 0; j < beessAmount ;j++){
                double distanceFromFlowerToBee = Math.sqrt((Math.pow(flowers.get(i).getX() - bees.get(j).getX(),2)) + Math.pow(flowers.get(i).getY() - bees.get(j).getY(),2));
                if(distanceFromFlowerToBee < (double) Flower.getFlowerSize() - Bee.getBeeSize()){
                    bees.get(j).getNectar(flowers.get(i));
                }
            }
        }
    }

    /**
     * It checks whether a bee - hive or hive - bee collision occurred
     */
    private void bee_hive_collisionDetector(){
        for(int i = 0; i < beessAmount;i++){//punkt (400,400) to srodek ula
            double distanceFromHiveToBee = Math.sqrt((Math.pow(400 - bees.get(i).getX(),2)) + Math.pow(400 - bees.get(i).getY(),2));
            if(distanceFromHiveToBee <= (double) ((Hive.size / 2)) + Bee.getBeeSize()){
                bees.get(i).giveNectarToHive(hive);
            }
        }
    }

    /**
     * It makes bee reproduction
     */
    private void bee_doing_sex() {
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

    /**
     * Checks if bee have something to it. If no it dies.
     */
    private void bee_eat_or_die() {
        eating = beessAmount * 2;
        Hive.storedNectar -= eating;
        if(Hive.storedNectar < 0) {
            // System.out.println(Hive.storedNectar);
            howManyDie = Hive.storedNectar * (-1) / 2;
            //  System.out.println(howManyDie);
            beessAmount -= howManyDie;
            Hive.storedNectar = 0;
        }
    }

    /**
     * Painting background of day and night.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isNight) {
            setBackground(Color.BLACK);
            drawNightInfo(g);
        } else if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Function that decrease days of simulation .
     */
    private void updateDaysAmountToSimulationEnd(){
        daysLeftToSimulationEnd--;
    }

    /**
     * Arraylist of simulation results.
     */
    private ArrayList<String> setSimulationResults(){
        ArrayList<String> simulationResults = new ArrayList<>();

        simulationResults.add("Symulacja trwala " + (daysPassed - 1) + " dni");
        simulationResults.add("Ilosc pszczol na koncu " + beessAmount);

        return simulationResults;
    }

    /**
     * Procedure of how should ending look like.
     */
    private void endOfSimulationProcedure(){
        new EndOfSimulationFrame(setSimulationResults(),startingBeesAmount,beessAmount);
        frame.dispose();
        timer.stop();
    }
    /**
     * method from ActionListener interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(maximumDaysAmount >= daysAmount){
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
        }
        else{
            endOfSimulationProcedure();
        }
        repaint();
    }

}