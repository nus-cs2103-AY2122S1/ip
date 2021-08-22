package duke.command;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {
    @Test
    public void isExit_anyInput_false() throws DukeException {
        assertFalse(new AddCommand("todo holder").isExit());
    }
}
