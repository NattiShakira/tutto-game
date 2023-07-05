package GameAssets.Cards;

import GameAssets.Cards.Card;
import GameAssets.Cards.Fireworks;
import GameAssets.Die;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FireworksTest {

    Card fireworks = new Fireworks();

    @Test
    void getCardName() {
        assertEquals("Fireworks", fireworks.getCardName());
        assertNotEquals("Bonus", fireworks.getCardName());
    }

    @Test
    void extendTurn() {//requires handling random events
    }

    @Test
    void validInputDiceChoice() {
        ArrayList<Die> activeDice = new ArrayList<>();
        ArrayList<Die> passiveDice = new ArrayList<>();

        Die die1 = new Die();
        die1.setEyes(1);
        Die die2 = new Die();
        die2.setEyes(2);
        Die die3 = new Die();
        die3.setEyes(3);
        Die die4 = new Die();
        die4.setEyes(4);
        Die die5 = new Die();
        die5.setEyes(5);
        Die die6 = new Die();
        die6.setEyes(6);

        activeDice.add(die1);
        activeDice.add(die2);
        activeDice.add(die3);
        activeDice.add(die4);
        activeDice.add(die5);

        passiveDice.add(die5);

        ArrayList<Integer> expectedAList = new ArrayList<>();
        expectedAList.add(1);
        expectedAList.add(5);

        ArrayList<Integer> actualAList = fireworks.validInputDiceChoice(activeDice, passiveDice);

        assertEquals(expectedAList.size(), actualAList.size());

        for (int i=0; i<expectedAList.size(); i++) {
            assertEquals(expectedAList.get(i), actualAList.get(i));
        }
    }
}