import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import duke.Duke;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.task.Deadline;
import duke.task.ToDo;



/**
 * Class for testing Duke using JUnit
 */
public class DukeTest {
    // Deadline
    @Test
    public void toString_newDeadline_correctString() {
        assertEquals(
                "[D][✔] homework (by: Thursday, 11 November 2021)",
                new Deadline("homework", LocalDate.of(2021, 11, 11), true).toString());
    }

    // Event
    @Test
    public void eventDate_newEvent_correctDate() {
        assertEquals(
                LocalDate.of(2021, 11, 11),
                new Event("Walk the dog", LocalDate.of(2021, 11, 11)).getEventDate());
    }

    // ToDo
    @Test
    public void todoSetDone_newTask_true() {
        ToDo todo = new ToDo("homework");
        todo.setDone(true);
        assertTrue(todo.isDone());
    }

    // Storage
    @Test
    public void getFile_always_fileExists() {
        Storage storage = new Storage("data/duke.txt");
        assertEquals("duke.txt", storage.getFile().getName());
    }

    // Duke
    @Test
    public void dukeGetResponse_noTaskName_missingTaskName() {
        Duke duke = new Duke("data/duke.txt");
        assertEquals(
                "Task name cannot be empty!",
                duke.getResponse("todo")
        );
    }
}
