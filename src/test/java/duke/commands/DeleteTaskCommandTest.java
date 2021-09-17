package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.TaskList;

public class DeleteTaskCommandTest {

    @Test
    public void testValidDeleteCommand() {
        String expected = "TO DELETE: index 10";
        Command actual = new DeleteTaskCommand("10");
        assertEquals(
                expected,
                actual.toString()
        );
    }

    @Test
    public void test0Index() {
        Command command = new DeleteTaskCommand("0");
        assertThrows(
                InvalidTaskNumberException.class, () -> command.execute(new TaskList(), null, null)
        );
    }

    @Test
    public void testInvalidIndex() {
        Command command = new DeleteTaskCommand("1");
        assertThrows(
                InvalidTaskNumberException.class, () -> command.execute(new TaskList(), null, null)
        );
    }
}
