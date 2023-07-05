package GameAssets.Cards;

import GameAssets.Cards.Card;
import GameAssets.Cards.Straight;
import GameAssets.Die;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StraightTest {

    Card straight = new Straight();

    @Test
    void getCardName() {
        assertEquals("Straight", straight.getCardName());
        assertNotEquals("Cloverleaf", straight.getCardName());
    }

    @Test
    void reachedTutto() {
        int score = 500;
        assertEquals(2500, straight.reachedTutto(score));
        assertNotEquals(500, straight.reachedTutto(score));
    }

    @Test
    void extendTurn() {//requires handling random events
    }

    @Test
    void optionToPick() {
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
        //1
        ArrayList<Die> activeHasOption1 = new ArrayList<>();
        ArrayList<Die> passiveHasOption1 = new ArrayList<>();

        activeHasOption1.add(die3);
        activeHasOption1.add(die2);
        activeHasOption1.add(die2);
        activeHasOption1.add(die1);
        activeHasOption1.add(die5);
        activeHasOption1.add(die1);
        //2
        ArrayList<Die> activeHasOption2 = new ArrayList<>(); //triplets
        ArrayList<Die> passiveHasOption2 = new ArrayList<>();

        activeHasOption2.add(die5);

        passiveHasOption2.add(die6);
        passiveHasOption2.add(die1);
        activeHasOption2.add(die4);
        activeHasOption2.add(die3);
        activeHasOption2.add(die2);
        //3
        ArrayList<Die> activeHasNoOption1 = new ArrayList<>();
        ArrayList<Die> passiveHasNoOption1 = new ArrayList<>();

        activeHasNoOption1.add(die5);
        activeHasNoOption1.add(die1);

        passiveHasNoOption1.add(die1);
        passiveHasNoOption1.add(die4);
        passiveHasNoOption1.add(die5);
        passiveHasNoOption1.add(die6);




        assertTrue(straight.optionToPick(activeHasOption1, passiveHasOption1));
        assertTrue(straight.optionToPick(activeHasOption2, passiveHasOption2));
        assertFalse(straight.optionToPick(activeHasNoOption1, passiveHasNoOption1));

    }

    @Test
    void validDiceChoice() {
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
        activeDice.add(die5);

        passiveDice.add(die5);
        passiveDice.add(die6);

        ArrayList<Integer> validTestChoice1 = new ArrayList<>();
        validTestChoice1.add(2);

        ArrayList<Integer> validTestChoice2 = new ArrayList<>();
        validTestChoice2.add(3);
        validTestChoice2.add(1);
        validTestChoice2.add(2);

        ArrayList<Integer> InvalidTestChoice1 = new ArrayList<>(); //Empty choice

        ArrayList<Integer> InvalidTestChoice2 = new ArrayList<>(); //invalid dice
        InvalidTestChoice2.add(1);
        InvalidTestChoice2.add(7);
        InvalidTestChoice2.add(3);

        ArrayList<Integer> InvalidTestChoice3 = new ArrayList<>(); //invalid choice
        InvalidTestChoice3.add(1);
        InvalidTestChoice3.add(3);
        InvalidTestChoice3.add(5);

        assertTrue(straight.validDiceChoice(validTestChoice1, activeDice, passiveDice));
        assertTrue(straight.validDiceChoice(validTestChoice2, activeDice, passiveDice));

        assertThrows(IllegalStateException.class, () -> straight.validDiceChoice(InvalidTestChoice1, activeDice, passiveDice), "choice cannot be empty");
        assertThrows(AssertionError.class, () -> straight.validDiceChoice(InvalidTestChoice2, activeDice, passiveDice), "Dice must be ints between 1 and 6");
        assertFalse(straight.validDiceChoice(InvalidTestChoice3, activeDice, passiveDice));

    }
}