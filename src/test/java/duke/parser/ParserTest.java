package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Tests the parser.
 */
class ParserTest {
    /**
     * Tests the functionality of parser.
     * @throws DukeException if the input is not accepted.
     */
    @Test
    public void parse() throws DukeException {
        Storage s = new Storage("data/test.txt");
        TaskList t = new TaskList(s.loadFile());
        Ui ui = new Ui();
        Command c = Parser.parse("todo finish CS2103 ip");
        c.execute(t, ui, s);
        ArrayList<Task> tasks = t.getAllTasks();
        assertEquals(tasks.get(tasks.size() - 1).toString(), "[T][] finish CS2103 ip");
    }
}
