package GameAssets;

import java.util.Random;

public class Die {
    private int eyes;

    public Die(){
        rollDie();
    }

    public void rollDie() {
        eyes = randomRoll();
    }

    public int getEyes () {
        return eyes;
    }

    public void setEyes(int num) { // solely for testing purposes
        eyes = num;
    }

    private int randomRoll() {

        int min = 1;
        int max = 6;

        Random random = new Random();

        return random.nextInt(max) + min;
    }
}
