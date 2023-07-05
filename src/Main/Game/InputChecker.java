package Game;

import java.util.Scanner;

public class InputChecker {

    Scanner s = new Scanner(System.in);
    public String validChoiceDR() {
        boolean invalidChoice = true;
        String choice = "";
        while (invalidChoice) {
            choice = s.nextLine().trim();
            if (choice.equalsIgnoreCase("R") || choice.equalsIgnoreCase("D")) {
                invalidChoice = false;
            } else {
                System.out.println("Invalid input! Please enter 'R' or 'D'.");
            }
        }
        return choice.toUpperCase();
    }
    public String validR() {
        boolean invalidChoice = true;
        String choice = "";
        while (invalidChoice) {
            choice = s.nextLine().trim();
            if (choice.equalsIgnoreCase("R")) {
                invalidChoice = false;
            } else {
                System.out.println("Invalid input! Please press 'R' to roll.");
            }
        }
        return choice.toUpperCase();
    }
    public String validChoiceRE() {
        boolean invalidChoice = true;
        String choice = "";
        while (invalidChoice) {
            choice = s.nextLine().trim();
            if (choice.equalsIgnoreCase("R") || choice.equalsIgnoreCase("E")) {
                invalidChoice = false;
            } else {
                System.out.println("Invalid input! Please enter 'R' to roll or 'E' to end your turn.");
            }
        }
        return choice.toUpperCase();
    }

    public int validWinningScore() {
        boolean valid = false;
        int score = 0;
        String messageIfInvalid = "Invalid input! Please enter a valid score.";
        while (!valid) {
            String inputScore = s.nextLine().trim();
            try {
                score = Integer.parseInt(inputScore);
            } catch (NumberFormatException ex) {
                System.out.println(messageIfInvalid);
                continue;
            }
            if (score > 0) {
                valid = true;
            } else {
                System.out.println(messageIfInvalid);
            }
        }
        return score;
    }

    public int validNumOfPlayers() {
        boolean valid = false;
        int numPlayers = 0;
        String messageIfInvalid = "Invalid input! Please choose between 2, 3 or 4 players.";
        while (!valid) {
            String inputPlayerCount = s.nextLine().trim();
            try {
                numPlayers = Integer.parseInt(inputPlayerCount);
            } catch (NumberFormatException ex) {
                System.out.println(messageIfInvalid);
            }
            if (numPlayers == 2 || numPlayers == 3 || numPlayers == 4) {
                valid = true;
            } else {
                System.out.println(messageIfInvalid);
            }
        }
        return numPlayers;
    }

    public String validPlayerName() {
        String name = "";
        boolean incorrectName = true;
        while (incorrectName) {
            name = s.nextLine().trim().replaceAll("\s+", " ");
            if (name.matches("^[a-zA-Z ]+$")) {
                incorrectName = false;
            } else {
                System.out.println("Invalid input! Please enter a valid player name.");
            }
        }
        return name;
    }
}
