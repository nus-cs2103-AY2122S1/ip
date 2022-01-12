package duke.command;

import java.io.IOException;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.IndexOutOfBoundException;

/**
 * The DeleteCommand class encapsulates information
 * and methods pertaining to "delete" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates and initalizes a new DeleteCommand with the given index.
     *
     * @param index The index of the Task to be deleted.
     * @return A new DeleteCommand object.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The DeleteCommand deletes the Task at the specified index of the taskList.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException, DukeException {
        if (!taskList.isValidIndex(index)) {
            throw new IndexOutOfBoundException(taskList.size());
        }
        ui.add("Noted. I've removed this task:");
        ui.add("  " + taskList.get(this.index));
        taskList.delete(index);
        ui.add(String.format("Now you have %d task(s) in the list.", taskList.size()));
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }
}
