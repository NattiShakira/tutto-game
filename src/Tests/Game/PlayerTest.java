package Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getScore() {
        Player player = new Player();
        assertEquals(0, player.getScore());
    }

    @Test
    void setScore() {
        Player player = new Player();
        player.setScore(100);
        assertEquals(100, player.getScore());
    }

    @Test
    void turn() {//requires handling random events
    }

    @Test
    void setName() {
        Player player = new Player();
        player.setName("Kate");
        assertEquals("Kate", player.getName());
    }

    @Test
    void getName() {
        Player player = new Player();
        player.setName("Kate");
        assertEquals("Kate", player.getName());
    }

    @Test
    void decreaseScoreBy1() {
        Player player = new Player();
        player.setName("Kate");
        player.setScore(1500);
        player.decreaseScoreBy(1000);
        assertEquals(500, player.getScore());
    }
    @Test
    void decreaseScoreBy2() {
        Player player = new Player();
        player.setName("Kate");
        player.setScore(700);
        player.decreaseScoreBy(1000);
        assertEquals(0, player.getScore());
    }
}