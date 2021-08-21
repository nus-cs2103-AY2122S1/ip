package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void markAsDone_negativeTaskId_exceptionThrown() {
        try {
            new TaskList(new ArrayList<Task>()).markAsDone(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task index specified!", e.getMessage());
        }
    }

    @Test
    public void deleteTask_negativeTaskId_exceptionThrown() {
        try {
            new TaskList(new ArrayList<Task>()).deleteTask(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task index specified!", e.getMessage());
        }
    }
}

