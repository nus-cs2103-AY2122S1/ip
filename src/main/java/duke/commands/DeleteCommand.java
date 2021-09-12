package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Class that contains the delete command
 *
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted */
    private int index;

    /**
     * Constructor for the delete command class
     *
     * @param index The index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        if (this.index < 1 || this.index > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid index number :(\n");
        }

        Task deleted = taskList.delete(index);
        return ui.deleteMessage(taskList, deleted);
    }
}
