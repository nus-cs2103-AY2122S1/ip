package duke;

import duke.command.Command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    /**
     * Tests when todo is not followed by a task name.
     */
    @Test
    void testParse_incorrectInput_exceptionThrown() {
        try {
            Command c = Parser.parse("todo");
            Assertions.fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Tests when event is not proceeded with a /at keyword.
     */
    @Test
    void testParse_eventWithoutAt_exceptionThrown() {
        try {
            Command c = Parser.parse("event wrongInput 2021-10-19");
            Assertions.fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests when deadline is not given a deadline.
     */
    @Test
    void testParse_deadlineWithoutDeadline_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline wrongINput 213");
            Assertions.fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests when deadline is given an incorrect date format.
     */
    @Test
    void testParse_deadlineIncorrectDateFormat_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline a /by 20-10-2012 06:20");
            Assertions.fail();
        } catch (DateTimeParseException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests when delete is not given a valid integer.
     */
    @Test
    void testParse_deleteInvalidInput_exceptionThrown() {
        try {
            Command c = Parser.parse("delete hello");
            Assertions.fail();
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }
}
