package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void testIsExit() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(true, exitCommand.isExit());
    }
}
