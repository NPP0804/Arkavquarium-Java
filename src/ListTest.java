import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    List<Integer> L1 = new List<Integer>();
    List<Double> L2 = new List<Double>();

    @Test
    void getFirst() {
        assertTrue(L1.getFirst()==null);
        assertTrue(L2.getFirst()==null);

        L1.add(1);
        assertTrue(L1.getFirst()== L1.first);
        assertTrue(L1.getFirst().getInfo()== 1);

        L1.add(2);
        assertTrue(L1.getFirst()== L1.first);
        assertTrue(L1.getFirst().getInfo()== 1);

    }

    @Test
    void getLast() {
        assertTrue(L1.getLast()==null);
        assertTrue(L2.getLast()==null);

        L1.add(1);
        assertTrue(L1.getLast()== L1.last);
        assertTrue(L1.getLast().getInfo()== 1);

        L1.add(2);
        assertTrue(L1.getLast()== L1.last);
        assertTrue(L1.getLast().getInfo() == 2);

    }

    @Test
    void isEmpty() {
        assertTrue(L1.isEmpty());

        L1.add(1);
        assertTrue(!L1.isEmpty());
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void get() {
    }

    @Test
    void find() {
    }
}