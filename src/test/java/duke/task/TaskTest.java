package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    /**
     * Tests Task constructor.
     */
    @Test
    public void testTaskConstructor() {
        Task task = new Task("My first task");
        String expected = "[ ] My first task";
        assertEquals(expected, task.toString());
    }

    /**
     * Tests status icon when task is uncompleted.
     */
    @Test
    public void testIncompleteStatus() {
        Task task = new Task("My first task");
        String expected = " ";
        assertEquals(expected, task.getStatusIcon());
    }

    /**
     * Tests status icon when task is completed.
     */
    @Test
    public void testCompleteStatus() {
        Task task = new Task("My first task");
        task.markAsDone();
        String expected = "X";
        assertEquals(expected, task.getStatusIcon());
    }

}
