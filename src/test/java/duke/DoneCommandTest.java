package duke;

import duke.commands.DoneCommand;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {

    @Test
    public void doneCommand_wrongInput_exceptionThrown() {
        try {
            DoneCommand d = new DoneCommand("done");
        } catch (DukeException e) {
            assertEquals("Please specify the task number.", e.getMessage());
        }
    }

    @Test
    public void doneCommand_wrongNumberFormat_exceptionThrown() {
        try {
            DoneCommand d = new DoneCommand("done five");
        } catch (DukeException e) {
            assertEquals("Please append a task number after 'done'.", e.getMessage());
        }
    }
}
