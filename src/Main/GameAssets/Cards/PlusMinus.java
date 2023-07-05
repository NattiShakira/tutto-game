package GameAssets.Cards;

import Game.GameState;
import Game.Player;
import GameAssets.Deck;
import GameAssets.Die;

import java.util.ArrayList;

/*
Plus/Minus: You must try to accomplish a “TUTTO” and may not stop before
you do. If you roll a null, you don’t score any points. But if you succeed, you score
exactly 1,000 points, irrespective of the number of points you have rolled. Besides
this, the leading player has 1,000 points deducted.
If more than one player is leading with the same number of points, each of them
has 1,000 points deducted. Nevertheless, you, as the player who is currently rolling
the dice, score 1,000 points only once. If it is the leading player who reveals this
card, naturally he doesn’t have to deduct any points from his score when he
accomplishes a “TUTTO”,
 */
public class PlusMinus extends Card {
    @Override
    public String getCardName() {
        return "Plus/Minus";
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
                System.out.println("Which dice do you want to put aside? " +
                        "(e.g. 551, if you want to put aside two fives and one 1; the order does NOT matter)");
                ArrayList<Integer> chosenOption = validInputDiceChoice(dice, passiveDice);

                if (chosenOption.size() == dice.size()) {
                    tutto = true;
                    turnOver = true;
                    userInterface.displayTutto();
                    temporaryScore = reachedTutto(player);
                }

                removeValidDice(chosenOption, dice, passiveDice);

                if (!tutto) {
                    System.out.println("Press 'R' to continue rolling.");
                    i.validR();
                }
            }
        }
        return temporaryScore;
    }

    protected int reachedTutto(Player currentPlayer) {
        GameState g = GameState.getInstance();
        ArrayList<Player> leaders = new ArrayList<>();
        leaders.add(g.getPlayers().get(0));
        for (int i = 1; i < g.numPlayers; i++) {
            if (g.getPlayers().get(i).getScore() > leaders.get(0).getScore()) {
                while (!leaders.isEmpty()) {
                    leaders.remove(0);
                }
                leaders.add(g.getPlayers().get(i));
            }
            else if (g.getPlayers().get(i).getScore() == leaders.get(0).getScore()) {
                leaders.add(g.getPlayers().get(i));
            }
        }

        leaders.removeIf(player -> player == currentPlayer);

        if (!(leaders.isEmpty())) {
            for (Player player:leaders) {
                player.decreaseScoreBy(1000);
            }
        }
        System.out.println(currentPlayer.getName() + "'s score was increased by 1000!");
        return 1000;
    }
}
