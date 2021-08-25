package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UnknownCommandTest {
    @Test
    public void testExit() {
        UnknownCommand uk = new UnknownCommand();
        assertFalse(uk.isExit());
    }
}
