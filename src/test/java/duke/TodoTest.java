package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString1() {
        assertEquals("[T][ ] read a book", new Todo("read a book").toString());
    }

    @Test
    public void testToString2() {
        assertEquals("[T][ ] cook dinner", new Todo("cook dinner").toString());
    }

    @Test
    public void testToData1() {
        assertEquals("T~S~0~S~read a book", new Todo("read a book").toData());
    }

    @Test
    public void testToData2() {
        assertEquals("T~S~0~S~cook dinner", new Todo("cook dinner").toData());
    }
}
