package org.example;

import java.awt.*;

/**
 * RandomEventGenerator class, responsible for generating random events
 */
public class RandomEventGenerator {
    private boolean randomEventHappened;
    private String randomEventResultsInfo;
    private final boolean randomEventsActived;
    int number;
    public RandomEventGenerator(boolean randomEventsAcitved){
        this.randomEventsActived = randomEventsAcitved;
    }

    public void generateRandomEvent(){
        if(randomEventsActived){
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

    private int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public boolean getRandomEventHappened(){
        return randomEventHappened;
    }

    public void setRandomEventHappenedToFalse(){
        randomEventHappened = false;
    }

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

    //Narazie wartosci w random eventach ponizej sa ustawione na sztywno, zawsze odejmuje albo dodaje polowe kwiatkow, nektaru, czy pszczol

    //Kubus Puchatek kradnie miod/nektar
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

    //Tygrysek zabija pszczoly
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

    //Prosiaczek niszczy kwiatki
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

    //Krzys sadzi kwiatki
    private void christopherPlantsFlowers(){
        number = 4;
        Panel.flowersAmount += Panel.flowersAmount/2;
        int howMuchFlowersWerePlanted = Panel.flowersAmount/2;
        randomEventResultsInfo = "Krzys zasadzil " + howMuchFlowersWerePlanted + " kwiatkow";
    }

}