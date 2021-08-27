package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void invalidEmptyDescription() {
        TaskList taskList = new TaskList();
        String invalidMessage = "☹ OOPS!!! The description of a task cannot be empty.";
        assertEquals(invalidMessage,taskList.addTask("todo "));
    }

    @Test
    public void invalidDeadlineDescription() {
        TaskList taskList = new TaskList();
        final String invalidMessage = "☹ OOPS!!! Please use the format: deadline <description> /by yyyy-mm-ddTHH:mm";
        assertEquals(invalidMessage, taskList.addTask("deadline /by 9999-99-99"));
    }

    @Test
    public void invalidEventDescription() {
        TaskList taskList = new TaskList();
        final String invalidMessage = "☹ OOPS!!! Please use the format: event <description> " +
                "/from yyyy-mm-ddTHH:mm /to yyyy-mm-ddTHH:mm";
        assertEquals(invalidMessage, taskList.addTask("event /by 9999-99-99"));
    }

}

