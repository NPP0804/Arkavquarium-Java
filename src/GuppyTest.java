import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuppyTest {

    Guppy G1 = new Guppy();
    Guppy G2 = new Guppy(10, -10);
    Guppy G3 = new Guppy(10, -10);

    @Test
    void move() {
    }

    @Test
    void moveTowardsTarget() {
    }

    @Test
    void getKoinCounter() {
        assertTrue(G1.getKoinCounter()==2000);
    }

    @Test
    void setKoinCounter() {
        G1.setKoinCounter(1000);
        assertTrue(G1.koinCounter ==1000);
    }

    @Test
    void equals() {
        Piranha P = new Piranha();
        assertTrue(!G1.equals(P));
        assertTrue(G2.equals(G3));
        assertTrue(!G2.equals(G1));
    }
}