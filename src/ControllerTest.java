import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller c;

    @BeforeEach
    void setUp(){
        Akuarium a = new Akuarium();
        c = new Controller(a);
    }

    @Test
    void getAkuarium() {
        Akuarium a = new Akuarium();
        c = new Controller(a);
        assertSame(c.getAkuarium(), a);
    }

    @Test
    void processGuppy() {
    }

    @Test
    void processPiranha() {
    }

    @Test
    void processMakanan() {
    }

    @Test
    void processSiput() {
    }

    @Test
    void processKoin() {
    }

    @Test
    void addKoin() {
        assertTrue(c.getAkuarium().getListKoin().isEmpty());
        c.addKoin(0,0,5);
        assertTrue(!c.getAkuarium().getListKoin().isEmpty());
    }

    @Test
    void addGuppy() {
        assertTrue(c.getAkuarium().getListGuppy().isEmpty());
        c.addGuppy();
        assertTrue(!c.getAkuarium().getListGuppy().isEmpty());
    }

    @Test
    void addPiranha() {
        assertTrue(c.getAkuarium().getListPiranha().isEmpty());
        c.addPiranha();
        assertTrue(!c.getAkuarium().getListPiranha().isEmpty());
    }

    @Test
    void addMakanan() {
        assertTrue(c.getAkuarium().getListMakanan().isEmpty());
        c.addMakanan(2);
        assertTrue(!c.getAkuarium().getListMakanan().isEmpty());
    }
}