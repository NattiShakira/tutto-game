package GameAssets.Cards;

import Game.InputChecker;
import Game.Player;
import GameAssets.Deck;
import GameAssets.Die;
import UserInterface.UI;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Card {
    protected boolean wantToContinue;

    protected boolean tutto;
    public Card() {
        tutto = false;
        wantToContinue = true;
    }
    Scanner s = new Scanner(System.in);
    InputChecker i = new InputChecker();
    UI userInterface = new UI();

    public abstract String getCardName();

    protected void roll(ArrayList<Die> dice) {
        if (dice.isEmpty()) {
            throw new IllegalStateException();
        }
//        userInterface.rollingDice();
        for (Die die : dice) {
            die.rollDie();
        }
    }

    public int extendTurn(Player player, Deck deck, ArrayList<Die> dice) {
        System.out.println("Your Card: " + getCardName());
        ArrayList<Die> passiveDice = new ArrayList<>();
        int temporaryScore = 0;
        boolean turnOver = false;
        while (!turnOver) {
            roll(dice);
            userInterface.displayDice(dice);
            System.out.println();

            if (!optionToPick(dice, passiveDice)) {
                System.out.println("Sorry, there is nothing to put aside.");
                temporaryScore = 0;
                turnOver = true;
            } else {
                System.out.println("Which dice do you want to put aside? " +
                        "(e.g. 551, if you want to put aside two fives and one 1; the order does NOT matter)");
                ArrayList<Integer> chosenOption = validInputDiceChoice(dice, passiveDice);

                temporaryScore += calculatePoints(chosenOption);

                if (chosenOption.size() == dice.size()) {
                    tutto = true;
                    turnOver = true;
                    userInterface.displayTutto();
                    temporaryScore = reachedTutto(temporaryScore);
                }
                removeValidDice(chosenOption, dice, passiveDice);

                System.out.println("Press 'R' to continue rolling or press 'E' to end your turn.");
                String choice = i.validChoiceRE();
                if (choice.equals("E")) {
                    turnOver = true;
                    wantToContinue = false;
                }
            }
        }
        return temporaryScore;
    }

    protected void removeValidDice(ArrayList<Integer> chosenOption, ArrayList<Die> dice, ArrayList<Die> passiveDice) {
        while (!chosenOption.isEmpty()) {
            for (Die die : dice) {
                if (chosenOption.contains(die.getEyes())) {
                    for (int j = 0; j < chosenOption.size(); j++) {
                        if (chosenOption.get(j) == die.getEyes()) {
                            chosenOption.remove(j);
                            break;
                        }
                    }
                    passiveDice.add(die);
                    dice.remove(die);
                    break;
                }
            }
        }
    }

    protected ArrayList<Integer> validInputDiceChoice(ArrayList<Die> activeDice, ArrayList<Die> passiveDice) {
        boolean invalidInput = true;
        ArrayList<Integer> chosenOption = new ArrayList<>();
        String messageIfInvalid = "Invalid choice! Please choose again.";
        while (invalidInput) {
            String input = s.nextLine().trim().replaceAll("\s+", "");
            boolean firstConditionLength = (input.length() > 0 && input.length() <= 6);
            boolean secondConditionIntegersOnly = input.matches("^[1-6]+$");

            if (firstConditionLength && secondConditionIntegersOnly) {
                for (int i = 0; i < input.length(); i++) {
                    chosenOption.add(Integer.parseInt(String.valueOf(input.charAt(i))));
                }
                if (validDiceChoice(chosenOption, activeDice, passiveDice)) {
                    invalidInput = false;
                } else {
                    while (chosenOption.size() > 0) {
                        chosenOption.remove(0);
                    }
                    System.out.println(messageIfInvalid);
                }
            } else {
                System.out.println(messageIfInvalid);
            }
        }
        return chosenOption;
    }

    protected boolean validDiceChoice(ArrayList<Integer> chosenDice, ArrayList<Die> activeDice, ArrayList<Die> passiveDice) {
        if (chosenDice.isEmpty()) {
            throw new IllegalStateException();
        }
        for (int i:chosenDice) {
            assert (i>0 && i<7);
        }
        int Aones = 0, Atwos = 0, Athrees = 0, Afours = 0, Afives = 0, Asixes = 0;
        for (Die die : activeDice) {
            switch (die.getEyes()) {
                case 1 -> Aones++; case 2 -> Atwos++; case 3 -> Athrees++;
                case 4 -> Afours++; case 5 -> Afives++; case 6 -> Asixes++;
            }
        }

        int Bones = 0, Btwos = 0, Bthrees = 0, Bfours = 0, Bfives = 0, Bsixes = 0;
        for (Integer integer : chosenDice) {
            switch (integer) {
                case 1 -> Bones++; case 2 -> Btwos++; case 3 -> Bthrees++;
                case 4 -> Bfours++; case 5 -> Bfives++; case 6 -> Bsixes++;
            }
        }

        boolean firstCondition = Bones <= Aones && Btwos <= Atwos && Bthrees <= Athrees && Bfours <= Afours &&
                Bfives <= Afives && Bsixes <= Asixes;
        boolean secondCondition = Btwos % 3 == 0 && Bthrees % 3 == 0 && Bfours % 3 == 0 && Bsixes % 3 == 0;

        return firstCondition && secondCondition;
    }

    protected int calculatePoints (ArrayList <Integer> option) {
        if (option.isEmpty()) {
            throw new IllegalStateException();
        }
        int score = 0;
        int numsLeft = option.size();
        int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
        for (int i : option) {
            switch (i) {
                case 1 -> ones++; case 2 -> twos++; case 3 -> threes++;
                case 4 -> fours++; case 5 -> fives++; case 6 -> sixes++;
            }
        }
        while (numsLeft > 0) {
            if (ones >= 3) {
                while (ones >= 3) {
                    score += 1000;
                    ones = ones - 3;
                    numsLeft = numsLeft - 3;
                }
            }
            if (sixes >= 3) {
                while (sixes >= 3) {
                    score += 600;
                    sixes = sixes - 3;
                    numsLeft = numsLeft - 3;
                }
            }
            if (fives >= 3) {
                while (fives >= 3) {
                    score += 500;
                    fives = fives - 3;
                    numsLeft = numsLeft - 3;
                }
            }
            if (fours >= 3) {
                while (fours >= 3) {
                    score += 400;
                    fours = fours - 3;
                    numsLeft = numsLeft - 3;
                }
            }
            if (threes >= 3) {
                while (threes >= 3) {
                    score += 300;
                    threes = threes - 3;
                    numsLeft = numsLeft - 3;
                }
            }
            if (twos >= 3) {
                while (twos >= 3) {
                    score += 200;
                    twos = twos - 3;
                    numsLeft = numsLeft - 3;
                }
            }
            if (ones > 0) {
                while (ones > 0) {
                    score += 100;
                    ones--;
                    numsLeft--;
                }
            }
            if (fives > 0) {
                while (fives > 0) {
                    score += 50;
                    fives--;
                    numsLeft--;
                }
            }
        }
        return score;
    }

    public boolean isTutto () {
        return tutto;
    }

    public void resetTutto () {
        tutto = false;
    }

    protected boolean optionToPick (ArrayList<Die> roll, ArrayList<Die> aside) {
        int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
        for (Die die : roll) {
            switch (die.getEyes()) {
                case 1 -> ones++; case 2 -> twos++; case 3 -> threes++;
                case 4 -> fours++; case 5 -> fives++; case 6 -> sixes++;
            }
        }
        return ones != 0 || twos >= 3 || threes >= 3 || fours >= 3 || fives != 0 || sixes >= 3;
    }

    protected int reachedTutto(int score) {
        return 0;
    }

    public boolean wantsToContinue() {
        return wantToContinue;
    }

    public int getValue () {
        return 999;
    }

    public void resetWantsToContinue() {
        wantToContinue = true;
    }
}