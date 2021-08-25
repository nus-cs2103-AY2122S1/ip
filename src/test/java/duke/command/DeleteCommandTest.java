package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_success() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("data/tasks.txt");
            
            tasks.addTask(TaskList.TaskType.TODO, "task");
            assertEquals(1, tasks.getListSize());

            DeleteCommand cmd = new DeleteCommand(1);
            cmd.execute(tasks, ui, storage);
            assertEquals(0, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }
    
}
