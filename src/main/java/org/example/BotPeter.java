package org.example;

public class BotPeter extends Bot{
    public BotPeter(Hive hive){
        super(hive);
        chanceToBuyBee = 0.6;
        chanceToBuyFlower = 0.4;
        name = "Peter";
        likesToSell = true;
        likesToInvest = true;
    }

}