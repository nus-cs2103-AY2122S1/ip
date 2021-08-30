package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class the implements command. This class is responsible for marking a task in
 * the task list as completed/done.
 */
public class DoneCommand implements Command {
    private String indexString;

    /**
     * Class constructor for the done command.
     *
     * @param indexString The index of the task in the task list to be marked as done.
     */
    public DoneCommand(String indexString) {
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
                return ui.errorFrame(" That task does not exist!");
            } else {
                t.get(index).setDone();
                return ui.textFrame("Good job, I have marked the task as done!" + "\n"
                        + t.get(index).toString());
            }
        } catch (NumberFormatException e) {
            return ui.errorFrame("That is not a valid index!");
        }
    }
}
