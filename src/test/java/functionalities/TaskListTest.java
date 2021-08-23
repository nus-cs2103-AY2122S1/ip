package functionalities;

import bern.functionalities.TaskList;
import bern.model.Task;
import bern.model.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        ArrayList<Task> arListTask = new ArrayList<>();
        new TaskList().addTask(new ToDo("hehe"), arListTask);
        new TaskList().addTask(new ToDo("hoho"), arListTask);
        assertEquals(2, arListTask.size());
    }
}
