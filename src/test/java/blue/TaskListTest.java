package blue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void get_noTasks_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(0));
    }
}
