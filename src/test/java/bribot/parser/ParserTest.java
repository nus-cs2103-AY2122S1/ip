package bribot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bribot.command.AddCommand;
import bribot.command.Command;
import bribot.exception.DukeException;
import bribot.task.Deadline;
import bribot.task.Event;
import bribot.task.Todo;

public class ParserTest {
    @Test
    public void testDeadline() throws DukeException {
        Command testCommand = Parser.parse("deadline feed spike /by 01/01/2021 1800");
        Command actualCommand = new AddCommand(new Deadline("feed spike",
                                                             "01/01/2021", "1800"));

        assertEquals(testCommand, actualCommand);
    }

    @Test
    public void testEvent() throws DukeException {
        Command testCommand = Parser.parse("event pet spike /at 01/01/2021 1800");
        Command actualCommand = new AddCommand(new Event("pet spike",
                                                        "01/01/2021", "1800"));

        assertEquals(testCommand, actualCommand);
    }

    @Test
    public void testTodo() throws DukeException {
        Command testCommand = Parser.parse("todo do homework");
        Command actualCommand = new AddCommand(new Todo("do homework"));

        assertEquals(testCommand, actualCommand);
    }
}
