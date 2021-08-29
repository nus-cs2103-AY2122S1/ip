package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * DoneCommand sets a task as done
 *
 * @author Chng Zi Hao
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskIndex The index of the task to be mark as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks specified task as done.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @throws DukeException If taskIndex is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.formatPrint(taskList.markTaskDone(this.taskIndex));
    }
}
