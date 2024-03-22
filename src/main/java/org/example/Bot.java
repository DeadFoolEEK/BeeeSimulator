package org.example;

import java.util.Random;

public abstract class Bot {
    Hive hive;
    Bot(Hive hive){
        this.hive = hive;
        random = new Random();
    }
    public double chanceToBuyBee;
    public double chanceToBuyFlower;
    public boolean likesToSell;
    public String name;
    public double totalMoneyForBuying;
    public double honeyPrice;
    public boolean likesToInvest;
    private int boughtBees;
    private int boughtFlowers;
    Random random;

    public void getTotalMoneyForBuying(){
        //likesToInvest == true => przeznacza 80% pieniedzy na inwestycje w kwiaty i pszczoly
        //likesToInvest == false => przeznacza 40% pieniedzy na inwestycje w kwiaty i pszczoly
        if(likesToInvest){
            totalMoneyForBuying = Hive.money * 0.8;
        }
        else{
            totalMoneyForBuying = Hive.money * 0.4;
        }
    }

    public void getHoneyPrice(){
        honeyPrice = Market.value;
    }


    public void sellHoney(){
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
    public void buySomething(){
        cleanBoughtThings();
        Market.updateBeePriceForBots();
        double moneyToSpendOnFlowers = totalMoneyForBuying * chanceToBuyFlower;
        double moneyToSpendOnBees = totalMoneyForBuying * chanceToBuyBee;

        while(moneyToSpendOnBees > Market.beePrice){
            moneyToSpendOnBees -= Market.beePrice;
            Hive.money -= Market.beePrice;
            Panel.beessAmount++;
            boughtBees++;
        }

        while(moneyToSpendOnFlowers > Hive.flowerPrice){
            Hive.flowerPrice += random.nextInt(15);
            moneyToSpendOnFlowers -= Hive.flowerPrice;
            Hive.money -= Hive.flowerPrice;
            Panel.flowersAmount++;
            boughtFlowers++;
        }

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

    public void action(){
        getHoneyPrice();
        sellHoney();
        getTotalMoneyForBuying();
        buySomething();
        Panel.botDidAction = true;
    }
}
