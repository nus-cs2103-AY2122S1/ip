package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.commands.Command;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;



public class ParserTest {
    @Test
    public void invalidCommand() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            Command cmd = Parser.parseCommand("hello");
            cmd.execute(tasks);
        });
    }

    @Test
    public void invalidDeleteIndexOutOfRange() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            Command cmd = Parser.parseCommand("todo read book");
            cmd.execute(tasks);
            Command nextCmd = Parser.parseCommand("delete 2");
            nextCmd.execute(tasks);
        });
    }

    @Test
    public void invalidDeadlineCommand() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            Command cmd = Parser.parseCommand("deadline iem");
            cmd.execute(tasks);
        });
    }

    @Test
    public void invalidEventCommand() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            Command cmd = Parser.parseCommand("event movie at night");
            cmd.execute(tasks);
        });
    }

    @Test
    public void invalidFindKeyword() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            Command cmd = Parser.parseCommand("find ");
            cmd.execute(tasks);
        });
    }

    @Test
    public void invalidDoneIndexOutOfRange() {
        TaskList tasks = new TaskList();

        assertThrows(DukeException.class, () -> {
            Command cmd = Parser.parseCommand("todo read book");
            cmd.execute(tasks);
            Command nextCmd = Parser.parseCommand("done -1");
            nextCmd.execute(tasks);
        });
    }
}
