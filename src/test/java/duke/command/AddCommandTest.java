package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;

public class AddCommandTest {
    @Test
    public void addCommand_success() {
        try {
            TaskList tasks = new TaskList();
            Storage storage = new Storage("data/tasks.txt");

            AddCommand cmd = new AddCommand(TaskList.TaskType.TODO, "task");
            cmd.execute(tasks, storage);

            assertEquals(1, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }

}
