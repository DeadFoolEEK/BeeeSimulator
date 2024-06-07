package unitTests;

import org.example.Bee;
import org.example.Flower;
import org.example.Hive;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for Bee Class
 */
public class BeeTest {
    /**
     * Bee class
     */
    Bee bee;
    /**
     * Flower class
     */
    Flower flower;
    /**
     * Unit test for beeSpawnRandomizer method
     */
    @Test
    public void beeSpawnRandomizerTest(){
        int testsAmount = 5;

        ArrayList<Bee> bees = new ArrayList<>();
        for(int i = 0; i < testsAmount ;i++){
            bees.add(new Bee());
        }

        for (Bee bee : bees) {
            assertTrue(bee.getxBeeSpawnLocation() >= Hive.x && bee.getxBeeSpawnLocation() <= Hive.x + Hive.size, "X spawn coordinate is not in hive");
            assertTrue(bee.getyBeeSpawnLocation() >= Hive.y && bee.getyBeeSpawnLocation() <= Hive.y + Hive.size, "Y spawn coordinate is not in hive");
            System.out.println("Hive location " + Hive.x + " " + Hive.y + ", szerokosc " + Hive.size + " .Pszczola, x: " + bee.getxBeeSpawnLocation() + ", y: " + bee.getyBeeSpawnLocation());
        }
    }
    /**
     * Unit test for beeVelocityRandomizer method
     */
    @Test
    public void beeVelocityRandomizerTest(){
        bee = new Bee();

        assertTrue(bee.getxVelocity() >= -2 && bee.getxVelocity() <= 2, "Bee x velocty is not between -2 and 2");
        assertTrue(bee.getyVelocity() >= -2 && bee.getyVelocity() <= 2, "Bee y velocty is not between -2 and 2");

    }
    /**
     * Unit test for getIsRandomized variable
     */
    @Test
    public void getIsRandomizedTest(){
        bee = new Bee();
        assertFalse(bee.getbeeVelocityisRandomized(),"Velocity is randomized, but should not be");
    }
    /**
     * Unit test for getHasSelectedFlower method
     */
    @Test
    public void getHasSelectedFlowerTest(){
        bee = new Bee();
        flower = new Flower();
        assertFalse(bee.getHasSelectedFlower(),"Bee has selected flower but should not");
    }
    /**
     * Unit test for x and y variables
     */
    @Test
    public void getXAndYTest(){
        bee = new Bee();
        flower = new Flower();
        bee.getFlower(flower);

        int movingTests = 100;

        for(int i = 0; i < movingTests ;i++){
            int previousX = bee.getX();
            int previousY = bee.getY();
            bee.beeMove();
            assertTrue(bee.getX() != previousX || bee.getY() != previousY, "Bee has not moved");
        }

    }
    /**
     * Unit test for getBeeSize method
     */
    @Test
    public void getBeeSizeTest(){
        assertEquals(15, Bee.getBeeSize(), "Bee size is not equal 15");
    }
    /**
     * Unit test for getFlower, getNectar, giveNectarToHive methods
     */
    @Test
    public void getFlowerAndGetNectarAndgiveNectarToHiveTest(){
        Hive hive = new Hive();
        bee = new Bee();
        flower = new Flower();
        bee.getFlower(flower);
        bee.getNectar(flower);
        bee.giveNectarToHive(hive);
        assertEquals(hive.todayStoredNectar,20,"Stored nectar in hive is not equal to nectar in flower");
        assertEquals(flower.getNectarAmount(),0,"Flower has nectar, but should not");
    }

}
