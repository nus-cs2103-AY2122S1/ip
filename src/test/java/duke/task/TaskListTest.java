package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

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

