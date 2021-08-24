package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void testIsExit() {
        ListCommand listCommand = new ListCommand();
        assertEquals(false, listCommand.isExit());
    }
}
