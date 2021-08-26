package duke;

import duke.command.Command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void testParse_incorrectInput_exceptionThrown() {
        try {
            Command c = Parser.parse("todo");
            Assertions.fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void testParse_eventWithoutBy_exceptionThrown() {
        try {
            Command c = Parser.parse("event wrongInput 2021-10-19");
            Assertions.fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    void testParse_deadlineWithoutDeadline_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline wrongINput 213");
            Assertions.fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    void testParse_deadlineIncorrectDateFormat_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline a /by 20-10-2012 06:20");
            Assertions.fail();
        } catch (DateTimeParseException e) {
            assertTrue(true);
        }
    }

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
