package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DoneCommandTest {

    @Test
    public void testDone() {
        int index = 5;
        DoneCommand done = new DoneCommand(index);
        assertEquals(done.getTaskIndex(), 5);
    }

    @Test
    public void testExit() {
        DoneCommand done = new DoneCommand(5);
        assertFalse(done.isExit());
    }
}
