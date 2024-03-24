package org.example;

import java.awt.*;

public class RandomEventGenerator {
    private boolean randomEventHappened;
    private String randomEventResultsInfo;
    public RandomEventGenerator(){

    }

    public void generateRandomEvent(){
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
        g.drawString(randomEventResultsInfo,100,100);
    }

    //Narazie wartosci w random eventach ponizej sa ustawione na sztywno, zawsze odejmuje albo dodaje polowe kwiatkow, nektaru, czy pszczol

    //Kubus Puchatek kradnie miod/nektar
    private void winnieThePoohStoleNectar(){
        int nectarAmount = Hive.storedNectar;
        if(nectarAmount - nectarAmount/2 < 0){
            Hive.storedNectar = 0;
        }
        else{
            Hive.storedNectar -= Hive.storedNectar/2;
        }
        randomEventResultsInfo = "Kubus Puchatek zjadl miod";
    }

    //Tygrysek zabija pszczoly
    private void tiggerKilledBees(){
        int beesAmount = Panel.beessAmount;
        if(beesAmount - beesAmount/2 < 0){
            Panel.beessAmount = 0;
        }
        else{
            Panel.beessAmount -= Panel.beessAmount/2;
        }
        randomEventResultsInfo = "Tygyrs zabil pszczoly";
    }

    //Prosiaczek niszczy kwiatki
    private void pigletDestroyedFlowers(){
        int flowersAmount = Panel.flowersAmount;
        if(flowersAmount - flowersAmount/2 < 0){
            Panel.flowersAmount = 0;
        }
        else{
            Panel.flowersAmount -= Panel.flowersAmount/2;
        }
        randomEventResultsInfo = "Prosiaczek zniszczyl kwiatki";
    }

    //Krzys sadzi kwiatki
    private void christopherPlantsFlowers(){
        Panel.flowersAmount += Panel.flowersAmount/2;
        randomEventResultsInfo = "Krzysz zasadzil kwiatki";
    }

}
