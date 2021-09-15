package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DateParsingFailException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateFormatException;
import duke.exception.MissingArgumentException;

/**
 * A test class which tests if DeadlineCommand's methods work as intended.
 */
public class DeadlineCommandTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the deadline description is empty.
     */
    @Test
    public void deadlineCommand_emptyDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new DeadlineCommand(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the deadline description consists of purely whitespace.
     */
    @Test
    public void deadlineCommand_blankDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new DeadlineCommand("          "));
    }

    /**
     * Passes test when MissingArgumentException is thrown when the deadline description misses "/at" argument.
     */
    @Test
    public void deadlineCommand_missingArgument_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () -> new DeadlineCommand("some dummy text."));
        assertThrows(MissingArgumentException.class, () -> new DeadlineCommand("some dummy text. 2019-03-25"));
        assertThrows(MissingArgumentException.class, () -> new DeadlineCommand("2019-03-25"));
        assertThrows(MissingArgumentException.class, () -> new DeadlineCommand("text /at 2019-03-25"));
    }

    /**
     * Passes test when InvalidDateFormatException is thrown when the deadline description has invalid date format.
     */
    @Test
    public void deadlineCommand_invalidDateFormat_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, () -> new DeadlineCommand("some text. /by tomorrow"));
        assertThrows(InvalidDateFormatException.class, () -> new DeadlineCommand("some text. /by 2019/03/25"));
        assertThrows(InvalidDateFormatException.class, () -> new DeadlineCommand("some text. /by 2019.03.25"));
    }

    /**
     * Passes test when DateParsingFailException is thrown when the date failed to be parsed into LocalDate instance.
     */
    @Test
    public void deadlineCommand_dateParseFail_exceptionThrown() {
        assertThrows(DateParsingFailException.class, () -> new DeadlineCommand("some text. /by 2019-09-31"));
        assertThrows(DateParsingFailException.class, () -> new DeadlineCommand("some text. /by 2020-02-30"));
    }
}
