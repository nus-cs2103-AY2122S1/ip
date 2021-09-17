package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    /**
     * Test the isExit method
     */
    @Test
    public void testIsExit() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(true, exitCommand.isExit());
    }
}
