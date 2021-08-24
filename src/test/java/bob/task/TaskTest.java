package bob.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task testTask = new Task("eat bread");
    @Test
    public void newTaskTest(){
        assertEquals(" ", testTask.getStatusIcon());
    }

    @Test
    public void markCompletedTest(){
        testTask.markCompleted();
        assertEquals("X", testTask.getStatusIcon());
    }
}
