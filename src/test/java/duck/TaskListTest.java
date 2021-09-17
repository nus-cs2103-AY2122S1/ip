package duck;

import duck.task.Deadline;
import duck.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    private final TaskList taskList = new TaskList(new Storage("data", "duck.txt"));

    @Test
    public void testIsInvalidIndex() {
        int invalidIndex = 0;
        assertTrue(taskList.isInvalidIndex(invalidIndex));
    }

    @Test
    public void testSetTaskDone() {
        Task toSetDone = new Deadline("Pick up 47 bricks", LocalDate.parse("2021-09-27"));
        taskList.addTask(toSetDone);
        toSetDone.setDone();
        
        String expectedOutput = "Nice! I've marked this task as done:\n"
                + toSetDone.listEntry() + "\n";
        
        assertEquals(expectedOutput, taskList.setTaskDone(0));

        taskList.deleteTask(0);
    }
}
