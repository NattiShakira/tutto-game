package GameAssets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DieTest {
    Die die = new Die();

    @Test
    void rollDie() {
        switch (die.getEyes()) {
            case 1 -> Assertions.assertEquals(1, die.getEyes());
            case 2 -> Assertions.assertEquals(2, die.getEyes());
            case 3 -> Assertions.assertEquals(3, die.getEyes());
            case 4 -> Assertions.assertEquals(4, die.getEyes());
            case 5 -> Assertions.assertEquals(5, die.getEyes());
            case 6 -> Assertions.assertEquals(6, die.getEyes());
        }
    }

    @Test
    void getEyes() {
        switch (die.getEyes()) {
            case 1 -> Assertions.assertEquals(1, die.getEyes());
            case 2 -> Assertions.assertEquals(2, die.getEyes());
            case 3 -> Assertions.assertEquals(3, die.getEyes());
            case 4 -> Assertions.assertEquals(4, die.getEyes());
            case 5 -> Assertions.assertEquals(5, die.getEyes());
            case 6 -> Assertions.assertEquals(6, die.getEyes());
        }
    }
}