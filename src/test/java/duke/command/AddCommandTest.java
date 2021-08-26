package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.BadInputFormatException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateException;
import duke.exception.UnknownTaskTypeException;


public class AddCommandTest {
    @Test
    public void testInvalidDate() {
        assertThrows(InvalidDateException.class, () -> AddCommand.of("event event 1 /at 10-10-2020"));
    }

    @Test
    public void testUnknownTaskType() {
        assertThrows(UnknownTaskTypeException.class, () -> AddCommand.of("unknown event 1 /at 2020-10-10"));
    }

    @Test
    public void testEmptyDescription() {
        assertThrows(EmptyDescriptionException.class, () -> AddCommand.of("event"));
    }

    @Test
    public void testBadInputFormat() {
        assertThrows(BadInputFormatException.class, () -> AddCommand.of("event event 1"));
    }

    @Test
    public void testIsExit()
            throws UnknownTaskTypeException,
            BadInputFormatException,
            EmptyDescriptionException,
            InvalidDateException {
        assertFalse(AddCommand.of("event event 1 /at 2020-10-10").isExit());
    }
}
