package org.example;

import java.util.Random;

/**
 * Bot class, responsible for bots (automatic simulation)
 */
public abstract class Bot {
    /**
     * Object of hive class, the same Hive for whole program
     */
    Hive hive;
    /**
     * Randomizer, randomizes flower price
     */
    Random random;
    /**
     * Determines how much money bot can spend on bees
     */
    protected double chanceToBuyBee;
    /**
     * Determines how much money bot can spend on flowers
     */
    protected double chanceToBuyFlower;
    /**
     * Determines how much nectar bot will sell
     */
    protected boolean likesToSell;
    /**
     * Bots name
     */
    protected String name;
    /**
     * Total money that bot can spend on flowers and bees
     */
    private double totalMoneyForBuying;
    /**
     * Honey price
     */
    private double honeyPrice;
    /**
     * Determines how much money bot will invest (buying bees and flowers), (true == Hive.money * 0.8, false == Hive.money * 0.4). True == bot sells 80% of money, false == bot sells 40% of nectar
     */
    protected boolean likesToInvest;
    /**
     * Amount of bought bees per night
     */
    private int boughtBees;
    /**
     * Amount of bought flowers per round (night)
     */
    private int boughtFlowers;
    /**
     * Money spent on bees per round (night)
     */
    private int moneySpentOnBees;
    /**
     * Money spent on flowers per round (night)
     */
    private int moneySpentOnFlowers;
    /**
     * Total money spent per round (night)
     */
    private int totalMoneySpent;
    /**
     * Class constructor
     */
    public Bot(Hive hive){
        this.hive = hive;
        random = new Random();
    }
    /**
     * Determines how much money will be spent on flowers and bees (sets totalMoneyForBuying)
     */
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
    /**
     * Gets honey price from Market
     */
    private void getHoneyPrice(){
        honeyPrice = Market.value;
    }
    /**
     * Sells nectar, likesToSell == true => sells 80% of nectar, likesToSell == false => sells 40% of nectar
     */
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
    /**
     * Bot buys things according to moneyToSpendOnFlowers and moneyToSpendOnBees variables (which are set in different methods)
     */
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
    /**
     * Used to get private variable boughtBees
     */
    public int getAmountOfBoughtBees(){
        return boughtBees;
    }
    /**
     * Used to get private variable boughtFlowers
     */
    public int getAmountOfBoughtFlowers(){
        return boughtFlowers;
    }
    /**
     * Sets boughBees and boughtFlowers variables to 0. Used after every round (night)
     */
    private void cleanBoughtThings(){
        boughtBees = 0;
        boughtFlowers = 0;
    }
    /**
     * Sets moneySpentOnFlowers, moneySpentOnBess and totalMoneySpent to 0. Used after every round (night)
     */
    private void setMoneySpentToZero(){
        moneySpentOnFlowers = 0;
        moneySpentOnBees = 0;
        totalMoneySpent = 0;
    }
    /**
     * Used to get private variable moneySpentOnBees
     */
    public int getMoneySpentOnBees(){
        return moneySpentOnBees;
    }
    /**
     * Used to get private variable moneySpentOnFlowers
     */
    public int getMoneySpentOnFlowers(){
        return moneySpentOnFlowers;
    }
    /**
     * Used to get private variable totalMoneySpent
     */
    public int getTotalMoneySpent(){
        return totalMoneySpent;
    }
    /**
     * bot action method, used once per round (night)
     */
    public void action(){
        setMoneySpentToZero();
        getHoneyPrice();
        sellHoney();
        getTotalMoneyForBuying();
        buySomething();
        Panel.botDidAction = true;
    }

}
