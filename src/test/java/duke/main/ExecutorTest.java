package duke.main;

import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class ExecutorTest {
    @Test
    public void deleteTask_singleTask_deleteSuccessful() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        tasks.addTask(new ToDo("test"));
        Storage storage = new Storage("test.txt");
        Executor executor = new Executor(storage, ui, tasks);
        executor.parseAndExecute("delete 1");
        assert (tasks.isEmpty());
    }
}
