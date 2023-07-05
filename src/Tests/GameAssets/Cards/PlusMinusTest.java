package GameAssets.Cards;

import Game.GameState;
import Game.Player;
import GameAssets.Cards.Card;
import GameAssets.Cards.PlusMinus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlusMinusTest {

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

    PlusMinus plusminus = new PlusMinus();

    @Test
    void getCardName() {
        assertEquals("Plus/Minus", plusminus.getCardName());
        assertNotEquals("Fireworks", plusminus.getCardName());
    }

    @Test
    void extendTurn() {//requires handling random events
    }

    @Test
    void reachedTutto() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nAngi\nMarkus\n1000\n".getBytes());
        System.setIn(in);

        GameState g = GameState.getInstance();
        g.getPlayers().get(0).setScore(1000);
        int test = plusminus.reachedTutto(g.getPlayers().get(1));
        assertEquals(1000, test);
        assertEquals(0,g.getPlayers().get(0).getScore());
    }
}