package duke;

import duke.exceptions.DukeEmptyTodoDescriptionException;
import duke.exceptions.DukeUnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {

    @Test
    public void testWrongCommand() {
        Duke duke = new Duke();
        assertThrows(DukeUnknownCommandException.class, () -> duke.handleCommandExecution("lol","nothing"));
    }

    @Test
    public void testEmptyDescription() {
        Duke duke = new Duke();
        assertThrows(DukeEmptyTodoDescriptionException.class, () -> duke.handleCommandExecution("todo"," "));
    }
}
