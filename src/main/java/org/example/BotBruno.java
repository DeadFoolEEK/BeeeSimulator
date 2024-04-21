package org.example;
/**
 * BotBruno class, bot likes to buy flowers more than bees and does not like to sell and invest. Prefers rather passive play-style
 */
public class BotBruno extends Bot{
    /**
     * Class constructor
     */
    public BotBruno(Hive hive) {
        super(hive);
        chanceToBuyBee = 0.4;
        chanceToBuyFlower = 0.6;
        name = "Bruno";
        likesToSell = false;
        likesToInvest = false;
    }

}