package org.example;

public class BotBruno extends Bot{
    public BotBruno(Hive hive) {
        super(hive);
        chanceToBuyBee = 0.4;
        chanceToBuyFlower = 0.6;
        name = "Bruno";
        likesToSell = false;
        likesToInvest = false;
    }

}