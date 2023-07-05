package GameAssets.Cards;

import Game.Player;
import GameAssets.Deck;
import GameAssets.Die;

import java.util.ArrayList;

/*
Straight: Attention! This card changes the rules for valid dice. You have to try to
accomplish a “Straight” and may not stop before you do. A “Straight” consists of all
six numbers 1, 2, 3, 4, 5, 6. As usual, you have to keep at least one valid die
after each roll. In this case, a valid die is a die that shows a number that you have not
yet put aside. If the roll does not contain any valid die, it counts as a null, and you don’t
score any points. But if you accomplish a “Straight”, you score 2,000 points for it. A
“Straight” is considered a “TUTTO” – consequently, you may continue if you want.
 */
public class Straight extends Card {
    @Override
    public String getCardName() {
        return "Straight";
    }

    @Override
    protected int reachedTutto(int score) {
        return score + 2000;
    }

    @Override
    public int extendTurn(Player player, Deck deck, ArrayList<Die> dice) {
        int temporaryScore = 0;
        boolean turnOver = false;
        ArrayList<Die> passiveDice = new ArrayList<>();

        System.out.println("Your Card: " + getCardName());

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
                        "(e.g. 124, if you want to put aside a one, a 2 and a 4; the order does NOT matter)");
                ArrayList<Integer> chosenOption = validInputDiceChoice(dice, passiveDice);

                if (chosenOption.size() == dice.size()) {
                    tutto = true;
                    turnOver = true;
                    userInterface.displayTutto();
                    temporaryScore = reachedTutto(temporaryScore);
                }

                removeValidDice(chosenOption, dice, passiveDice);

                if (tutto) {
                    System.out.println("Press 'R' to continue rolling or press 'E' to end your turn.");
                    String choice = i.validChoiceRE();
                    if (choice.equals("E")) {
                        turnOver = true;
                        wantToContinue = false;
                    }
                } else {
                    System.out.println("Press 'R' to continue rolling.");
                    i.validR();
                }
            }
        }
        return temporaryScore;
    }

    @Override
    protected boolean optionToPick(ArrayList<Die> activeDice, ArrayList<Die> aside) {
        if (aside.isEmpty()) {
            return true;
        }
        for (Die die:activeDice) {
            boolean option = true;
            for (Die die2:aside) {
                if (die.getEyes() == die2.getEyes()) {
                    option = false;
                }
            }
            if (option) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean validDiceChoice(ArrayList<Integer> chosenDice, ArrayList<Die> activeDice, ArrayList<Die> passiveDice) {
        if (chosenDice.isEmpty()) {
            throw new IllegalStateException();
        }
        for (int i:chosenDice) {
            assert (i>0 && i<7);
        }
        int Aones = 0, Atwos = 0, Athrees = 0, Afours = 0, Afives = 0, Asixes = 0;
        for (Die value : activeDice) {
            switch (value.getEyes()) {
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

        if (Bones > Aones || Btwos > Atwos || Bthrees > Athrees || Bfours > Afours ||
                Bfives > Afives || Bsixes > Asixes) {
            return false;
        }

        for (Die die:passiveDice) {
            switch (die.getEyes()) {
                case 1 -> Bones++; case 2 -> Btwos++; case 3 -> Bthrees++;
                case 4 -> Bfours++; case 5 -> Bfives++; case 6 -> Bsixes++;
            }
        }

        return Bones <= 1 && Btwos <= 1 && Bthrees <= 1 && Bfours <= 1 && Bfives <= 1 && Bsixes <= 1;
    }
}