package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {

    @Test
    public void testAddTodo() {
        String taskType = "todo";
        String description = "read book";
        AddCommand add = new AddCommand(taskType, description);
        assertEquals(add.getTaskType(), taskType);
        assertEquals(add.getTaskDescription(), description);
    }

    @Test
    public void testAddDeadline() {
        String taskType = "deadline";
        String description = "return book /by 2019-10-04 6pm";
        AddCommand add = new AddCommand(taskType, description);
        assertEquals(add.getTaskType(), taskType);
        assertEquals(add.getTaskDescription(), description);
    }

    @Test
    public void testAddEvent() {
        String taskType = "event";
        String description = "project meeting /at 2018-11-03 8pm";
        AddCommand add = new AddCommand(taskType, description);
        assertEquals(add.getTaskType(), taskType);
        assertEquals(add.getTaskDescription(), description);
    }

    @Test
    public void testExit() {
        AddCommand addCommand = new AddCommand("todo", "read book");
        assertFalse(addCommand.isExit());
    }
}
