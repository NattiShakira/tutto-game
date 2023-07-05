package GameAssets.Cards;

import GameAssets.Cards.TimesTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimesTwoTest {
    TimesTwo timesTwoCard = new TimesTwo();

    @Test
    void getCardName() {
        assertEquals("x2",timesTwoCard.getCardName());
    }

    @Test
    void reachedTutto() {
        int score = 10;
        assertEquals(20, timesTwoCard.reachedTutto(score));
    }
}