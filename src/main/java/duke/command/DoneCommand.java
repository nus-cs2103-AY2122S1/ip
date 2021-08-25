package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * The DoneCommand class represents the done command for marking specific Task as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Class constructor that receives an index of the Task in the TaskList to be marked as done.
     *
     * @param index The index of the Task in the TaskList.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Method which executes the marking of specified task as done.
     *
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @throws DukeException On index out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskDone = tasks.getTask(index);
            taskDone.markAsDone();
            ui.displayCompletedMessage(taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t List number out of range, please enter a valid number\n");
        }
    }
}
