package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class AddCommandTest {
    @Test
    public void isExit_anyInput_false() throws DukeException {
        assertFalse(new AddCommand("todo holder").isExit());
    }
}
