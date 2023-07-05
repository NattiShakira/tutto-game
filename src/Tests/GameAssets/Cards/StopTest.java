package GameAssets.Cards;

import GameAssets.Cards.Bonus;
import GameAssets.Cards.Card;
import GameAssets.Cards.Stop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StopTest {

    Card stopCard = new Stop();

    @Test
    void getCardName() {
        assertEquals("Stop", stopCard.getCardName());
        assertNotEquals("Straight", stopCard.getCardName());
    }
}