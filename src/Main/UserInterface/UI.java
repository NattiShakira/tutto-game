package UserInterface;

import Game.Player;
import GameAssets.Cards.Card;
import GameAssets.Die;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class UI {

    public void displayPlayerScores(ArrayList<Player> players) {
        String stringToPrint = "Current standings:";
        for (Player player:players) {
            stringToPrint += "\nPlayer " + player.getName() + ": " + player.getScore() + " points";
        }
        System.out.println(stringToPrint);
    }

    /*public void displayPlayerScores(ArrayList<Player> players) {
        String stringToPrint = "";
        System.out.println("Current standings:");
        for (Player player:players) {
            System.out.printf("Player %s: %d points\n", player.getName(), player.getScore());
        }
    }*/

    public void displayCard(Card card) {
        String a = "";
        String b = "";
        String c = "";
        switch (card.getCardName()) {
            case "Bonus" -> {
                a = "Bonus";
                b = "*****";
                switch (card.getValue()){
                    case 200 -> c = " 200 ";
                    case 300 -> c = " 300 ";
                    case 400 -> c = " 400 ";
                    case 500 -> c = " 500 ";
                    case 600 -> c = " 600 ";
                }
            }
            case "Straight" -> {
                a = "Str8 ";
                b = "*****";
                c = " 2000";
            }
            case "Fireworks" -> {
                a = "҉ * ꙰";
                b = " ҈ ۞ ";
                c = "ѪѪѪѪѪ";
            }
            case "Stop" -> {
                a = "STOP#";
                b = "#STOP";
                c = "STOP#";
            }

            case "Plus/Minus" -> {
                a = "+1000";
                b = " +/- ";
                c = "-1000";
            }
            case "Cloverleaf" -> {
                a = "*****";
                b = " ☘  ";
                c = "*****";
            }
            case "x2" -> {
                a = "x2   ";
                b = "*****";
                c = "   x2";
            }

        }
        String stringToPrint = " ͟͟͟͟͟ \n" + "|"+ a +"|\n" + "|"+ b +"|\n" + "|"+ c +"|\n" + " ͞͞͞͞͞\n";
        System.out.print(stringToPrint);
    }

//    public void rollingDice(){
//        String string1 = "   +-+-+-+-+-+-+-+ +-+-+-+-+\n";
//        String string2 = "   |R|o|l|l|i|n|g| |D|i|c|e|\n";
//        String string3 = "   +-+-+-+-+-+-+-+ +-+-+-+-+\n";
//        String stringToPrint = string1 + string2 + string3;
//        System.out.println(stringToPrint);
//
//        try {
//            Thread.sleep(0);//1000
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
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
//        System.out.println(stringToPrint2);
//
//        try {
//            Thread.sleep(500);//500
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void displayDice(ArrayList<Die> dice) {
        String topString = " ͟͟͟͟͟͟͟͟͟   ";
        String bottomString = " ͞͞͞͞͞͞͞͞͞   ";
        String String11 = "|         |  ";
        String String12 = "|    o    |  ";
        String String13 = "|         |  ";
        String String21 = "|  o      |  ";
        String String22 = "|         |  ";
        String String23 = "|      o  |  ";
        String String31 = "|  o      |  ";
        String String32 = "|    o    |  ";
        String String33 = "|      o  |  ";
        String String41 = "|  o   o  |  ";
        String String42 = "|         |  ";
        String String43 = "|  o   o  |  ";
        String String51 = "|  o   o  |  ";
        String String52 = "|    o    |  ";
        String String53 = "|  o   o  |  ";
        String String61 = "|  o   o  |  ";
        String String62 = "|  o   o  |  ";
        String String63 = "|  o   o  |  ";

        StringBuilder String0 = new StringBuilder();
        StringBuilder String1 = new StringBuilder();
        StringBuilder String2 = new StringBuilder();
        StringBuilder String3 = new StringBuilder();
        StringBuilder String4 = new StringBuilder();

        for (Die die:dice) {
            int checker = die.getEyes();
            if (checker == 1) {
                String0.append(topString);
                String1.append(String11);
                String2.append(String12);
                String3.append(String13);
                String4.append(bottomString);
            }
            if (checker == 2) {
                String0.append(topString);
                String1.append(String21);
                String2.append(String22);
                String3.append(String23);
                String4.append(bottomString);
            }
            if (checker == 3) {
                String0.append(topString);
                String1.append(String31);
                String2.append(String32);
                String3.append(String33);
                String4.append(bottomString);
            }
            if (checker == 4) {
                String0.append(topString);
                String1.append(String41);
                String2.append(String42);
                String3.append(String43);
                String4.append(bottomString);
            }
            if (checker == 5) {
                String0.append(topString);
                String1.append(String51);
                String2.append(String52);
                String3.append(String53);
                String4.append(bottomString);
            }
            if (checker == 6) {
                String0.append(topString);
                String1.append(String61);
                String2.append(String62);
                String3.append(String63);
                String4.append(bottomString);
            }

        }

        String stringToPrint = String0 + "\n" + String1 +"\n" + String2 +"\n" + String3 +"\n" + String4;

        System.out.print(stringToPrint);
    }

    public void displayVictory() {
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

        String stringToPrint = fireworksVictory + lettersVictory;
        System.out.print(stringToPrint);
    }

    public void displayTutto() {
        String stringToPrint = "\n" +
                " ▀█▀ █ █ ▀█▀ ▀█▀ █▀█  █ \n" +
                "  ▀  ▀▀▀  ▀   ▀  ▀▀▀  ▀\n";
        System.out.print(stringToPrint);
    }

//    public void displayStart() {
//        System.out.print(
//                " ___       __   _______   ___       ________  ________  _____ ______   _______           _________  ________          _________  ___  ___  _________  _________  ________          ___       \n" +
//                        "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\___   ___\\\\   __  \\        |\\___   ___\\\\  \\|\\  \\|\\___   ___\\\\___   ___\\\\   __  \\        |\\  \\      \n" +
//                        "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\|___ \\  \\_\\ \\  \\|\\  \\       \\|___ \\  \\_\\ \\  \\\\\\  \\|___ \\  \\_\\|___ \\  \\_\\ \\  \\|\\  \\       \\ \\  \\     \n" +
//                        " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__           \\ \\  \\ \\ \\  \\\\\\  \\           \\ \\  \\ \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\\\\\  \\       \\ \\  \\    \n" +
//                        "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\           \\ \\  \\ \\ \\  \\\\\\  \\           \\ \\  \\ \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\\\\\  \\       \\ \\__\\   \n" +
//                        "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\_______\\           \\ \\__\\ \\ \\_______\\   \\ \\__\\     \\ \\__\\ \\ \\_______\\       \\|__|   \n" +
//                        "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______|            \\|__|  \\|_______|            \\|__|  \\|_______|    \\|__|      \\|__|  \\|_______|           ___ \n" +
//                        "                                                                                                                                                                                        |\\__\\\n" +
//                        "                                                                                                                                                                                        \\|__|\n" +
//                        "                                                                                                                                                                                             \n");
//        try {
//            Thread.sleep(1000);//1000
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}