package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;

public class ParserTest {
    @Test
    public void parseDateTask_noMatchingCommand_returnNull() {
        try {
            Task task = Parser.parseDateTask("homework /by 03/03/2021", "todo");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid command to create a deadline or event.",
                    e.getMessage());
        }
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
