package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExitCommandTest {

    @Test
    public void testExit() {
        ExitCommand exit = new ExitCommand();
        assertTrue(exit.isExit());
    }
}
