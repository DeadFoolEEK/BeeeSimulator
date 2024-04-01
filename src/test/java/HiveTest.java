import org.example.Hive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HiveTest {

    Hive hive;

    @Test
    public void getAmountOfBeesTest(){
        hive = new Hive();
        assertEquals(0,hive.amountOfBess,"Amount of bess is not 0");
        hive.setAmountOfBees(100);
        assertEquals(100,hive.amountOfBess,"Amount of bees is not what is should be");
    }

    @Test
    public void addDayTest(){
        hive = new Hive();
        assertEquals(0, Hive.day,"No days added, should be 0");
        hive.addDay();
        assertEquals(1, Hive.day,"1 day added, should be 1");
    }

    @Test
    public void addTodaysNectarToTotalNectarTest(){
        hive = new Hive();
        Hive.storedNectar = 0;
        assertEquals(0,Hive.storedNectar,"There's no stored nectar");
        hive.todayStoredNectar = 100;
        hive.addTodaysNectarToTotalNectar();
        assertEquals(100,Hive.storedNectar,"Wrong stored nectar amount");
    }

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
