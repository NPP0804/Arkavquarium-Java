import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AkuariumTest {
    private Akuarium a;
    private int x = 500;
    private int y = 300;

    @BeforeEach
    void setUp(){
        a = new Akuarium(x, y);
    }

    @Test
    void getX(){
        assertTrue(a.getX() == x);
    }

    @Test
    void getY() {
        assertTrue(a.getY() == y);
    }

    @Test
    void getListMakanan() {
        assertNotNull(a.getListMakanan());
    }

    @Test
    void getListGuppy() {
        assertNotNull(a.getListGuppy());
    }

    @Test
    void getListPiranha() {
        assertNotNull(a.getListPiranha());
    }

    @Test
    void getListKoin() {
        assertNotNull(a.getListKoin());
    }

    @Test
    void getSiput() {
        assertNotNull(a.getSiput());
        assertTrue(a.getSiput() instanceof Siput);
    }

    @Test
    void koinAvailable() {
        assertTrue(a.koinAvailable() == !a.getListKoin().isEmpty());
    }

    @Test
    void guppyAvailable() {
        assertTrue(a.guppyAvailable() == !a.getListGuppy().isEmpty());
    }

    @Test
    void makananAvailable() {
        assertTrue(a.makananAvailable() == !a.getListMakanan().isEmpty());
    }

    @Test
    void searchMakanan() {
        Makanan m = new Makanan(0, 0);
        a.getListMakanan().add(m);
        int x = 0;
        int y = 3;
        assertEquals(a.searchMakanan(x,y), m);
    }

    @Test
    void searchGuppy() {
        Guppy g = new Guppy(0, 0);
        a.getListGuppy().add(g);
        int x = 0;
        int y = 3;
        assertEquals(a.searchGuppy(x,y), g);
    }

    @Test
    void searchKoin() {
        Koin k = new Koin(0, 0);
        a.getListKoin().add(k);
        int x = 0;
        int y = 3;
        assertEquals(a.searchKoin(x,y), k);
    }

    @Test
    void updateMakanan() {
        Makanan m = new Makanan(10, 10);
        a.getListMakanan().add(m);
        assertTrue(!a.getListMakanan().isEmpty());
        m.setY(0);
        a.updateMakanan();
        assertTrue(a.getListMakanan().isEmpty());
    }

    @Test
    void updatePiranha() {
        Piranha p = new Piranha();
        a.getListPiranha().add(p);
        assertTrue(!a.getListPiranha().isEmpty());
        p.setMati(true);
        a.updatePiranha();
        assertTrue(a.getListPiranha().isEmpty());
    }

    @Test
    void updateGuppy() {
        Guppy g = new Guppy();
        a.getListGuppy().add(g);
        assertTrue(!a.getListGuppy().isEmpty());
        g.setMati(true);
        a.updateGuppy();
        assertTrue(a.getListGuppy().isEmpty());
    }
}