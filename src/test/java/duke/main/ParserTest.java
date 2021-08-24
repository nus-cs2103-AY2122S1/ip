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
        Parser.parse(tasks, storage, "delete 1", ui);
        assert (tasks.isEmpty());
    }
}
