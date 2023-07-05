package Game;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InputCheckerTest {

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

    @Test
    public void validChoiceDR() {

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("r".getBytes()); //user should give "R" as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();
        String test = i1.validChoiceDR(); //returns String "R" or "D" if input is valid

        assertEquals("R", test);

        ByteArrayInputStream in2 = new ByteArrayInputStream("Invalid input\nD".getBytes());
        System.setIn(in2);
        InputChecker i2 = new InputChecker();
        String test2 = i2.validChoiceDR(); //returns String "R" or "D" if input is valid

        String expected = "Invalid input! Please enter 'R' or 'D'.\r\n";

        assertEquals(expected, outputStreamCaptor.toString());
        assertEquals("D", test2);

        System.setIn(sysInBackup);
    }

    @Test
    void validR() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("?\nr".getBytes()); //user should give "R" as input
        System.setIn(in);
        InputChecker i = new InputChecker();
        String test = i.validR(); //returns String "R" or "D" if input is valid

        String expected = "Invalid input! Please press 'R' to roll.\r\n";
        assertEquals(expected, outputStreamCaptor.toString());

        assertEquals("R", test);

        System.setIn(sysInBackup);
    }

    @Test
    void validChoiceRE() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("r".getBytes()); //user should give "R" as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();
        String test = i1.validChoiceDR(); //returns String "R" or "D" if input is valid

        assertEquals("R", test);

        ByteArrayInputStream in2 = new ByteArrayInputStream("Not_R_or_E\ne".getBytes());
        System.setIn(in2);
        InputChecker i2 = new InputChecker();
        String test2 = i2.validChoiceRE(); //returns String "R" or "D" if input is valid

        String expected = "Invalid input! Please enter 'R' to roll or 'E' to end your turn.\r\n";

        assertEquals(expected, outputStreamCaptor.toString());
        assertEquals("E", test2);

        System.setIn(sysInBackup);
    }


    @Test
    void validWinningScore() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("ImpossibleToParse\n500".getBytes()); //user should give "R" as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();
        int test1 = i1.validWinningScore(); //returns String "R" or "D" if input is valid

        String expected1 = "Invalid input! Please enter a valid score.\r\n";
        assertEquals(expected1, outputStreamCaptor.toString());

        assertEquals(500, test1);

        System.setIn(sysInBackup);


    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void validWinningScoreException() throws NumberFormatException {

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("-20\n750".getBytes()); //user should give 2, 3, or 4 as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();

        exception.expect(NumberFormatException.class);

        int test1 = i1.validWinningScore(); //returns String number of players if input is valid

        assertEquals(750, test1);

        System.setIn(sysInBackup);
    }

    @Test
    void validNumOfPlayers() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("5\n3".getBytes()); //user should give 2, 3, or 4 as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();
        int test1 = i1.validNumOfPlayers(); //returns String number of players if input is valid

        String expected1 = "Invalid input! Please choose between 2, 3 or 4 players.\r\n";
        assertEquals(expected1, outputStreamCaptor.toString());

        assertEquals(3, test1);

        System.setIn(sysInBackup);
    }

    @Test
    void validNumOfPlayersException() throws NumberFormatException {

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("Invalid\n2".getBytes()); //user should give 2, 3, or 4 as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();

        exception.expect(NumberFormatException.class);

        int test1 = i1.validNumOfPlayers(); //returns String number of players if input is valid


        assertEquals(2, test1);

        System.setIn(sysInBackup);
    }

    @Test
    void validPlayerName() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("?!Invalid!?\nValid name".getBytes()); //user should give 2, 3, or 4 as input
        System.setIn(in1);
        InputChecker i1 = new InputChecker();
        String test1 = i1.validPlayerName(); //returns String number of players if input is valid

        String expected1 = "Invalid input! Please enter a valid player name.\r\n";
        assertEquals(expected1, outputStreamCaptor.toString());

        assertEquals("Valid name", test1);

        System.setIn(sysInBackup);
    }
}