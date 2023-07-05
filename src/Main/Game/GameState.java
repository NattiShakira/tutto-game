package Game;

import GameAssets.Deck;
import UserInterface.UI;

import java.util.ArrayList;
import static java.lang.String.CASE_INSENSITIVE_ORDER;

public class GameState {
    public final int numPlayers;
    private final Deck deck;
    private final int winningScore;
    private final InputChecker i = new InputChecker();
    private final UI userInterface = new UI();
    private final static ArrayList<Player> players = new ArrayList<>();
    private final static GameState instance = new GameState();

    private GameState() {
        players.add(new Player());
        players.add(new Player());
        numPlayers = getNumberOfPlayers();
        initPlayers();
        deck = new Deck();
        winningScore = decideWinningScore();
    }

    public static GameState getInstance() {
        return instance;
    }

    public void play() {
        boolean noWinnerYet = true;
        int currentPlayer = 0;
//        userInterface.displayStart();
        while (noWinnerYet) {
            System.out.printf("%s's turn!\n", players.get(currentPlayer).getName());
            players.get(currentPlayer).turn(deck);

            if (hasWon(players.get(currentPlayer))) {
                userInterface.displayVictory();
                System.out.printf("%s has won the Game!", players.get(currentPlayer).getName());
                noWinnerYet = false;
            }
            if (currentPlayer == numPlayers - 1) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }
        }
    }

    private int decideWinningScore() {
        System.out.println("How many points are necessary to win the game? (e.g. 6000)");
        return i.validWinningScore();
    }

    private int getNumberOfPlayers() {
        System.out.println("How many players attend the game? (2, 3, or 4)");
        return i.validNumOfPlayers();
    }

    private void initPlayers() {
        String name;
        ArrayList<String> playerNames = new ArrayList<>();
        for (int j = 0; j < numPlayers; j++) {
            String x;
            if (j == 0) {
                x = "first";
            } else if (j == 1) {
                x = "second";
            } else if (j == 2) {
                players.add(new Player());
                x = "third";
            } else {
                players.add(new Player());
                x = "fourth";
            }
            System.out.printf("Enter the name of the %s player:\n", x);
            name = i.validPlayerName();
            playerNames.add(name);
        }
        
        playerNames.sort(CASE_INSENSITIVE_ORDER);
        for (int j = 0; j < numPlayers; j++) {
            players.get(j).setName(playerNames.get(j));
        }
    }

    private boolean hasWon(Player player) {
        return (player.getScore() >= winningScore);
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
}

    public int getWinningScore() {
        return winningScore;
    }

    public int getNumPlayers() { //for testing purposes
        return numPlayers;
    }
}
