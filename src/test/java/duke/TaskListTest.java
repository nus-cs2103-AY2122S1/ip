package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;



public class TaskListTest {
    @Test
    public void invalidAddKeyword() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            tasks.addTask("hello");
        });
    }

    @Test
    public void invalidDeleteIndexOutOfRange() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            tasks.addTask("todo read book");
            tasks.deleteTask("delete 2");
        });
    }
}
