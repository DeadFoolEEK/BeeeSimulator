package unitTests;

import org.example.Hive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for Hive Class
 */
public class HiveTest {
    /**
     * Hive class
     */
    Hive hive;
    /**
     * Unit test for getAmountOfBees method
     */
    @Test
    public void getAmountOfBeesTest(){
        hive = new Hive();
        assertEquals(0,hive.amountOfBess,"Amount of bess is not 0");
        hive.setAmountOfBees(100);
        assertEquals(100,hive.amountOfBess,"Amount of bees is not what is should be");
    }
    /**
     * Unit test for addDay method
     */
    @Test
    public void addDayTest(){
        hive = new Hive();
        assertEquals(0, hive.getDay(),"No days added, should be 0");
        hive.addDay();
        assertEquals(1, hive.getDay(),"1 day added, should be 1");
    }
    /**
     * Unit test for addTodaysNectarToTotalNectar method
     */
    @Test
    public void addTodaysNectarToTotalNectarTest(){
        hive = new Hive();
        Hive.storedNectar = 0;
        assertEquals(0,Hive.storedNectar,"There's no stored nectar");
        hive.todayStoredNectar = 100;
        hive.addTodaysNectarToTotalNectar();
        assertEquals(100,Hive.storedNectar,"Wrong stored nectar amount");
    }
    /**
     * Unit test for clearTodaysStoreNectar method
     */
    @Test
    public void  clearTodaysStoreNectarTest(){
        hive = new Hive();
        assertEquals(Hive.storedNectar,0,"There's no stored nectar");
        hive.todayStoredNectar = 100;
        hive.addTodaysNectarToTotalNectar();
        assertEquals(100,Hive.storedNectar,"Wrong stored nectar amount");
        hive.clearTodaysStoreNectar();
        assertEquals(0,hive.todayStoredNectar,"Todays stored nectar has been cleaned");
    }

}
