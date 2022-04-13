package jared.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] borrow book", new Todo("borrow book").toString());
    }

    @Test
    public void testSaveFormat() {
        assertEquals("T _ 0 _ buy groceries", new Todo("buy groceries").saveFormat());
    }
}
