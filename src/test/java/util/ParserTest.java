package util;

import duke.exception.EmptyDescriptionException;

import duke.util.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A test class which tests if Parser's methods work as intended.
 */
public class ParserTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the command is empty.
     */
    @Test
    public void emptyCommandTest() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the command is incomplete.
     */
    @Test
    public void incompleteCommandTest() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("todo "));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the command consists of purely whitespace.
     */
    @Test
    public void allSpaceCommandTest() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("      "));
    }

}
