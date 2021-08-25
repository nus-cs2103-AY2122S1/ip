package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void invalidAddKeyword() {
        TaskList tl = new TaskList();

        assertThrows(DukeException.class, () -> {
            tl.addTask("hello");
        });
    }

    @Test
    public void invalidDeleteIndexOutOfRange() {
        TaskList tl = new TaskList();

        assertThrows(DukeException.class, () -> {
            tl.addTask("todo read book");
            tl.deleteTask("delete 2");
        });
    }
}
