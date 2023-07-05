package GameAssets.Cards;



import Game.InputChecker;
import GameAssets.Cards.*;
import GameAssets.Deck;
import GameAssets.Die;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Card bonus200 = new Bonus(200);
    Card bonus300 = new Bonus(300);
    Card bonus400 = new Bonus(400);
    Card bonus500 = new Bonus(500);
    Card bonus600 = new Bonus(600);
    Card cloverleaf = new Cloverleaf();
    Card fireworks = new Fireworks();
    Card plusminus = new PlusMinus();
    Card stop = new Stop();
    Card straight = new Straight();
    Card timestwo = new TimesTwo();

    @Test
    void getCardName() {
        assertEquals("Bonus", bonus200.getCardName());
        assertEquals("Bonus", bonus300.getCardName());
        assertEquals("Bonus", bonus400.getCardName());
        assertEquals("Bonus", bonus500.getCardName());
        assertEquals("Bonus", bonus600.getCardName());

        assertEquals("Cloverleaf", cloverleaf.getCardName());
        assertEquals("Fireworks", fireworks.getCardName());
        assertEquals("Plus/Minus", plusminus.getCardName());
        assertEquals("Stop", stop.getCardName());
        assertEquals("Straight", straight.getCardName());
        assertEquals("x2", timestwo.getCardName());

        assertNotEquals("Cloverleaf", bonus200.getCardName());
        assertNotEquals("Fireworks", bonus300.getCardName());
        assertNotEquals("Plus/Minus", bonus400.getCardName());
        assertNotEquals("Stop", bonus500.getCardName());
        assertNotEquals("Straight", bonus600.getCardName());

        assertNotEquals("x2", cloverleaf.getCardName());
        assertNotEquals("Bonus", fireworks.getCardName());
        assertNotEquals("Bonus", plusminus.getCardName());
        assertNotEquals("Bonus", stop.getCardName());
        assertNotEquals("Bonus", straight.getCardName());
        assertNotEquals("Bonus", timestwo.getCardName());
    }

    @Test
    void roll() {
        //Deck deck = new Deck();
        Card card = new Bonus(600);

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
        ArrayList<Die> dice = new ArrayList<>();
        assertThrows(IllegalStateException.class, () -> card.roll(dice), "Empty List of dice should throw");
    }

    @Test
    void extendTurn() {//requires handling random events
    }

    @Test
    void removeValidDice() {
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

        passiveDice.add(die5);
        passiveDice.add(die6);

        ArrayList<Integer> testChoice = new ArrayList<>();
        testChoice.add(2);
        testChoice.add(3);

        ArrayList<Die> expectedActive = new ArrayList<>();
        expectedActive.add(die1);
        expectedActive.add(die4);

        ArrayList<Die> expectedPassive = new ArrayList<>();
        expectedPassive.add(die5);
        expectedPassive.add(die6);
        expectedPassive.add(die2);
        expectedPassive.add(die3);

        bonus200.removeValidDice(testChoice, activeDice, passiveDice);

        assertEquals(expectedActive.size(), activeDice.size());
        assertEquals(expectedPassive.size(), passiveDice.size());

        for (int i=0; i<expectedActive.size(); i++) {
            assertEquals(expectedActive.get(i), activeDice.get(i));
        }
        for (int i=0; i<expectedPassive.size(); i++) {
            assertEquals(expectedPassive.get(i), passiveDice.get(i));
        }
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

        activeDice.add(die5);
        activeDice.add(die5);
        activeDice.add(die1);
        activeDice.add(die3);

        passiveDice.add(die5);
        passiveDice.add(die5);

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1234567\n551".getBytes());
        System.setIn(in);

        Bonus anyCard = new Bonus(300);
        ArrayList<Integer> test = anyCard.validInputDiceChoice(activeDice, passiveDice);
        ArrayList<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(5);
        expectedArray.add(5);
        expectedArray.add(1);

        String expected = "Invalid choice! Please choose again.\r\n";
        assertEquals(expected, outputStreamCaptor.toString());
        assertEquals(expectedArray.size(), test.size());

        for(int i=0; i<expectedArray.size(); i++) {
            assertEquals(expectedArray.get(i), test.get(i));
        }

        System.setIn(sysInBackup);

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
        activeDice.add(die4);
        activeDice.add(die5);

        passiveDice.add(die6);

        ArrayList<Integer> validTestChoice1 = new ArrayList<>();
        validTestChoice1.add(1);
        validTestChoice1.add(5);

        ArrayList<Integer> validTestChoice2 = new ArrayList<>();
        validTestChoice2.add(5);

        ArrayList<Integer> InvalidTestChoice1 = new ArrayList<>(); //Empty choice

        ArrayList<Integer> InvalidTestChoice2 = new ArrayList<>(); //invalid dice
        InvalidTestChoice2.add(0);
        InvalidTestChoice2.add(7);
        InvalidTestChoice2.add(24);

        ArrayList<Integer> InvalidTestChoice3 = new ArrayList<>(); //invalid choice
        InvalidTestChoice3.add(1);
        InvalidTestChoice3.add(3);
        InvalidTestChoice3.add(5);

        assertTrue(bonus200.validDiceChoice(validTestChoice1, activeDice, passiveDice));
        assertTrue(timestwo.validDiceChoice(validTestChoice2, activeDice, passiveDice));

        assertThrows(IllegalStateException.class, () -> bonus300.validDiceChoice(InvalidTestChoice1, activeDice, passiveDice), "choice cannot be empty");
        assertThrows(AssertionError.class, () -> cloverleaf.validDiceChoice(InvalidTestChoice2, activeDice, passiveDice), "Dice must be ints between 1 and 6");
        assertFalse(plusminus.validDiceChoice(InvalidTestChoice3, activeDice, passiveDice));

    }

    @Test
    void calculatePoints() {
        ArrayList<Integer> validOption1 = new ArrayList<>();//single values
        validOption1.add(5);
        validOption1.add(1);
        validOption1.add(5);

        ArrayList<Integer> validOption2 = new ArrayList<>();//triplet values
        validOption2.add(6);
        validOption2.add(6);
        validOption2.add(6);
        validOption2.add(1);

        ArrayList<Integer> inValidOption1 = new ArrayList<>();

        assertEquals(200, bonus200.calculatePoints(validOption1));
        assertEquals(700, cloverleaf.calculatePoints(validOption2));
        assertThrows(IllegalStateException.class, () -> bonus300.calculatePoints(inValidOption1), "choice cannot be empty");
    }

    @Test
    void isTutto() {
        assertFalse(bonus200.isTutto());
        assertFalse(cloverleaf.isTutto());
        assertFalse(plusminus.isTutto());
        assertFalse(fireworks.isTutto());
        assertFalse(straight.isTutto());
        assertFalse(timestwo.isTutto());
        assertFalse(stop.isTutto());
    }

    @Test
    void resetTutto() {
        bonus200.resetTutto();
        cloverleaf.resetTutto();
        plusminus.resetTutto();
        fireworks.resetTutto();
        straight.resetTutto();
        timestwo.resetTutto();
        stop.resetTutto();

        assertFalse(bonus200.isTutto());
        assertFalse(cloverleaf.isTutto());
        assertFalse(plusminus.isTutto());
        assertFalse(fireworks.isTutto());
        assertFalse(straight.isTutto());
        assertFalse(timestwo.isTutto());
        assertFalse(stop.isTutto());
    }

    @Test
    void optionToPick() {
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

        ArrayList<Die> activeHasOption1 = new ArrayList<>();
        ArrayList<Die> passiveHasOption1 = new ArrayList<>();

        activeHasOption1.add(die3);
        activeHasOption1.add(die2);
        activeHasOption1.add(die2);
        activeHasOption1.add(die1);
        activeHasOption1.add(die5);
        activeHasOption1.add(die1);

        ArrayList<Die> activeHasNoOption1 = new ArrayList<>(); //triplets
        ArrayList<Die> passiveHasNoOption1 = new ArrayList<>();

        activeHasNoOption1.add(die2);
        activeHasNoOption1.add(die4);
        activeHasNoOption1.add(die2);
        activeHasNoOption1.add(die3);

        passiveHasNoOption1.add(die5);
        passiveHasNoOption1.add(die5);

        ArrayList<Die> activeHasOption2 = new ArrayList<>(); //triplets
        ArrayList<Die> passiveHasOption2 = new ArrayList<>();

        activeHasOption2.add(die2);
        activeHasOption2.add(die4);
        activeHasOption2.add(die2);
        activeHasOption2.add(die2);

        passiveHasOption2.add(die5);
        passiveHasOption2.add(die5);



        assertTrue(bonus200.optionToPick(activeHasOption1, passiveHasOption1));
        assertTrue(plusminus.optionToPick(activeHasOption2, passiveHasOption2));
        assertFalse(cloverleaf.optionToPick(activeHasNoOption1, passiveHasNoOption1));

    }

    @Test
    void reachedTutto() {
        int score = 250;
        assertEquals(450, bonus200.reachedTutto(score));
        assertEquals(550, bonus300.reachedTutto(score));
        assertEquals(650, bonus400.reachedTutto(score));
        assertEquals(750, bonus500.reachedTutto(score));
        assertEquals(850, bonus600.reachedTutto(score));
        assertEquals(500, timestwo.reachedTutto(score));
        assertEquals(2250, straight.reachedTutto(score));
    }

    @Test
    void wantsToContinue() {
        assertTrue(bonus200.wantsToContinue());
        assertTrue(bonus300.wantsToContinue());
        assertTrue(bonus400.wantsToContinue());
        assertTrue(bonus500.wantsToContinue());
        assertTrue(bonus600.wantsToContinue());
        assertTrue(straight.wantsToContinue());
        assertTrue(plusminus.wantsToContinue());
        assertTrue(timestwo.wantsToContinue());
        assertTrue(cloverleaf.wantsToContinue());
        assertTrue(fireworks.wantsToContinue());
    }

    @Test
    void getValue() {
        assertEquals(200, bonus200.getValue());
        assertEquals(300, bonus300.getValue());
        assertEquals(400, bonus400.getValue());
        assertEquals(500, bonus500.getValue());
        assertEquals(600, bonus600.getValue());
    }

    @Test
    void resetWantsToContinue() {
        bonus200.resetWantsToContinue();
        bonus300.resetWantsToContinue();
        bonus400.resetWantsToContinue();
        bonus500.resetWantsToContinue();
        bonus600.resetWantsToContinue();
        straight.resetWantsToContinue();
        cloverleaf.resetWantsToContinue();
        fireworks.resetWantsToContinue();
        plusminus.resetWantsToContinue();
        stop.resetWantsToContinue();

        assertTrue(bonus200.wantsToContinue());
        assertTrue(bonus300.wantsToContinue());
        assertTrue(bonus400.wantsToContinue());
        assertTrue(bonus500.wantsToContinue());
        assertTrue(bonus600.wantsToContinue());
        assertTrue(straight.wantsToContinue());
        assertTrue(cloverleaf.wantsToContinue());
        assertTrue(fireworks.wantsToContinue());
        assertTrue(plusminus.wantsToContinue());
        assertTrue(stop.wantsToContinue());
    }
}