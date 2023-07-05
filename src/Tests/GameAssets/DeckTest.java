package GameAssets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void shuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        Deck deck1 = new Deck();
        deck1.shuffle();
        assertEquals(deck.size(), deck1.size());


    }

    //@Test
    //void getDeck() {
    //Deck deck = new Deck();

    //}

    //@Test
    //void setDeck() {
    //Deck deck = new Deck();
    //}

    //@Test
    //void getCard() {
    //}

    @Test
    void isEmpty() {
        Deck deck = new Deck();
        for (int i = 0; i < 56; i++) {
            deck.draw();
            if (i < 55) {
                assertFalse(deck.isEmpty());
            }
        }
        assertTrue(deck.isEmpty());

    }

    @Test
    void draw() {
        Deck deck = new Deck();
        deck.draw();
        assertEquals(55, deck.size());
        for (int i = 0; i < 55; i++) {
            deck.draw();
        }
        assertTrue(deck.isEmpty());
        deck.draw();
        assertEquals(55, deck.size());

    }
}