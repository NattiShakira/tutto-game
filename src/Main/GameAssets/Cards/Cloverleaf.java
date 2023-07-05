package GameAssets.Cards;

import Game.GameState;
import Game.Player;
import GameAssets.Deck;
import GameAssets.Die;

import java.util.ArrayList;

/*
Cloverleaf: You have to try to accomplish a “TUTTO” twice in a row on this turn
and may not stop before you do. If you roll a null, you don’t score any points. But
if you succeed, the game ends immediately, and you win – no matter what score
you have!
 */
public class Cloverleaf extends Card {
    @Override
    public String getCardName() {
        return "Cloverleaf";
    }

    public int extendTurn(Player player, Deck deck, ArrayList<Die> dice) {
        int tuttoCounter = 0;
        int temporaryScore = 0;
        boolean turnOver = false;
        ArrayList<Die> passiveDice = new ArrayList<>();

        System.out.println("Your Card: " + getCardName());

        while (!turnOver && tuttoCounter < 2) {
            roll(dice);
            userInterface.displayDice(dice);
            System.out.println();

            if (!optionToPick(dice, passiveDice)) {
                System.out.println("Sorry, there is nothing to put aside.");
                turnOver = true;

            } else {
                System.out.println("Which dice do you want to put aside? " +
                        "(e.g. 551, if you want to put aside two fives and one 1; the order does NOT matter)");
                ArrayList<Integer> chosenOption = validInputDiceChoice(dice, passiveDice);

                if (chosenOption.size() == dice.size()) {
                    tuttoCounter++;
                    while (dice.size() < 6) {
                        dice.add(new Die());
                    }
                    userInterface.displayTutto();
                } else {
                    removeValidDice(chosenOption, dice, passiveDice);
                }

                if (tuttoCounter < 2) {
                    System.out.println("Press 'R' to continue rolling.");
                    i.validR();
                    }
                }
            }
        if (tuttoCounter == 2) {
            System.out.println("Congratulations! You win immediately by reaching Tutto twice in a row!");
            temporaryScore = reachedTutto(player);
            wantToContinue = false;
        }
        return temporaryScore;
    }
    protected int reachedTutto(Player player) {
        GameState g = GameState.getInstance();
        return g.getWinningScore();
    }
}
