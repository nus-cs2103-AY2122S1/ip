package duke.command;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Storage;
import duke.Ui;
import duke.exception.TaskDoesNotExistException;
import duke.task.TaskList;
import duke.task.Todo;

public class DeleteCommandTest {

    private Storage storage = new Storage("data/duke.txt");
    private TaskList taskList = new TaskList(new ArrayList<>());
    private Ui ui = new Ui();

    /**
     * Tests DeleteCommmand.
     */
    @Test
    public void testDeleteCommand() throws TaskDoesNotExistException {
        Todo todo = new Todo("Do homework");
        taskList.addTask(todo);
        assertEquals(1, taskList.length());
        DeleteCommand cmd = new DeleteCommand(1);
        cmd.execute(taskList, ui, storage);
        assertEquals(0, taskList.length());
    }

}
