package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;

public class TaskTest {
    @Test
    public void toString_notDone_correctStringReturned() {
        String description = "a task";
        Task task = new Task(description);
        assertEquals("[ ] a task", task.toString());
    }

    @Test
    public void toString_done_correctStringReturned() {
        String description = "done task";
        Task task = new Task(description);
        task.markAsDone();
        assertEquals("[X] done task", task.toString());
    }
}
