package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A class that implements command. This class is responsible for any deletion
 * of tasks from the current task list.
 */
public class DeleteCommand implements Command {
    private final String indexString;

    /**
     * Class constructor for the delete command.
     *
     * @param indexString The index of the task to be deleted in the task list.
     */
    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Method to determine if the program should continue running.
     *
     * @return Always returns true.
     */
    @Override
    public boolean isRunning() {
        return true;
    }

    /**
     * Method to execute the given command.
     *
     * @param t The TaskList loaded.
     * @param ui The Ui object running the program.
     * @param storage The Storage object handling the loading and saving.
     * @throws DukeException Thrown in the event of input format errors.
     */
    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(indexString) - 1;
            if (index >= t.getSize() || index <= -1) {
                throw new DukeException("invalid task");
            } else {
                Task deletedTask = t.remove(index);
                return ui.textFrame("Quitter! I have deleted that task for you" + "\n"
                        + deletedTask + "\n"
                        + "Now you have " + t.getSize() + " tasks left.");
            }
        } catch (NumberFormatException | DukeException e) {
            return ui.errorFrame(e.getMessage());
        }

    }
}
