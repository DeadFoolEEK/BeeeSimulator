package unitTests;

import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test class for Bot Class
 */
public class BotTest {
    /**
     * Bot class
     */
    Bot bot;
    /**
     * Hive class
     */
    Hive hive;
    /**
     * Unit test for getAmountOfBoughtBees method for bot Peter
     */
    @Test
    public void getAmountOfBoughtBeesTest_BOTPETER(){
        double money = 100;
        hive = new Hive();
        bot = new BotPeter(hive);
        Hive.money = money;

        assertEquals(0,bot.getAmountOfBoughtBees());

        bot.action();

        int amountOfBoughtBees = bot.getAmountOfBoughtBees();
        int moneySpentOnBees = bot.getMoneySpentOnBees();

        money -= moneySpentOnBees;

        assertTrue(money >= 0, "Money is lower than 0");
        assertTrue(money > moneySpentOnBees, "Money spent on bees is greater than total money");
        if(money >= Market.beePrice){
            assertTrue(amountOfBoughtBees >= 0, "Bot did not bought bees, even he had enough money");
        }
        assertTrue(amountOfBoughtBees >= 0,"Bot bought minus bees");

        System.out.println("Bot bought " + amountOfBoughtBees + " bess and spent " + moneySpentOnBees + " money. Left money was invested into flowers or saved");
    }
    /**
     * Unit test for getAmountOfBoughtFlowers method for bot Peter
     */
    @Test
    public void getAmountOfBoughtFlowers_BOTPETER(){
        double money = 1000;
        hive = new Hive();
        bot = new BotPeter(hive);
        Hive.money = money;

        assertEquals(0,bot.getAmountOfBoughtFlowers());

        bot.action();

        int amountOfBoughtFlowers = bot.getAmountOfBoughtFlowers();
        int moneySpentOnFlowers = bot.getMoneySpentOnFlowers();

        money -= moneySpentOnFlowers;

        assertTrue(money >= 0, "Money is lower than 0");
        assertTrue(money > moneySpentOnFlowers, "Money spent on flowers is greater than total money");
        assertTrue(amountOfBoughtFlowers > 0,"Bot bought minus bees");

        System.out.println("Bot bought " + amountOfBoughtFlowers + " flowers and spent " + moneySpentOnFlowers + " money. Left money was invested into bees or saved");
    }
    /**
     * Unit test for bot actions for bot Peter
     */
    @Test
    public void actionTest_BOTPETER(){
        double money = 1000;
        hive = new Hive();
        bot = new BotPeter(hive);
        Hive.money = money;

        assertEquals(0,bot.getAmountOfBoughtFlowers());
        assertEquals(0,bot.getAmountOfBoughtBees());

        bot.action();

        int amountOfBoughtFlowers = bot.getAmountOfBoughtFlowers();
        int moneySpentOnFlowers = bot.getMoneySpentOnFlowers();
        int amountOfBoughtBees = bot.getAmountOfBoughtBees();
        int moneySpentOnBees = bot.getMoneySpentOnBees();

        money -= (moneySpentOnFlowers + moneySpentOnBees);

        assertTrue(money >= 0 , "Bot bought too much and has < 0 money");
        assertEquals(bot.getTotalMoneySpent(), moneySpentOnFlowers + moneySpentOnBees , "Bot bought too much and has < 0 money");

        System.out.println("Bot bought " + amountOfBoughtFlowers + " flowers and " + amountOfBoughtBees + " bees. He paid " + (moneySpentOnFlowers + moneySpentOnBees));
    }
    /**
     * Unit test for getAmountOfBoughtBees method for bot Bruno
     */
    @Test
    public void getAmountOfBoughtBeesTest_BOTBRUNO(){
        double money = 100;
        hive = new Hive();
        bot = new BotBruno(hive);
        Hive.money = money;

        assertEquals(0,bot.getAmountOfBoughtBees());

        bot.action();

        int amountOfBoughtBees = bot.getAmountOfBoughtBees();
        int moneySpentOnBees = bot.getMoneySpentOnBees();

        money -= moneySpentOnBees;

        assertTrue(money >= 0, "Money is lower than 0");
        assertTrue(money > moneySpentOnBees, "Money spent on bees is greater than total money");
        if(money >= Market.beePrice){
            assertTrue(amountOfBoughtBees >= 0, "Bot did not bought bees, even he had enough money");
        }
        assertTrue(amountOfBoughtBees >= 0,"Bot bought minus bees");

        System.out.println("Bot bought " + amountOfBoughtBees + " bess and spent " + moneySpentOnBees + " money. Left money was invested into flowers or saved");
    }
    /**
     * Unit test for getAmountOfBoughtFlowers method for bot Bruno
     */
    @Test
    public void getAmountOfBoughtFlowers_BOTBRUNO(){
        double money = 1000;
        hive = new Hive();
        bot = new BotBruno(hive);
        Hive.money = money;

        assertEquals(0,bot.getAmountOfBoughtFlowers());

        bot.action();

        int amountOfBoughtFlowers = bot.getAmountOfBoughtFlowers();
        int moneySpentOnFlowers = bot.getMoneySpentOnFlowers();

        money -= moneySpentOnFlowers;

        assertTrue(money >= 0, "Money is lower than 0");
        assertTrue(money > moneySpentOnFlowers, "Money spent on flowers is greater than total money");
        assertTrue(amountOfBoughtFlowers > 0,"Bot bought minus bees");

        System.out.println("Bot bought " + amountOfBoughtFlowers + " flowers and spent " + moneySpentOnFlowers + " money. Left money was invested into bees or saved");
    }
    /**
     * Unit test for bot actions for bot Bruno
     */
    @Test
    public void actionTest_BOTBRUNO(){
        double money = 1000;
        hive = new Hive();
        bot = new BotBruno(hive);
        Hive.money = money;

        assertEquals(0,bot.getAmountOfBoughtFlowers());
        assertEquals(0,bot.getAmountOfBoughtBees());

        bot.action();

        int amountOfBoughtFlowers = bot.getAmountOfBoughtFlowers();
        int moneySpentOnFlowers = bot.getMoneySpentOnFlowers();
        int amountOfBoughtBees = bot.getAmountOfBoughtBees();
        int moneySpentOnBees = bot.getMoneySpentOnBees();

        money -= (moneySpentOnFlowers + moneySpentOnBees);

        assertTrue(money >= 0 , "Bot bought too much and has < 0 money");
        assertEquals(bot.getTotalMoneySpent(), moneySpentOnFlowers + moneySpentOnBees , "Bot bought too much and has < 0 money");

        System.out.println("Bot bought " + amountOfBoughtFlowers + " flowers and " + amountOfBoughtBees + " bees. He paid " + (moneySpentOnFlowers + moneySpentOnBees));
    }

}
