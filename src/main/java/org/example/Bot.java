package org.example;

import java.util.Random;

public abstract class Bot {

    Hive hive;
    Random random;
    protected double chanceToBuyBee;
    protected double chanceToBuyFlower;
    protected boolean likesToSell;
    protected String name;
    private double totalMoneyForBuying;
    private double honeyPrice;
    protected boolean likesToInvest;
    private int boughtBees;
    private int boughtFlowers;
    private int moneySpentOnBees;
    private int moneySpentOnFlowers;
    private int totalMoneySpent;

    public Bot(Hive hive){
        this.hive = hive;
        random = new Random();
    }

    private void getTotalMoneyForBuying(){
        //likesToInvest == true => przeznacza 80% pieniedzy na inwestycje w kwiaty i pszczoly
        //likesToInvest == false => przeznacza 40% pieniedzy na inwestycje w kwiaty i pszczoly
        if(likesToInvest){
            totalMoneyForBuying = Hive.money * 0.8;
        }
        else{
            totalMoneyForBuying = Hive.money * 0.4;
        }
    }

    private void getHoneyPrice(){
        honeyPrice = Market.value;
    }


    private void sellHoney(){
        //likesToSell == true => sprzedaje 80% miodu
        //likesToSell == false => sprzedaje 40% miodu
        int honeyForSale;

        if(likesToSell){
            honeyForSale = (int) Math.floor(Hive.storedNectar * 0.8);
        }
        else{
            honeyForSale = (int) Math.floor(Hive.storedNectar * 0.6);
        }
        while(honeyForSale > 0){
            honeyForSale--;
            Hive.storedNectar--;
            Hive.money += honeyPrice;
        }
    }
    private void buySomething(){
        cleanBoughtThings();
        Market.updateBeePriceForBots();
        double moneyToSpendOnFlowers = totalMoneyForBuying * chanceToBuyFlower;
        double moneyToSpendOnBees = totalMoneyForBuying * chanceToBuyBee;

        while(moneyToSpendOnBees > Market.beePrice){
            moneyToSpendOnBees -= Market.beePrice;
            Hive.money -= Market.beePrice;
            moneySpentOnBees += Market.beePrice;
            Panel.beessAmount++;
            boughtBees++;
        }

        while(moneyToSpendOnFlowers > Hive.flowerPrice){
            Hive.flowerPrice += random.nextInt(15);
            moneyToSpendOnFlowers -= Hive.flowerPrice;
            moneySpentOnFlowers += Hive.flowerPrice;
            Hive.money -= Hive.flowerPrice;
            Panel.flowersAmount++;
            boughtFlowers++;
        }

        totalMoneySpent = moneySpentOnBees + moneySpentOnFlowers;

    }

    public int getAmountOfBoughtBees(){
        return boughtBees;
    }

    public int getAmountOfBoughtFlowers(){
        return boughtFlowers;
    }

    private void cleanBoughtThings(){
        boughtBees = 0;
        boughtFlowers = 0;
    }

    private void setMoneySpentToZero(){
        moneySpentOnFlowers = 0;
        moneySpentOnBees = 0;
        totalMoneySpent = 0;
    }

    public int getMoneySpentOnBees(){
        return moneySpentOnBees;
    }

    public int getMoneySpentOnFlowers(){
        return moneySpentOnFlowers;
    }

    public int getTotalMoneySpent(){
        return totalMoneySpent;
    }


    public void action(){
        setMoneySpentToZero();
        getHoneyPrice();
        sellHoney();
        getTotalMoneyForBuying();
        buySomething();
        Panel.botDidAction = true;
    }

}
