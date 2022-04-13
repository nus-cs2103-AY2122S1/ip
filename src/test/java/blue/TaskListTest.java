package blue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void get_noTasks_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(0));
    }
}
