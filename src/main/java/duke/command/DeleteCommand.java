package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * DeleteCommand delets a task to the task list.
 *
 * @author Chng Zi Hao
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task from the task list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @throws DukeException If taskIndex is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.formatPrint(taskList.deleteTask(this.taskIndex));
    }
}
