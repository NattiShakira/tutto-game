package GameAssets.Cards;

import Game.GameState;
import GameAssets.Cards.Card;
import GameAssets.Cards.Cloverleaf;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CloverleafTest {

    Cloverleaf cloverleaf = new Cloverleaf();

    @Test
    void getCardName() {
        assertEquals("Cloverleaf", cloverleaf.getCardName());
        assertNotEquals("Straight", cloverleaf.getCardName());
    }

    @Test
    void extendTurn() {//requires handling random events
    }

    @Test
    void reachedTutto() {//requires gamestate
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nAngi\nMarkus\n1000\n".getBytes());
        System.setIn(in);

        GameState g = GameState.getInstance();
        int test = cloverleaf.reachedTutto(g.getPlayers().get(1));
        assertEquals(1000, test);
        assertEquals(0, g.getPlayers().get(0).getScore());

        System.setIn(sysInBackup);
    }
}