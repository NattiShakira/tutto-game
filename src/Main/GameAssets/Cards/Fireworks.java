package GameAssets.Cards;

import Game.Player;
import GameAssets.Deck;
import GameAssets.Die;

import java.util.ArrayList;

/*
Fireworks: You have to keep throwing the dice until you roll a null. After each roll,
you need to keep all valid single dice and triplets. If you accomplish a “TUTTO”,
you have to continue without revealing a new card. Your turn ends only when you
roll a null. However, you score all points you have rolled on this turn.
 */
public class Fireworks extends Card {
    @Override
    public String getCardName() {
        return "Fireworks";
    }

    @Override
    public int extendTurn(Player player, Deck deck, ArrayList<Die> dice) {
        int temporaryScore = 0;
        boolean turnOver = false;
        ArrayList<Die> passiveDice = new ArrayList<>();
        wantToContinue = false;

        System.out.println("Your Card: " + getCardName());

        while (!turnOver) {
            try {
                roll(dice);
            } catch (IllegalStateException e) {
                while (dice.size() < 6) {
                    dice.add(new Die());
                }
                roll(dice);
            }
            userInterface.displayDice(dice);
            System.out.println();

            if (!optionToPick(dice, passiveDice)) {
                System.out.println("Sorry, there is nothing to put aside.");
                turnOver = true;
            } else {
                System.out.println("All valid single dice and triplets are automatically put aside.");
                ArrayList<Integer> chosenOption = validInputDiceChoice(dice, passiveDice);

                if (chosenOption.size() == dice.size()) {
                    userInterface.displayTutto();
                    System.out.println("You can continue rolling without revealing a new card!");
                }

                temporaryScore += calculatePoints(chosenOption);
                removeValidDice(chosenOption, dice, passiveDice);

                System.out.println("Press 'R' to continue rolling.");
                i.validR();
            }
        }
        return temporaryScore;
    }

    @Override
    protected ArrayList<Integer> validInputDiceChoice(ArrayList<Die> activeDice, ArrayList<Die> passiveDice) {
        int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;

        for (Die die : activeDice) {
            switch (die.getEyes()) {
                case 1 -> ones++; case 2 -> twos++; case 3 -> threes++;
                case 4 -> fours++; case 5 -> fives++; case 6 -> sixes++;
            }
        }

        StringBuilder choice = new StringBuilder();
        while (ones > 0) {
            if (ones >= 3) {
                choice.append("111");
                ones = ones - 3;
            } else {
                choice.append("1");
                ones--;
            }
        }
        while (sixes >= 3) {
            choice.append("666");
            sixes = sixes - 3;
        }
        while (fives > 0) {
            if (fives >= 3) {
                choice.append("555");
                fives = fives - 3;
            } else {
                choice.append("5");
                fives--;
            }
        }
        while (fours >= 3) {
            choice.append("444");
            fours = fours - 3;
        }
        while (threes >= 3) {
            choice.append("333");
            threes = threes - 3;

        }
        while (twos >= 3) {
            choice.append("222");
            twos = twos - 3;
        }

        ArrayList<Integer> chosenOption = new ArrayList<>();
        for (int i = 0; i < choice.length(); i++) {
            chosenOption.add(Integer.parseInt(String.valueOf(choice.charAt(i))));
        }

        return chosenOption;
    }
}