package GameAssets.Cards;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class BonusTest {
    Bonus bonusCard = new Bonus(200);

    @Test
    void getValue() {
        Assertions.assertEquals(200, bonusCard.getValue());
    }

    @Test
    void getValues() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(200, 300, 400, 500, 600));
        Assertions.assertEquals(expected, Bonus.getValues());
    }

    @Test
    void getCardName() {
        Assertions.assertEquals("Bonus",bonusCard.getCardName());
    }

    @Test
    void reachedTutto() {
        int score = 500;
        Assertions.assertEquals(700, score + bonusCard.getValue());
    }
}