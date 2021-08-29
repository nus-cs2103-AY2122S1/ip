package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

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
