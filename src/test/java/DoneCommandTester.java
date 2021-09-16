import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommandTester {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    DoneCommandTester() {
        this.ui = new Ui();
        this.storage = new Storage("DoneCommandTest.txt");
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
    void testDoneCommandWhenNothingInList() {
        executeCommand("done 1");
        assertEquals(0, taskList.size());
    }

    @Test
    void testDoneCommand() {
        executeCommand("todo read books");
        executeCommand("done 1");
        assertEquals("X", taskList.getAllTasks().get(0).getStatusIcon());

    }
}
