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
        Task task = new Todo("Buy pen");
        Command addCommand = new AddCommand(task);
        try {
            addCommand.execute(new TaskList(), new Ui(), new Storage("./data/duke.txt"));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
