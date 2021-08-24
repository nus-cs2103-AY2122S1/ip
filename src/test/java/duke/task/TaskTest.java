package duke.task;

import duke.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private static String TASK_NOT_DONE_SYMBOL = " ";
    private static String TASK_DONE_SYMBOL = "X";

    @Test
    public void toString_success() {
        String taskDescription = "Lorem ipsum dolor sit amet";
        Task task = new Task(taskDescription);
        assertEquals(String.format("[%s] %s", TASK_NOT_DONE_SYMBOL, taskDescription), task.toString());
        task.markDone();
        assertEquals(String.format("[%s] %s", TASK_DONE_SYMBOL, taskDescription), task.toString());
    }

    @Test
    public void getStatusIcon_success() {
        String taskDescription = "Lorem ipsum dolor sit amet";
        Task task = new Task(taskDescription);
        assertEquals(TASK_NOT_DONE_SYMBOL, task.getStatusIcon());
        task.markDone();
        assertEquals(TASK_DONE_SYMBOL, task.getStatusIcon());
    }
}
