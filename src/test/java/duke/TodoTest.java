package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

/**
 * JUnit test class for {@code Todo}.
 */
public class TodoTest {
    /**
     * Tests the inheritance of {@code Todo} from its superclass {@code Task}.
     */
    @Test
    public void testInheritance() {
        Todo testToDo = new Todo("Goodnight world.");
        assertEquals("Goodnight world.", testToDo.getTaskName());
        assertFalse(testToDo.isDone());
        testToDo.toggleDone();
        assertTrue(testToDo.isDone());
        assertFalse(new Todo("Not completed").isDone());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[T][ ] Test todo", new Todo("Test todo").toString());
        assertEquals("[T][ ] Get coffee", new Todo("Get coffee").toString());
    }
}
