package captain.command;

import org.junit.jupiter.api.Test;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.Task;
import captain.task.TaskList;
import captain.task.Todo;


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
