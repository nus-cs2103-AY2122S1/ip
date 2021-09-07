package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(Parser.parse("list"), new ListCommand());
    }

    @Test
    public void testByeInput() {
        Assertions.assertEquals(Parser.parse("bye"), new ExitCommand());
    }

    @Test
    public void testTodoInput() {
        Assertions.assertEquals(Parser.parse("todo read book"), new TodoCommand("read book"));
    }

    @Test
    public void testValidDoneInput() {
        Assertions.assertEquals(Parser.parse("done 1"), new DoneCommand("0"));
    }

    @Test
    public void testInvalidDoneInput() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("done ab"));
    }

    @Test
    public void testValidDeleteInput() {
        Assertions.assertEquals(Parser.parse("delete 1"), new DeleteCommand("0"));
    }

    @Test
    public void testInvalidDeleteInput() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("delete ab"));
    }

    @Test
    public void testValidDeadlineInput() {
        Assertions.assertEquals(Parser.parse("deadline return book /by 06-06-2021 18:00"),
                new DeadlineCommand("return book", "06-06-2021 18:00"));
    }

    @Test
    public void testInvalidDeadlineInput() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void testValidEventInput() {
        Assertions.assertEquals(Parser.parse("event return book /at 06-06-2021 18:00"),
                new EventCommand("return book", "06-06-2021 18:00"));
    }

    @Test
    public void testInvalidEventInput() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void testInvalidCommand() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("blah"));
    }

    @Test
    public void testMissingDoneDescription() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("done"));
    }

    @Test
    public void testMissingDeadlineDescription() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void testMissingDeleteDescription() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void testMissingEventDescription() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("event"));
    }

    @Test
    public void testMissingTodoDescription() {
        Assertions.assertThrows(DukeException.class, () -> Parser.parse("todo"));
    }
}
