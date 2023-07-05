package GameAssets;
import GameAssets.Cards.*;

import java.util.*;

public class Deck {

    private Stack<Card> deck = new Stack<>();

    boolean isShuffled;

    public Deck() {
        init();
        shuffle();
    }

    private void init() {
        int numberOfCards = 5;
        for (int i = 0; i < numberOfCards; i++) {
            deck.push(new Fireworks());
            deck.push(new PlusMinus());
            deck.push(new Straight());
            deck.push(new TimesTwo());
            deck.push(new Stop());
            deck.push(new Stop());
            for (int value : Bonus.getValues()) {
                deck.push(new Bonus(value));
            }
        }
        deck.push(new Cloverleaf());
    }
    public void shuffle() {
        Collections.shuffle(deck);
        isShuffled = true;
    }

    //public List<Card> getDeck() {
    //*return Collections.unmodifiableList(deck);
    //}

    //public void setDeck(Stack<Card> deck) {
    //this.deck = deck;
    //}

    //public Card getCard(int index) {
    //return deck.get(index);
    //}

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Card draw() {
        if (isEmpty()) {
            init();
            shuffle();
        }
        return deck.pop();
    }

    public int size(){
        return deck.size();
    }
}