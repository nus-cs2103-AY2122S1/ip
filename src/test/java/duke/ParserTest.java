package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class ParserTest {
    @Test
    public void parseDateTask_noMatchingCommand_returnNull() throws DukeException {
        Task task = Parser.parseDateTask("homework /by 03/03/2021", "todo");
        assertNull(task);
    }

    @Test
    public void parseDateTask_deadlineWithNoBy_dukeExceptionThrown() {
        try {
            Parser.parseDateTask("homework 03/03/2021", "deadline");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Oops!!! Deadlines or events should contain a description, followed by "
                            + "a /by or /at respectively, followed by a date.",
                    e.getMessage());
        }
    }

}
