package functionalities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bern.functionalities.TaskList;
import bern.model.Task;
import bern.model.ToDo;


/**
 * A class to encapsulates tests for TaskList functions.
 */
public class TaskListTest {
    /**
     * A test for addTask method.
     */
    @Test
    public void addTaskTest() {
        ArrayList<Task> arListTask = new ArrayList<>();
        new TaskList().addTask(new ToDo("hehe"), arListTask);
        new TaskList().addTask(new ToDo("hoho"), arListTask);
        assertEquals(2, arListTask.size());
    }
}
