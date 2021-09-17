package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    @Test
    public void todoTaskNameTest() {
        TodoTask task = new TodoTask("Test task");
        assertEquals("Test task", task.getName());
    }

    @Test
    public void todoTaskTypeTest() {
        TodoTask task = new TodoTask("Test task");
        assertEquals(Task.TaskType.TODO, task.getType());
    }
}
