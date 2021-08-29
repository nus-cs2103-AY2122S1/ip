package Duke.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import duke.tasks.ToDos;

public class ToDosTest {
    @Test
    public void testGetSymbol() {
        ToDos todos = new ToDos("testGetSymbol description");

        assertEquals("T", todos.getSymbol());
    }

    @Test
    public void testMarkAsDone() {
        ToDos todos = new ToDos("testGetBy description");

        assertEquals(true, todos.markAsDone().getIsDone());
    }

    @Test
    public void testToString() {
        ToDos todos = new ToDos("testToString description");

        assertEquals("[T][ ] testToString description", todos.toString());
    }

}
