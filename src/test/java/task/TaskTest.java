package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskTest {
    @Test
    public void statusIcon() {
        Task task = new Task("New Task");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void setDone() {
        Task task = new Task("New Task");
        task.setDone();
        assertEquals("X", task.getStatusIcon());
    }
}
