package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.TaskList;



public class MarkDoneCommandTest {

    @Test
    public void testValidMarkDoneCommand() {
        String expected = "TO MARK DONE: index 10";
        Command actual = new MarkDoneCommand("10");
        assertEquals(
                expected,
                actual.toString()
        );
    }

    @Test
    public void test0Index() {
        Command command = new MarkDoneCommand("0");
        assertThrows(
                InvalidTaskNumberException.class, () -> command.execute(new TaskList(), null, null)
        );
    }

    @Test
    public void testInvalidIndex() {
        Command command = new MarkDoneCommand("1");
        assertThrows(
                InvalidTaskNumberException.class, () -> command.execute(new TaskList(), null, null)
        );
    }
}
