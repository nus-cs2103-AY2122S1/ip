package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task.
 *
 * @author Sherman Ng Wei Sheng
 */
public class DeleteCommand extends Command {
    private boolean isExit;
    private int index;

    /**
     * Constructor for the delete command.
     *
     * @param index The index of the task in the TaskList to be deleted (1-indexed).
     */
    public DeleteCommand(int index) {
        this.isExit = false;
        this.index = index;
    }

    /**
     * Returns true if the command is a program terminating command.
     *
     * @return True if it is a terminating command and false otherwise.
     */
    @Override
    public boolean isAExitCommand() {
        return isExit;
    }

    /**
     * Executes the given command to delete a task.
     *
     * @param list TaskList before deletion of task.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     * @throws DukeException If index provided is invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        int sizeBeforeDeleting = list.size();
        Task removed = list.deleteAtIndex(index);
        if (removed != null) {
            int sizeAfterDeleting = list.size();
            assert sizeBeforeDeleting - 1 == sizeAfterDeleting : "size should decrease by 1";
            String message =
                    "Noted.I've removed this task:\n"
                    + "  " + removed + "\n"
                    + String.format("Now you have %d tasks in the list.", list.size());
            storage.saveDukeData(list.convertToStorageString());
            return ui.printAndReturnMessage(message);
        } else {
            throw new InvalidIndexException();
        }
    }
}
