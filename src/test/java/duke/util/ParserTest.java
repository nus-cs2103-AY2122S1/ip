package duke.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyCommandException;

/**
 * A test class which tests if Parser's methods work as intended.
 */
public class ParserTest {

    /**
     * Passes test when EmptyCommandException is thrown when the command is empty.
     */
    @Test
    public void parser_emptyCommand_exceptionThrown() {
        assertThrows(EmptyCommandException.class, () -> Parser.parse(""));
    }

    /**
     * Passes test when EmptyCommandException is thrown when the command consists of purely whitespace.
     */
    @Test
    public void parser_blankCommand_exceptionThrown() {
        assertThrows(EmptyCommandException.class, () -> Parser.parse("      "));
    }
}
