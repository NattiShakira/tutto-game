package GameAssets.Cards;
/*
x2: If you accomplish a “TUTTO”, all points you have rolled so far on this turn are
doubled. If you stop and have not accomplished a “TUTTO”, you score only the
points rolled.
 */
public class TimesTwo extends Card {
    @Override
    public String getCardName() {
        return "x2";
    }

    @Override
    protected int reachedTutto(int score) {
        return score * 2;
    }
}
