package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done.
 *
 * @author Sherman Ng Wei Sheng
 */
public class MarkDoneCommand extends Command {

    private boolean isExit;
    private int index;

    /**
     * Constructor for the command to mark a task as done.
     *
     * @param index The index of the task to be removed.
     */
    public MarkDoneCommand(int index) {
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
     * Executes the command to mark a task as done.
     *
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     * @throws DukeException If index provided is invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        int sizeBeforeMarking = list.size();
        boolean markedDone = list.markDoneAtIndex(index);
        if (markedDone) {
            int sizeAfterMarking = list.size();
            assert sizeBeforeMarking == sizeAfterMarking : "size should remain the same";
            String message =
                    "Nice! I've marked this task as done:\n"
                    + list.get(index);
            storage.saveDukeData(list.convertToStorageString());
            return ui.printAndReturnMessage(message);
        } else {
            throw new InvalidIndexException();
        }
    }
}
