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

    @Test
    public void parseAndUpdate() throws DukeException {
        Storage storage = Storage.createStorage("data/test.txt");
        TaskList taskList = new TaskList();
        storage.load(taskList);
        Command command = new Parser(taskList).parse("todo finish ip");
        command.execute();
        Task task = taskList.get(1);
        assertEquals("[T][ ] finish ip", task.toString());
        Command command1 = new Parser(taskList).parse("update /i 1 /n finish");
        command1.execute();
        Task task1 = taskList.get(1);
        assertEquals("[T][ ] finish", task1.toString());
    }

    @Test
    public void parseAndUpdateDeadline() throws DukeException {
        Storage storage = Storage.createStorage("data/test.txt");
        TaskList taskList = new TaskList();
        storage.load(taskList);
        Command command = new Parser(taskList).parse("deadline finish ip /by 2021-10-10 10:00");
        command.execute();
        Task task = taskList.get(1);
        assertEquals("[D][ ] finish ip (by: Oct 10 2021 10:00:00)", task.toString());
        Command command1 = new Parser(taskList).parse("update /i 1 /n finish hw /d 2021-10-10 11:00");
        command1.execute();
        Task task1 = taskList.get(1);
        assertEquals("[D][ ] finish hw (by: Oct 10 2021 11:00:00)", task1.toString());
    }
}
