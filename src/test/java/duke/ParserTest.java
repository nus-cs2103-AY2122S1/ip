package duke;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

public class ParserTest {
    @Test
    public void testListInput() {
        assertEquals(Parser.parse("list"), new ListCommand());
    }

    @Test
    public void testByeInput() {
        assertEquals(Parser.parse("bye"), new ExitCommand());
    }

    @Test
    public void testTodoInput() {
        assertEquals(Parser.parse("todo read book"), new TodoCommand(new String[] { "read book" }));
    }

    @Test
    public void testValidDoneInput() {
        assertEquals(Parser.parse("done 1"), new DoneCommand(new String[] { "0" }));
    }

    @Test
    public void testInvalidDoneInput() {
        assertThrows(DukeException.class, () -> Parser.parse("done ab"));
    }

    @Test
    public void testValidDeleteInput() {
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(new String[] { "0" }));
    }

    @Test
    public void testInvalidDeleteInput() {
        assertThrows(DukeException.class, () -> Parser.parse("delete ab"));
    }

    @Test
    public void testValidDeadlineInput() {
        assertEquals(Parser.parse("deadline return book /by 06-06-2021 18:00"),
                new DeadlineCommand(new String[] { "return book", "06-06-2021 18:00" }));
    }

    @Test
    public void testInvalidDeadlineInput() {
        assertThrows(DukeException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void testValidEventInput() {
        assertEquals(Parser.parse("event return book /at 06-06-2021 18:00"),
                new EventCommand(new String[] { "return book", "06-06-2021 18:00" }));
    }

    @Test
    public void testInvalidEventInput() {
        assertThrows(DukeException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(DukeException.class, () -> Parser.parse("blah"));
    }

    @Test
    public void testMissingDoneDescription() {
        assertThrows(DukeException.class, () -> Parser.parse("done"));
    }

    @Test
    public void testMissingDeadlineDescription() {
        assertThrows(DukeException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void testMissingDeleteDescription() {
        assertThrows(DukeException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void testMissingEventDescription() {
        assertThrows(DukeException.class, () -> Parser.parse("event"));
    }

    @Test
    public void testMissingTodoDescription() {
        assertThrows(DukeException.class, () -> Parser.parse("todo"));
    }
}
