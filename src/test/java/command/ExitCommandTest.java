package command;

import jwbot.command.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void isExitTest() {
        ExitCommand exitCommand = new ExitCommand("");
        assertEquals(true, exitCommand.isExit());
    }
}
