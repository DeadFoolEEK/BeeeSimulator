package unitTests;

import org.example.RandomEventGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for RandomEventGenerator Class
 */
public class RandomEventGeneratorTest {
    /**
     * RandomEventGenerator class
     */
    RandomEventGenerator randomEventGenerator;
    /**
     * Unit test for setRandomEventHappenedToFalseT method
     */
    @Test
    public void setRandomEventHappenedToFalseTestWhenActivated(){
        randomEventGenerator = new RandomEventGenerator(true);
        randomEventGenerator.setRandomEventHappenedToFalse();
        assertFalse(randomEventGenerator.getRandomEventHappened());
    }

    /**
     * Unit test that checks if random events chance of happen is correct
     */
    @Test
    public void generateRandomEventTestWhenActivated(){
        randomEventGenerator = new RandomEventGenerator(true);
        int testsAmount = 1000;
        int eventsHappened = 0;

        double totalChangeOfEventsHappening = 0.2;
        double delta = 0.1;

        for(int i = 0; i < testsAmount ;i++){
            randomEventGenerator.generateRandomEvent();
            if(randomEventGenerator.getRandomEventHappened()){
                eventsHappened++;
            }
            randomEventGenerator.setRandomEventHappenedToFalse();
        }
        assertEquals(totalChangeOfEventsHappening, (double) eventsHappened /testsAmount,delta);
        System.out.println("[generateRandomEventTestWhenActivated] % of event happened " + (double)eventsHappened/testsAmount);
     }
    /**
     * Unit test for setRandomEventHappenedToFalseT method
     */
    @Test
    public void generateRandomEventTestWhenDisctivated(){
        randomEventGenerator = new RandomEventGenerator(false);
        int testsAmount = 10;
        int eventsHappened = 0;
        for(int i = 0; i < testsAmount ;i++){
            randomEventGenerator.generateRandomEvent();
            if(randomEventGenerator.getRandomEventHappened()){
                eventsHappened++;
            }
            randomEventGenerator.setRandomEventHappenedToFalse();
        }
        assertEquals(0,eventsHappened);
        System.out.println("[generateRandomEventTestWhenDisctivated] % of event happened " + eventsHappened);
    }

}
