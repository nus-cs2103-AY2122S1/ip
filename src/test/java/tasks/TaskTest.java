package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import side.tasks.Task;

public class TaskTest {

    @Test
    public void getDescriptionTest() {
        Task t = new Task("Test");
        assertEquals("Test ", t.getDescription());
    }

    @Test
    public void getIsDoneTest() {
        Task t = new Task("Test", true);
        assertTrue(t.getIsDone());
    }

    @Test
    public void markAsDoneTestWithFalse() {
        Task t = new Task("Test");
        assertEquals(t.markAsDone(), "Fine, I'll mark it for you: [T][x]Test ");
    }

    @Test
    public void markAsDoneTestWithTrue() {
        Task t = new Task("Test", true);
        assertEquals(t.markAsDone(), "I'm lazy, stop making me mark the same things again...");
    }

    @Test
    public void toStringTestWithFalse() {
        Task t = new Task("Test");
        assertEquals(t.toString(), "[T][ ]Test ");
    }

    @Test
    public void toStringTestWithTrue() {
        Task t = new Task("Test", true);
        assertEquals(t.toString(), "[T][x]Test ");
    }
}
