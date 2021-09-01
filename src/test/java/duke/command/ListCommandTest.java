package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    /**
     * Test the isExit method
     */
    @Test
    public void testIsExit() {
        ListCommand listCommand = new ListCommand();
        assertEquals(false, listCommand.isExit());
    }
}
