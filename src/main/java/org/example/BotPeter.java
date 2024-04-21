package org.example;
/**
 * BotPeter class, bot likes to buy bees more than flowers and likes to sell and invest. Prefers rather offensive play-style
 */
public class BotPeter extends Bot{
    /**
     * Class constructor
     */
    public BotPeter(Hive hive){
        super(hive);
        chanceToBuyBee = 0.6;
        chanceToBuyFlower = 0.4;
        name = "Peter";
        likesToSell = true;
        likesToInvest = true;
    }

}