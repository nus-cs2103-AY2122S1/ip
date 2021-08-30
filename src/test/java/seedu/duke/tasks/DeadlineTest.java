package seedu.duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void testGetSymbol() {
        Deadline deadline = new Deadline("testGetSymbol description", "testGetSymbol by");

        assertEquals("D", deadline.getSymbol());
    }

    @Test
    public void testGetBy() {
        Deadline deadline = new Deadline("testGetBy description", "testGetBy by");

        assertEquals("testGetBy by", deadline.getDateTime());
    }

    @Test
    public void testMarkAsDone() {
        Deadline deadline = new Deadline("testMarkAsDone description", "testMarkAsDone by");

        assertTrue(deadline.markAsDone().getIsDone());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("testToString description", "testToString by");

        assertEquals("[D][ ] testToString description (by: testToString by)", deadline.toString());
    }
}