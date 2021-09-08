package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.commands.Command;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;



public class TaskListTest {
    @Test
    public void invalidAddKeyword() {
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
}
