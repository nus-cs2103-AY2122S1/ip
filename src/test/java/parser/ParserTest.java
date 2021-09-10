package parser;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void detectExitCommand_exitCommand_true() {
        String exitCommand = "bye";
        System.setIn(new ByteArrayInputStream(exitCommand.getBytes()));
        boolean isExitCommand = new Parser().detectExitCommand(exitCommand);

        Assertions.assertEquals(true, isExitCommand);
        System.setIn(System.in);
    }

    @Test
    public void detectExitCommand_nonExitCommand_false() {
        String nonExitCommand = "hey";
        System.setIn(new ByteArrayInputStream(nonExitCommand.getBytes()));
        boolean isExitCommand = new Parser().detectExitCommand(nonExitCommand);

        Assertions.assertEquals(false, isExitCommand);
        System.setIn(System.in);
    }
}
