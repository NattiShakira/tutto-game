package Game;

import GameAssets.Cards.Card;
import GameAssets.Cards.Cloverleaf;
import GameAssets.Cards.Fireworks;
import GameAssets.Cards.Stop;
import GameAssets.Die;
import GameAssets.Deck;
import UserInterface.UI;

import java.util.ArrayList;

public class Player {
    private String name;
    private int score;
    UI userInterface = new UI();
    InputChecker i = new InputChecker();
    ArrayList<Die> allDice = new ArrayList<>();

    public Player() {
        score = 0;
        for (int i = 0; i < 6; i++) {
            allDice.add(new Die());
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int points) { //Solely for testing purposes
        score = points;
    }

    public void turn(Deck deck) {
        int tempScore = 0;

        System.out.println("Press 'R' to roll the dice or press 'D' to display the current score.");
        String choice = i.validChoiceDR();

        if (choice.equals("D")) {
            userInterface.displayPlayerScores(GameState.getInstance().getPlayers());
            System.out.println("Press 'R' to roll.");
            i.validR();
        }

        boolean turnOver = false;
        while (!turnOver) {
            Card currentCard = deck.draw();
            userInterface.displayCard(currentCard);

            if (!(currentCard instanceof Stop)) {
                ArrayList<Die> allDiceCopy = (ArrayList<Die>) allDice.clone(); //unchecked Cast, maybe cleaner solution
                tempScore += currentCard.extendTurn(this, deck, allDiceCopy);
            } else {
                tempScore = 0;
                System.out.println("Tough luck! You have to end your turn.");
            }

            if (!currentCard.wantsToContinue()) { // Player doesn't want to continue
                currentCard.resetWantsToContinue();
                turnOver = true;
                if (currentCard.isTutto()) {
                    currentCard.resetTutto();
                }
            } else {                                // Player wants to continue
                if (currentCard.isTutto()) {            // ... and CAN continue
                    currentCard.resetTutto();
                } else {                                // ... but CAN'T continue
                    turnOver = true;
                    if (!(currentCard instanceof Fireworks) && !(currentCard instanceof Cloverleaf)) {
                        tempScore = 0;                  // reset score
                    }
                }
            }
        }
        score += tempScore;
    }

    public void setName(String playerName) {
        name = playerName;
    }

    public String getName() {
        return name;
    }

    public void decreaseScoreBy(int i) {
        if (score < i) {
            System.out.println(getName() + "'s points were decreased by "+ score + "!");
            score = 0;
        } else {
            System.out.println(getName() + "'s points were decreased by "+ i + "!");
            score = score - i;
        }
    }
}
