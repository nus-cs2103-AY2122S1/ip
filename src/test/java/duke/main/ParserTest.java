package duke.main;

import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.Test;

public class ParserTest {
    @Test
    public void deleteTask_singleTask_deleteSuccessful() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        tasks.addTask(new ToDo("test"));
        Storage storage = new Storage("test.txt");
        Parser parser = new Parser(storage, ui, tasks);
        parser.parse("delete 1");
        assert (tasks.isEmpty());
    }
}
