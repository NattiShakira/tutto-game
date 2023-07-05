package UserInterface;

import Game.Player;
import GameAssets.Cards.*;
import GameAssets.Die;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UITest {

    UI ui = new UI();
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
    void displayPlayerScores() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        player1.setName("Julian");
        player2.setName("Lennard");
        player3.setName("Simon");
        player4.setName("Natalia");
        player1.setScore(5500);
        player2.setScore(4950);
        player3.setScore(4600);
        player4.setScore(4300);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        ui.displayPlayerScores(players);

        String expected = "Current standings:" +
                "\nPlayer Julian: 5500 points" +
                "\nPlayer Lennard: 4950 points" +
                "\nPlayer Simon: 4600 points" +
                "\nPlayer Natalia: 4300 points";

        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void displayCardBonus200() {
        Card bonus200 = new Bonus(200);
        ui.displayCard(bonus200);
        String expected = " ͟͟͟͟͟ \n|Bonus|\n|*****|\n| 200 |\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
    @Test
    void displayCardBonus300() {
        Card bonus300 = new Bonus(300);
        ui.displayCard(bonus300);
        String expected = " ͟͟͟͟͟ \n|Bonus|\n|*****|\n| 300 |\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayCardBonus400() {
        Card bonus400 = new Bonus(400);
        ui.displayCard(bonus400);
        String expected = " ͟͟͟͟͟ \n|Bonus|\n|*****|\n| 400 |\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
    @Test
    void displayCardBonus500() {
        Card bonus500 = new Bonus(500);
        ui.displayCard(bonus500);
        String expected = " ͟͟͟͟͟ \n|Bonus|\n|*****|\n| 500 |\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
    @Test
    void displayCardBonus600() {
        Card bonus600 = new Bonus(600);
        ui.displayCard(bonus600);
        String expected = " ͟͟͟͟͟ \n|Bonus|\n|*****|\n| 600 |\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayCardStraight() {
        Card straight = new Straight();
        ui.displayCard(straight);
        String expected = " ͟͟͟͟͟ \n|Str8 |\n|*****|\n| 2000|\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayCardFireworks() {
        Card fireworks = new Fireworks();
        ui.displayCard(fireworks);
        String expected = " ͟͟͟͟͟ \n|҉ * ꙰|\n| ҈ ۞ |\n|ѪѪѪѪѪ|\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayCardStop() {
        Card stop = new Stop();
        ui.displayCard(stop);
        String expected = " ͟͟͟͟͟ \n|STOP#|\n|#STOP|\n|STOP#|\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
    @Test
    void displayCardPlusMinus() {
        Card plusMinus = new PlusMinus();
        ui.displayCard(plusMinus);
        String expected = " ͟͟͟͟͟ \n|+1000|\n| +/- |\n|-1000|\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayCardCloverleaf() {
        Card cloverleaf = new Cloverleaf();
        ui.displayCard(cloverleaf);
        String expected = " ͟͟͟͟͟ \n|*****|\n| ☘  |\n|*****|\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayCardx2() {
        Card x2 = new TimesTwo();
        ui.displayCard(x2);
        String expected = " ͟͟͟͟͟ \n|x2   |\n|*****|\n|   x2|\n ͞͞͞͞͞\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

//    @Test
//    void rollingDice() {
//        String string1 = "   +-+-+-+-+-+-+-+ +-+-+-+-+\n";
//        String string2 = "   |R|o|l|l|i|n|g| |D|i|c|e|\n";
//        String string3 = "   +-+-+-+-+-+-+-+ +-+-+-+-+\n";
//        String stringToPrint = string1 + string2 + string3;
//
//        String string4 = "                (( ͟͟͟͟͟͟͟\n";
//        String string5 = "     ͟͟͟͟͟͟͟͟     /\\o     o\\\n";
//        String string6 = "    /o      /\\   /  \\       \\\n";
//        String string7 = "   /   o   /o \\ / o  \\o ͟͟͟͟o\\ ))\n";
//        String string8 = "((/͟͟͟͟͟͟o/    \\\\    /o      /\n";
//        String string9 = "  \\o     o\\    / \\  /   o   /\n";
//        String string10 = "   \\o     o\\ o/   \\/ ͟͟͟͟͟o/\n";
//        String string11 = "    \\o ͟͟͟͟o\\/ ))          ))\n";
//        String string12 = "  (( ";
//        String string13 = "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ \n";
//        String stringToPrint2 = string4 + string5 + string6 + string7 + string8 + string9 + string10 + string11 + string12 + string13;
//
//        String expected = stringToPrint + "\r\n" + stringToPrint2 + "\r\n";
//        ui.rollingDice();
//        assertEquals(expected, outputStreamCaptor.toString());
//    }

    @Test
    void displayDice() {
        Die die1 = new Die();
        die1.setEyes(5);
        Die die2 = new Die();
        die2.setEyes(2);
        Die die3 = new Die();
        die3.setEyes(4);
        Die die4 = new Die();
        die4.setEyes(3);
        Die die5 = new Die();
        die5.setEyes(1);
        Die die6 = new Die();
        die6.setEyes(6);
        ArrayList<Die> dice = new ArrayList<>();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);

        String top = " ͟͟͟͟͟͟͟͟͟   " + " ͟͟͟͟͟͟͟͟͟   " + " ͟͟͟͟͟͟͟͟͟   " + " ͟͟͟͟͟͟͟͟͟   " + " ͟͟͟͟͟͟͟͟͟   " + " ͟͟͟͟͟͟͟͟͟   ";
        String firstLine = "|  o   o  |  " + "|  o      |  " + "|  o   o  |  " + "|  o      |  " + "|         |  " + "|  o   o  |  ";
        String secondLine = "|    o    |  " + "|         |  " + "|         |  " + "|    o    |  " + "|    o    |  " + "|  o   o  |  ";
        String thirdLine = "|  o   o  |  " + "|      o  |  " + "|  o   o  |  " + "|      o  |  " + "|         |  " + "|  o   o  |  ";
        String bottom = " ͞͞͞͞͞͞͞͞͞   " + " ͞͞͞͞͞͞͞͞͞   " + " ͞͞͞͞͞͞͞͞͞   " + " ͞͞͞͞͞͞͞͞͞   " + " ͞͞͞͞͞͞͞͞͞   " + " ͞͞͞͞͞͞͞͞͞   ";

        String expectedString = top + "\n" + firstLine + "\n" + secondLine + "\n" + thirdLine + "\n" + bottom;

        ui.displayDice(dice);

        assertEquals(expectedString, outputStreamCaptor.toString());
    }

    @Test
    void displayVictory() {
        String fireworksVictory =
                "                                       .'''.       \n" +
                        "          .'''.     .        *''*     :_\\./_:    . \n" +
                        "         :_\\./_:  _\\(/_  .:.*_\\./_*   : /'\\ : .'.:.'.\n" +
                        "    .'''.: /'\\ :  ./)\\   ':'* /'\\ * :  '..'.  -=:o:=-\n" +
                        "   :_\\./_:'.:::.   ' *'''*    * '.\\'/.' _\\(/_ '.':'.'\n" +
                        "   : /'\\ : :::::    *_\\./_*     -= o =-  /)\\     '  *\n" +
                        "    '...'  ':::'    * /'\\ *     .'/.\\'.   '\n" +
                        "        *            *...*         :\n" +
                        "          *\n" +
                        "          *\n";

        String lettersVictory =
                "██╗   ██╗██╗ ██████╗████████╗ ██████╗ ██████╗ ██╗   ██╗\n" +
                        "██║   ██║██║██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗╚██╗ ██╔╝\n" +
                        "██║   ██║██║██║        ██║   ██║   ██║██████╔╝ ╚████╔╝ \n" +
                        "╚██╗ ██╔╝██║██║        ██║   ██║   ██║██╔══██╗  ╚██╔╝  \n" +
                        " ╚████╔╝ ██║╚██████╗   ██║   ╚██████╔╝██║  ██║   ██║   \n" +
                        "  ╚═══╝  ╚═╝ ╚═════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝  \n";

        String expected = fireworksVictory + lettersVictory;

        ui. displayVictory();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void displayTutto() {
        String expected = "\n" +
                " ▀█▀ █ █ ▀█▀ ▀█▀ █▀█  █ \n" +
                "  ▀  ▀▀▀  ▀   ▀  ▀▀▀  ▀\n";

        ui.displayTutto();
        assertEquals(expected, outputStreamCaptor.toString());
    }

//    @Test
//    void displayStart() {
//        String expected = " ___       __   _______   ___       ________  ________  _____ ______   _______           _________  ________          _________  ___  ___  _________  _________  ________          ___       \n" +
//                "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\___   ___\\\\   __  \\        |\\___   ___\\\\  \\|\\  \\|\\___   ___\\\\___   ___\\\\   __  \\        |\\  \\      \n" +
//                "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\|___ \\  \\_\\ \\  \\|\\  \\       \\|___ \\  \\_\\ \\  \\\\\\  \\|___ \\  \\_\\|___ \\  \\_\\ \\  \\|\\  \\       \\ \\  \\     \n" +
//                " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__           \\ \\  \\ \\ \\  \\\\\\  \\           \\ \\  \\ \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\\\\\  \\       \\ \\  \\    \n" +
//                "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\           \\ \\  \\ \\ \\  \\\\\\  \\           \\ \\  \\ \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\\\\\  \\       \\ \\__\\   \n" +
//                "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\_______\\           \\ \\__\\ \\ \\_______\\   \\ \\__\\     \\ \\__\\ \\ \\_______\\       \\|__|   \n" +
//                "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______|            \\|__|  \\|_______|            \\|__|  \\|_______|    \\|__|      \\|__|  \\|_______|           ___ \n" +
//                "                                                                                                                                                                                        |\\__\\\n" +
//                "                                                                                                                                                                                        \\|__|\n" +
//                "                                                                                                                                                                                             \n";
//        ui.displayStart();
//        assertEquals(expected, outputStreamCaptor.toString());
//    }
}