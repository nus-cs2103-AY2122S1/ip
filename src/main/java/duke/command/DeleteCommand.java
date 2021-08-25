package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The DeleteCommand class represents the delete command for removing Task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Class constructor that receives an index representing the position in the TaskList of the Task to be deleted.
     *
     * @param index The index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Method which executes the deleting of the specific Task from the TaskList.
     *
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @throws DukeException On index out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.deleteTask(index);
            ui.displayDeleteMessage(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t List number out of range, please enter a valid number\n");
        }
    }
}
