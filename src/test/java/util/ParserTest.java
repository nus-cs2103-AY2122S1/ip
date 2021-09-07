package util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyCommandException;
import duke.exception.IncompleteDescriptionException;
import duke.util.Parser;

/**
 * A test class which tests if Parser's methods work as intended.
 */
public class ParserTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the command is empty.
     */
    @Test
    public void emptyCommandTest() {
        assertThrows(EmptyCommandException.class, () -> Parser.parse(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the command is incomplete.
     */
    @Test
    public void incompleteCommandTest() {
        assertThrows(IncompleteDescriptionException.class, () -> Parser.parse("todo "));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the command consists of purely whitespace.
     */
    @Test
    public void allSpaceCommandTest() {
        assertThrows(EmptyCommandException.class, () -> Parser.parse("      "));
    }
}
