package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    @Test
    public void addCommand_success() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/tasks.txt");

            AddCommand cmd = new AddCommand(TaskList.TaskType.TODO, "task");
            cmd.execute(tasks, ui, storage);

            assertEquals(1, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }

}