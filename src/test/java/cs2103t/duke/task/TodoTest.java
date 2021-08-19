package cs2103t.duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {

    @Test
    public void testMarkAsDone() {
        Todo t = new Todo("read book");
        t.markAsDone();
        assertTrue(t.getIsDone());
    }

    @Test
    public void testGetDescriptionWithStatus() {
        Todo t = new Todo("read book");
        t.markAsDone();
        assertEquals("[T][X] read book", t.getDescriptionWithStatus());
    }
}