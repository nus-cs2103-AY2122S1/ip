package duke.utils;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIDException;
import duke.tasks.Task;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void taskList_AddCommand_success() throws DukeException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("brush teeth");
        taskList.add(todo);
        String expected = "List:\n" + "---------------\n" + "ToDo: brush teeth [ ]";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void taskList_markCompleted_success() throws DukeException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("brush teeth");
        taskList.add(todo);
        taskList.markAsCompleted(0);
        String expected = "List:\n" + "---------------\n" +
                "ToDo: brush teeth [X]";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void taskList_markCompletedInvalid_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.markAsCompleted(1);
        } catch (InvalidTaskIDException e) {
            String expected = "I'm sorry! I couldn't find a task with that ID. Please check again!";
            assertEquals(expected, e.getMessage());
        }
    }


}
