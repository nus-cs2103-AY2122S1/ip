package ponyo.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ponyo.data.task.Deadline;
import ponyo.data.task.Task;
import ponyo.data.task.TaskList;

public class AddCommandTest {
    @Test
    public void addCommand_taskListContainsTask_emptyTaskList() {
        TaskList list = new TaskList();
        Task task = new Deadline("desc", "2020-10-12");
        list.add(task);
        assertTrue(list.contains(task));
        assertEquals(list.retrieveTask(0), task);
    }
}
