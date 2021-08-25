package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskConstructor() {
        Task task = new Task("My first task");
        String expected = "[ ] My first task";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testIncompleteStatus() {
        Task task = new Task("My first task");
        String expected = " ";
        assertEquals(expected, task.getStatusIcon());
    }

    @Test
    public void testCompleteStatus() {
        Task task = new Task("My first task");
        task.markAsDone();
        String expected = "X";
        assertEquals(expected, task.getStatusIcon());
    }

}