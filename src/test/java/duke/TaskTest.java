package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;

/**
 * JUnit test class for {@code Task}.
 */
public class TaskTest {

    /**
     * Tests the {@code getDescription()} method.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Test description", new Task("Test description").getTaskName());
        assertEquals("This is true.", new Task("This is true.").getTaskName());
    }

    /**
     * Tests the {@code isDone()} method.
     */
    @Test
    public void testIsCompleted() {
        assertFalse(new Task("Test description").isDone());
        assertFalse(new Task("Hello world").isDone());
    }

    /**
     * Tests the {@code toggleIsCompleted()} method.
     */
    @Test
    public void testToggleIsCompleted() {
        Task testTask = new Task("Toggle me!!!!");
        assertFalse(testTask.isDone());
        testTask.toggleDone();
        assertTrue(testTask.isDone());
        testTask.toggleDone();
        assertTrue(testTask.isDone());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        Task testTask = new Task("Do you like pizza?");
        assertEquals("[ ] Do you like pizza?", testTask.toString());
        testTask.toggleDone();
        assertEquals("[X] Do you like pizza?", testTask.toString());

    }
}
