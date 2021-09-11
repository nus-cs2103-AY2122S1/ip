package parser;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Encapsulates tests for Parser.
 */
public class ParserTest {
    /**
     * Tests if exitCommand is detected as one.
     */
    @Test
    public void detectExitCommand_exitCommand_true() {
        String exitCommand = "bye";
        System.setIn(new ByteArrayInputStream(exitCommand.getBytes()));
        boolean isExitCommand = new CommandParser().detectExitCommand(exitCommand);

        Assertions.assertEquals(true, isExitCommand);
        System.setIn(System.in);
    }

    /**
     * Tests if non exitCommand is not detected as an exitCommand.
     */
    @Test
    public void detectExitCommand_nonExitCommand_false() {
        String nonExitCommand = "hey";
        System.setIn(new ByteArrayInputStream(nonExitCommand.getBytes()));
        boolean isExitCommand = new CommandParser().detectExitCommand(nonExitCommand);

        Assertions.assertEquals(false, isExitCommand);
        System.setIn(System.in);
    }
}
