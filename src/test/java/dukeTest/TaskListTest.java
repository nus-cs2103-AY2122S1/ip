package dukeTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Task;
import duke.TaskList;


/**
 * Test class for TaskList class
 */
public class TaskListTest {
    @Test
    public void addTaskTest() throws DukeException {
        TaskList test = new TaskList();
        Task task = new Task.Deadline("test", false, "2021-12-12");
        test.addTask(task);
        assertEquals(task,
                test.getTask(0));
    }

    @Test
    public void removeTaskTest() throws DukeException {
        Task task = new Task.Deadline("test", false, "2021-12-12");
        ArrayList<Task> temp = new ArrayList<>();
        temp.add(task);
        TaskList test = new TaskList(temp);
        test.deleteTask(0);
        assertEquals(new TaskList().size(), test.size());
    }
}
