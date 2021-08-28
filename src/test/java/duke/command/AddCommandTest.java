package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void testExecute() {
        try {
            Task task = new Todo("Buy pen");
            Command addCommand = new AddCommand(task);
            addCommand.execute(new TaskList(), new Ui(), new Storage("./data/duke.txt"));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
