package seedu.duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    @Test
    public void testGetSymbol() {
        ToDos todos = new ToDos("testGetSymbol description");

        assertEquals("T", todos.getSymbol());
    }

    @Test
    public void testMarkAsDone() {
        ToDos todos = new ToDos("testGetBy description");

        assertTrue(todos.markAsDone().getIsDone());
    }

    @Test
    public void testToString() {
        ToDos todos = new ToDos("testToString description");

        assertEquals("[T][ ] testToString description", todos.toString());
    }

}
