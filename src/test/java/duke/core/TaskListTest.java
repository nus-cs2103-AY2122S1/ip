package duke.core;

import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetSize() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Homework"));
        assertEquals(1, taskList.getSize());
        taskList.addTask(new Deadline("Project", "2020-11-11 6pm"));
        assertEquals(2, taskList.getSize());
    }
}
