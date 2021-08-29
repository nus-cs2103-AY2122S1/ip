package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void deleteTaskById_invalidId_exceptionThrown() {
        TaskList tasks = new TaskList();
        int taskId = 0;
        try {
            tasks.deleteTaskById(taskId);
            fail("Should throw InvalidTaskSelectedException");
        } catch (Exception e) {
            assertEquals("Invalid task selected!", e.getMessage());
        }
    }
}
