import duke.taskList.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void invalidEmptyDescription() {
        TaskList taskList = new TaskList();
        String invalidMessage = "☹ OOPS!!! The description of a task cannot be empty.";
        Todo todo = new Todo("read book", false);
        Assertions.assertEquals(invalidMessage,taskList.addTask(todo));
    }

    @Test
    public void invalidDeadlineDescription() {
        TaskList taskList = new TaskList();
        final String invalidMessage = "☹ OOPS!!! Please use the format: deadline <description> /by yyyy-mm-ddTHH:mm";
        Deadline deadline = new Deadline("join sports club", "9999-99-99", false);
        Assertions.assertEquals(invalidMessage, taskList.addTask(deadline));
    }

    @Test
    public void invalidEventDescription() {
        TaskList taskList = new TaskList();
        final String invalidMessage = "☹ OOPS!!! Please use the format: event <description> " +
                "/from yyyy-mm-ddTHH:mm /to yyyy-mm-ddTHH:mm";
        Event event = new Event("this is an event", "9999-99-99", false);
        Assertions.assertEquals(invalidMessage, taskList.addTask(event));
    }

}

