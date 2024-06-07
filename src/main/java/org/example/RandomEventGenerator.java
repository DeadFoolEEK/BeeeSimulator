package org.example;

import java.awt.*;

/**
 * RandomEventGenerator class, responsible for generating random events
 */
public class RandomEventGenerator {
    /**
     * Determines if random event happened
     */
    private boolean randomEventHappened;
    /**
     * String info about random event
     */
    private String randomEventResultsInfo;
    /**
     * Checks if random events are activated, user can set this in settings
     */
    private final boolean randomEventsActivated;
    /**
     * Unique number for random event
     */
    int number;
    /**
     * RandomEventGenerator class constructor
     */
    public RandomEventGenerator(boolean randomEventsAcitved){
        this.randomEventsActivated = randomEventsAcitved;
    }
    /**
     * Method that generates random events, randomize number and chose random event (every random has 5% of happen)
     */
    public void generateRandomEvent(){
        if(randomEventsActivated){
            int min = 1;
            int max = 100;
            int randomNumber = getRandomNumber(min, max);

            if(randomNumber >= 1 && randomNumber <= 5){ //5 % szans
                winnieThePoohStoleNectar();
                randomEventHappened = true;
            }
            else if(randomNumber >= 6 && randomNumber <= 10){ //5 % szans
                tiggerKilledBees();
                randomEventHappened = true;
            }
            else if(randomNumber >= 11 && randomNumber <= 15){ //5 % szans
                pigletDestroyedFlowers();
                randomEventHappened = true;
            }
            else if(randomNumber >= 16 && randomNumber <= 20){ //5 % szans
                christopherPlantsFlowers();
                randomEventHappened = true;
            }
        }
    }
    /**
     * Random number generator, used in generateRandomEvent method
     */
    private int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
    /**
     * Returns private variable randomEventHappened
     */
    public boolean getRandomEventHappened(){
        return randomEventHappened;
    }
    /**
     * Sets randomEventHappened variable to false
     */
    public void setRandomEventHappenedToFalse(){
        randomEventHappened = false;
    }
    /**
     * Method that draws random event info during night (if random event happened)
     */
    public void drawRandomEventResult(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        if(number==1) {
            g.drawString(randomEventResultsInfo,130,100);
        } else if (number==2) {
            g.drawString(randomEventResultsInfo,160,100);
        } else if (number==3) {
            g.drawString(randomEventResultsInfo,105,100);
        } else if (number==4) {
            g.drawString(randomEventResultsInfo,140,100);
        }

    }

    /**
     * Negative random event, multiply amount of nectar by 0.5. 5% chance of happen
     */
    private void winnieThePoohStoleNectar(){
        number = 1;
        int nectarAmount = Hive.storedNectar;
        int howMuchNectarWasEaten;
        if(nectarAmount - nectarAmount/2 < 0){
            howMuchNectarWasEaten = nectarAmount;
            Hive.storedNectar = 0;
        }
        else{
            howMuchNectarWasEaten = Hive.storedNectar/2;
            Hive.storedNectar -= Hive.storedNectar/2;
        }
        randomEventResultsInfo = "Kubus Puchatek zjadl " + howMuchNectarWasEaten + " miodu";
    }

    /**
     * Negative random event, multiply amount of bees by 0.5. 5% chance of happen
     */
    private void tiggerKilledBees(){
        number = 2;
        int beesAmount = Panel.beessAmount;
        int howMuchBessWerekilled;
        if(beesAmount - beesAmount/2 < 0){
            howMuchBessWerekilled = beesAmount ;
            Panel.beessAmount = 0;
        }
        else{
            howMuchBessWerekilled = Panel.beessAmount/2;
            Panel.beessAmount -= Panel.beessAmount/2;
        }
        randomEventResultsInfo = "Tygyrsek zabil " + howMuchBessWerekilled + " pszczol";
    }

    /**
     * Negative random event, multiply amount of flowers by 0.5. 5% chance of happen
     */
    private void pigletDestroyedFlowers(){
        number = 3;
        int flowersAmount = Panel.flowersAmount;
        int howMuchFlowersWereDestroyed;
        if(flowersAmount - flowersAmount/2 < 0){
            howMuchFlowersWereDestroyed = flowersAmount;
            Panel.flowersAmount = 0;
        }
        else{
            howMuchFlowersWereDestroyed = Panel.flowersAmount/2;
            Panel.flowersAmount -= Panel.flowersAmount/2;
        }
        randomEventResultsInfo = "Prosiaczek zniszczyl " + howMuchFlowersWereDestroyed + " kwiatkow";
    }

    /**
     * Positive random event, multiply amount of flowers by 1.5. 5% chance of happen
     */
    private void christopherPlantsFlowers(){
        number = 4;
        Panel.flowersAmount += Panel.flowersAmount/2;
        int howMuchFlowersWerePlanted = Panel.flowersAmount/2;
        randomEventResultsInfo = "Krzys zasadzil " + howMuchFlowersWerePlanted + " kwiatkow";
    }

}