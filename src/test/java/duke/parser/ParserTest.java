package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ParserTest {

  @Test
  void parse() throws DukeException {
    Storage s = new Storage("data/test.txt");
    TaskList t = new TaskList(s.loadFile());
    Ui ui = new Ui();
    Command c = Parser.parse("todo finish CS2103 ip");
    c.execute(t, ui, s);
    ArrayList<Task> tasks = t.getAllTasks();
    assertEquals(tasks.get(tasks.size() - 1).toString(), "[T][] finish CS2103 ip");
  }
}