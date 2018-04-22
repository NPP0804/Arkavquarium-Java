import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PiranhaTest {

    Piranha P1 = new Piranha();
    Piranha P2 = new Piranha(10,-10);
    Piranha P3 = new Piranha(10,-10);

    @Test
    void move() {
    }

    @Test
    void moveTowardsTarget() {
    }

    @Test
    void equals() {
        assertTrue(!P1.equals(P2));
        assertTrue(P3.equals(P2));
        Guppy G = new Guppy();
        assertTrue(!P1.equals(G));
    }

}