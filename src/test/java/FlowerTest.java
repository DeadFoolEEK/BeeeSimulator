import org.example.Flower;
import org.example.Panel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FlowerTest {

    Flower flower;

    @Test
    public void getXAndYTest(){
        int flowerAmount = 10;

        ArrayList<Flower> flowers = new ArrayList<>();
        for(int i = 0; i < flowerAmount ;i++){
            flowers.add(new Flower());
        }

        for (Flower flower : flowers) {
            assertTrue(flower.getX() <= Panel.PANEL_HEIGHT && flower.getY() >= 0,"Flower not fit into panel [x]");
            assertTrue(flower.getY() <= Panel.PANEL_HEIGHT && flower.getY() >= 0,"Flower not fit into panel [y]");
            System.out.println("Flower coordinates: " + flower.getX() + ", " + flower.getY());
        }

    }

    @Test
    public void getAndIsOccupiedByBeeTest(){
        flower = new Flower();
        assertFalse(flower.getIsOccupiedByBee(),"Flower is occupied, but should not");
        flower.setIsOccupiedByBeeToTrue();
        assertTrue(flower.getIsOccupiedByBee(),"Flower is not occupied, but should not");
    }

    @Test
    public void setAndGetNectarAmountTest(){
        flower = new Flower();
        assertEquals(20,flower.getNectarAmount(),"Basic nectar amount is different than 20");
        int amount = 100;
        flower.setNectarAmount(amount);
        assertEquals(100,flower.getNectarAmount(),"Set nectar amount is different than actual nectar amount");
    }

    @Test
    public void getFlowerSizeTest(){
        assertEquals(30, Flower.getFlowerSize(), "Flower size is not euqal 30, but should be");
    }

}
