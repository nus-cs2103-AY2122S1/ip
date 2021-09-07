package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * A class to test the parser.
 */
public class ParserTest {

    /**
     * Tests the parser.
     * @throws DukeException if the input is not accepted.
     */
    @Test
    public void parse() throws DukeException {
        Storage storage = Storage.createStorage("data/test.txt");
        TaskList taskList = new TaskList();
        storage.load(taskList);
        Command command = new Parser(taskList).parse("todo finish ip");
        command.execute();
        Task task = taskList.get(1);
        assertEquals(task.toString(), "[T][ ] finish ip");
    }
}
