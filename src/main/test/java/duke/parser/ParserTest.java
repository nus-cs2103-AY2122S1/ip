package parser;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTodoTest() throws DukeException {
        Storage s = new Storage("data/test.txt");
        TaskList t = new TaskList(s.loadFile());
        Ui ui = new Ui();
        Command c = Parser.parse("todo finish CS2103 ip");
        c.execute(t, ui, s);
        ArrayList<Task> tasks = t.getAllTasks();
        assertEquals(tasks.get(tasks.size() - 1).toString(), "[T][] finish CS2103 ip");
    }
}
