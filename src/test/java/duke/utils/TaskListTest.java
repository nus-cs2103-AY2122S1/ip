package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Task;
import duke.tasks.ToDo;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    public void taskList_AddCommand_success() throws DukeException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("brush teeth");
        taskList.add(todo);
        String expected = "Current List:\n" + "---------------\n" + "ToDo: brush teeth [ ]";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void taskList_markCompleted_success() throws DukeException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("brush teeth");
        taskList.add(todo);
        taskList.markAsCompleted(0);
        String expected = "Current List:\n" + "---------------\n" +
                "ToDo: brush teeth [X]";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void taskList_markCompletedInvalid_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.markAsCompleted(1);
        } catch (InvalidTaskIdException e) {
            String expected = "I'm sorry! I couldn't find a task with that ID. Please check again!";
            assertEquals(expected, e.getMessage());
        }
    }


}
