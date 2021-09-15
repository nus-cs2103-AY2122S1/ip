package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DateParsingFailException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateFormatException;
import duke.exception.MissingArgumentException;

/**
 * A test class which tests if EventCommand's methods work as intended.
 */
public class EventCommandTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the event description is empty.
     */
    @Test
    public void eventCommand_emptyDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new EventCommand(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the event description consists of purely whitespace.
     */
    @Test
    public void eventCommand_blankDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new EventCommand("          "));
    }

    /**
     * Passes test when MissingArgumentException is thrown when the event description misses "/at" argument.
     */
    @Test
    public void eventCommand_missingArgument_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> new EventCommand("some dummy text."));
        assertThrows(MissingArgumentException.class, () -> new EventCommand("some dummy text. 2019-03-25"));
        assertThrows(MissingArgumentException.class, () -> new EventCommand("2019-03-25"));
        assertThrows(MissingArgumentException.class, () -> new EventCommand("text /by 2019-03-25"));
    }

    /**
     * Passes test when InvalidDateFormatException is thrown when the event description has invalid date format.
     */
    @Test
    public void eventCommand_invalidDateFormat_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, () -> new EventCommand("some text. /at tomorrow"));
        assertThrows(InvalidDateFormatException.class, () -> new EventCommand("some text. /at 2019/03/25"));
        assertThrows(InvalidDateFormatException.class, () -> new EventCommand("some text. /at 2019.03.25"));
    }

    /**
     * Passes test when DateParsingFailException is thrown when the date failed to be parsed into LocalDate instance.
     */
    @Test
    public void eventCommand_dateParseFail_exceptionThrown() {
        assertThrows(DateParsingFailException.class, () -> new EventCommand("some text. /at 2019-09-31"));
        assertThrows(DateParsingFailException.class, () -> new EventCommand("some text. /at 2020-02-30"));
    }
}
