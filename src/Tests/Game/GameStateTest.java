package Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

class GameStateTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void getInstance() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nAngi\nMarkus\n1000\n".getBytes());

        System.setIn(in);
        GameState g = GameState.getInstance();

        String expected =
                "How many players attend the game? (2, 3, or 4)\r\n" +
                        "Enter the name of the first player:\r\n" +
                        "Enter the name of the second player:\r\n" +
                        "How many points are necessary to win the game? (e.g. 6000)\r\n";
        //assertEquals(expected, outputStreamCaptor.toString());

        int test = g.getWinningScore();
        assertEquals(1000, test);

        System.setIn(sysInBackup);
    }

    @Test
    void play() {
    }

    @Test
    void getPlayers() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nAngi\nMarkus\n1000\n".getBytes());
        System.setIn(in);

        GameState g = GameState.getInstance();
        ArrayList<Player> test = g.getPlayers();
        assertEquals(2, test.size());
        System.setIn(sysInBackup);
    }

    @Test
    void getNumPlayers() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nAngi\nMarkus\n1000\n".getBytes());
        System.setIn(in);

        GameState g = GameState.getInstance();
        int test = g.getNumPlayers();
        assertEquals(2, test);
        System.setIn(sysInBackup);
    }

    @Test
    void getWinningScore() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nAngi\nMarkus\n1000\n".getBytes());
        System.setIn(in);

        GameState g = GameState.getInstance();
        int test = g.getWinningScore();
        assertEquals(1000, test);
        System.setIn(sysInBackup);
    }
}