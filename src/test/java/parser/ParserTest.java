package parser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class ParserTest {
    @Test
    public void detectExitCommand_exitCommand_true() {
        String exitCommand = "bye";
        System.setIn(new ByteArrayInputStream(exitCommand.getBytes()));
        boolean isExitCommand = new Parser().detectExitCommand(exitCommand);

        assertEquals(true, isExitCommand);
        System.setIn(System.in);
    }

    @Test
    public void detectExitCommand_nonExitCommand_false() {
        String nonExitCommand = "hey";
        System.setIn(new ByteArrayInputStream(nonExitCommand.getBytes()));
        boolean isExitCommand = new Parser().detectExitCommand(nonExitCommand);

        assertEquals(false, isExitCommand);
        System.setIn(System.in);
    }
}
