package GameAssets.Cards;

import java.util.*;
/*
Bonus: If you accomplish a “TUTTO”, you get the bonus points indicated on
the card in addition to the points you have rolled. If you stop and have not
accomplished a “TUTTO”, you score only the points rolled without getting the
bonus.
 */

public class Bonus extends Card {

    private final int value;
    protected static final List<Integer> values = new ArrayList<>(Arrays.asList(200, 300, 400, 500, 600));

    public Bonus(int value) {
        assert values.contains(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static List<Integer> getValues() {
        return values;
    }

    @Override
    public String getCardName() {
        return "Bonus";
    }

    @Override
    protected int reachedTutto(int score) {
        return score + value;
    }
}