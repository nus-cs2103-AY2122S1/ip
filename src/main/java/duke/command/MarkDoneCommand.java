package duke.command;

import java.io.IOException;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.IndexOutOfBoundException;

/**
 * The MarkDoneCommand class encapsulates information
 * and methods pertaining to "done" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class MarkDoneCommand extends Command {
    private int index;

    /**
     * Creates and initalizes a new MarkDoneCommand with the given index.
     *
     * @param index The index of the Task to be marked as done.
     * @return A new MarkDoneCommand object.
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The MarkDoneCommand marks the task at the specified
     * index of the taskList as done.
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
        taskList.markDone(index);
        ui.add("Nice! I've marked this task as done:");
        ui.add("  " + taskList.get(index));
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }
}