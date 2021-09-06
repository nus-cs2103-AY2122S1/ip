import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class TaskListTester {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    TaskListTester() {
        this.ui = new Ui();
        this.storage = new Storage("TaskListtest.txt");
        try {
            storage.clear();
            taskList = storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    void executeCommand(String command) {
        Command c = new Parser().parse(command);
        c.execute(taskList, ui, storage);
    }

    @Test
    void testInvalidCommannd() {
        executeCommand("foo");
        assertEquals(taskList.size(), 0);
    }

    @Test
    void testDoneCommand() {
        executeCommand("todo read books");
        executeCommand("done 1");
        assertEquals(taskList.getAllTasks().get(0).getStatusIcon(), "X");
    }
}
