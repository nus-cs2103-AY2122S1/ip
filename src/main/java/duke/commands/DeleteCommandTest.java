package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {

    @Test
    public void testDelete() {
        int index = 5;
        DeleteCommand del = new DeleteCommand(index);
        assertEquals(del.getDeletionIndex(), 5);
    }

    @Test
    public void testExit() {
        DeleteCommand del = new DeleteCommand(5);
        assertFalse(del.isExit());
    }
}
