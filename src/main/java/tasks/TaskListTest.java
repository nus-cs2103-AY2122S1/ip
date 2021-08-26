package tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void invalidEmptyDescription(){
        TaskList taskList = new TaskList();
        final String invalidMessage = "☹ OOPS!!! The description of a task cannot be empty.";
        try {
            System.out.println(taskList.addTask("todo "));
            assertEquals(invalidMessage, taskList.addTask("todo "));
        } catch (DukeException e) {
            return;
        }
        fail("The task you added has an invalid description");
    }

    @Test
    public void invalidDeadlineDescription(){
        TaskList taskList = new TaskList();
        final String invalidMessage = "☹ OOPS!!! Please use the format: deadline <description> /by yyyy-mm-ddTHH:mm";
        try {
//            System.out.println(taskList.addTask("deadline /by 9999-99-99"));
            assertEquals(invalidMessage, taskList.addTask("deadline /by 9999-99-99"));
        } catch (DukeException e) {
            return;
        }
        fail("The deadline task you added has an invalid description");
    }

}
