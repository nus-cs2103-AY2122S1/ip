package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task testTask = new Task("eat bread");

    @Test
    public void newTaskTest() {
        assertEquals(" ", testTask.getStatusIcon());
    }

    @Test
    public void markCompletedTest() {
        testTask.markCompleted();
        assertEquals("X", testTask.getStatusIcon());
    }
}
