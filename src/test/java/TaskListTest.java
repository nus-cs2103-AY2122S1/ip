import duke.Task;
import duke.TaskList;
import duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();

        Task newTask = new ToDo("TEST ADD TASK", false);
        taskList.addTask(newTask);

        assertEquals("[T][ ] TEST ADD TASK", taskList.getTasks().get(0).toString());
    }
}
