package duke;

import duke.Duke;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaskListTest {
    @Test
    public void markTaskDoneTest() {
        TaskList task = new TaskList(new ArrayList<>());
        task.addTask(new Event("attend meeting", "Monday"));
        task.getTask(0).markAsDone();
        assertEquals("[E][X] attend meeting (at: Monday)", task.getTask(0).toString());
    }
}
